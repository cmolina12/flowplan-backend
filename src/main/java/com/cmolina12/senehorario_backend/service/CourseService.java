package com.cmolina12.senehorario_backend.service;

import com.cmolina12.senehorario_backend.domain.Course;
import com.cmolina12.senehorario_backend.domain.Meeting;
import com.cmolina12.senehorario_backend.domain.Section;
import com.cmolina12.senehorario_backend.models.ApiCourse;
import com.cmolina12.senehorario_backend.models.Attr;
import com.cmolina12.senehorario_backend.models.Instructor;
import com.cmolina12.senehorario_backend.models.Schedule;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CourseService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${uniandes.api.base-url}")
    private String apiBaseUrl;

    /**
     * Fetches raw course sections from the API based on the provided name input.
     */
    public ApiCourse[] fetchRawSections(String nameInput, String profesorName) {
        String url =
            apiBaseUrl +
            "?term=&ptrm=&prefix=&attr=&nameInput=" +
            nameInput.toUpperCase() +
            "&campus=&attrs=&timeStart=&offset=0&limit=25&courseQuotas=&days=&courseRestrictions=&programNew=&profesorName=" +
            (profesorName != null ? profesorName : "");

        return restTemplate.getForObject(url, ApiCourse[].class);
    }

    /**
     * Fetches all CBU (Ciclo Básico Uniandino) sections from the API.
     * Uses nameInput=CB (the proven search mechanism) with a high limit.
     */
    private ApiCourse[] fetchRawCBUSections() {
        String url =
            apiBaseUrl +
            "?term=&ptrm=&prefix=&attr=&nameInput=CB&campus=&attrs=" +
            "&timeStart=&offset=0&limit=500&courseQuotas=&days=&courseRestrictions=&programNew=&profesorName=";

        return restTemplate.getForObject(url, ApiCourse[].class);
    }

    /**
     * Maps an array of raw ApiCourse objects into grouped Course domain objects,
     * including attrs on each Section.
     */
    private List<Course> mapToDomain(ApiCourse[] raw) {
        if (raw == null) return new ArrayList<>();

        Map<String, Course> courseMap = new LinkedHashMap<>();

        for (ApiCourse a : raw) {
            String code = a.getClazz() + a.getCourse();

            Course course = courseMap.computeIfAbsent(code, c ->
                new Course(c, a.getTitle(), Integer.parseInt(a.getCredits()))
            );

            List<Meeting> meetings = new ArrayList<>();
            for (Schedule s : a.getSchedules()) {
                for (DayOfWeek day : parseDays(s)) {
                    LocalTime start = parseTime(s.getTime_ini());
                    LocalTime end = parseTime(s.getTime_fin());
                    String location = s.getBuilding() + " " + s.getClassroom();
                    meetings.add(new Meeting(day, start, end, location));
                }
            }

            List<String> profs = new ArrayList<>();
            for (Instructor ins : a.getInstructors()) {
                profs.add(reorderProfessorName(ins.getName()));
            }

            List<String> attrCodes = new ArrayList<>();
            if (a.getAttr() != null) {
                for (Attr attr : a.getAttr()) {
                    if (attr.getCode() != null) attrCodes.add(attr.getCode());
                }
            }

            int availableSeats = parseIntSafe(a.getSeatsavail());
            int totalSeats = parseIntSafe(a.getMaxenrol());
            availableSeats = Math.max(0, Math.min(availableSeats, totalSeats));

            Section sec = new Section(
                a.getNrc(),
                a.getSection(),
                a.getTerm(),
                a.getPtrm(),
                a.getCampus(),
                meetings,
                profs,
                availableSeats,
                totalSeats,
                attrCodes
            );

            course.addSection(sec);
        }

        return new ArrayList<>(courseMap.values());
    }

    /**
     * Converts raw course sections into grouped Course domain objects.
     */
    public List<Course> getDomainCourses(String nameInput, String profesorName) {
        ApiCourse[] raw = fetchRawSections(nameInput, profesorName);
        return mapToDomain(raw);
    }

    /**
     * Returns all CBU courses (prefix CB) with sections and attrs included.
     */
    public List<Course> getCBUCourses() {
        ApiCourse[] raw = fetchRawCBUSections();
        return mapToDomain(raw);
    }

    /**
     * Finds sections by course code.
     */
    public List<Section> findSectionsByCourseCode(String code) {
        List<Course> courses = getDomainCourses(code, "");

        if (courses.isEmpty()) {
            return new ArrayList<>();
        }

        Course course = courses.get(0);
        return course.getSections();
    }

    private LocalTime parseTime(String hhmm) {
        if (hhmm == null || hhmm.length() < 4) {
            throw new IllegalArgumentException(
                "Invalid time format when trying to parse: " + hhmm
            );
        }
        int hour = Integer.parseInt(hhmm.substring(0, 2));
        int minute = Integer.parseInt(hhmm.substring(2, 4));
        return LocalTime.of(hour, minute);
    }

    private List<DayOfWeek> parseDays(Schedule s) {
        List<DayOfWeek> days = new ArrayList<>();

        if ("L".equalsIgnoreCase(s.getL())) days.add(DayOfWeek.MONDAY);
        if ("M".equalsIgnoreCase(s.getM())) days.add(DayOfWeek.TUESDAY);
        if ("I".equalsIgnoreCase(s.getI())) days.add(DayOfWeek.WEDNESDAY);
        if ("J".equalsIgnoreCase(s.getJ())) days.add(DayOfWeek.THURSDAY);
        if ("V".equalsIgnoreCase(s.getV())) days.add(DayOfWeek.FRIDAY);
        if ("S".equalsIgnoreCase(s.getS())) days.add(DayOfWeek.SATURDAY);

        return days;
    }

    private String reorderProfessorName(String name) {
        if (name == null || name.trim().isEmpty()) return name;

        String[] parts = name.trim().split("\\s+");
        int len = parts.length;

        if (len == 2) {
            return parts[1] + " " + parts[0];
        } else if (len == 3) {
            return parts[2] + " " + parts[0] + " " + parts[1];
        } else if (len == 4) {
            return parts[2] + " " + parts[3] + " " + parts[0] + " " + parts[1];
        } else {
            return name;
        }
    }

    private int parseIntSafe(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
