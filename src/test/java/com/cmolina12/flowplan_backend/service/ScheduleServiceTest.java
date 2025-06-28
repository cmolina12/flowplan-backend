package com.cmolina12.flowplan_backend.service;

import org.junit.jupiter.api.Test;
import com.cmolina12.flowplan_backend.domain.Section;
import com.cmolina12.flowplan_backend.domain.Meeting;
import java.time.DayOfWeek;
import java.util.List;
import java.time.LocalTime;


class ScheduleServiceTest {
    
    
    private Section makeFullSection (String nrc, String sectionId, String term, String ptrm, String campus, DayOfWeek day, String start, String end, String location, List<String> professors)
    {
        Meeting meeting = new Meeting(day, LocalTime.parse(start), LocalTime.parse(end), location); // Create a Meeting object with the specified day, start time, end time, and location. This method assumes there's only one meeting per section for simplicity.
        return new Section(nrc, sectionId, term, ptrm, campus, List.of(meeting), professors); // Create a Section object with the provided parameters, including the list of meetings and professors.
    }

    @Test
    void courseWithTwoNonOverlappingSections_shouldReturnTwoSchedules(){

        Section s1 = makeFullSection(
            "11060", "1", "202519", "1", "CAMPUS PRINCIPAL",
            DayOfWeek.MONDAY, "09:00", "10:00", ".A_101",
            List.of("PROF. A")
        );
        Section s2 = makeFullSection(
            "11061", "2", "202519", "1", "CAMPUS PRINCIPAL",
            DayOfWeek.MONDAY, "10:00", "11:00", ".A_102",
            List.of("PROF. B")
        );
   
        List<List<Section>> candidates = List.of(List.of(s1,s2)); // Create a list of lists of Section objects, where each inner list represents the candidate sections for a certain course. In this case, we have two sections (s1 and s2) for the same course, which do not overlap in time.
    
        ScheduleService scheduleService = new ScheduleService(); // Create an instance of ScheduleService to use its methods for generating schedules based on course sections.

        List<List<Section>> schedules = scheduleService.generateAllSchedules(candidates); // Call the generateAllSchedules method of ScheduleService with the candidates list to get all possible schedules.

        // Assert that the size of the schedules list is 2, indicating that two non-overlapping sections were successfully scheduled. (2 because we have two sections for one course, so there are two possible schedules: one with section 1 and another with section 2)
        assert schedules.size() == 2 : "Expected 2 schedules, but got " + schedules.size();
        // Assert that the first schedule contains the first section and the second schedule contains the second section
        assert schedules.get(0).contains(s1) : "First schedule should contain section 1, but it does not.";
        assert schedules.get(1).contains(s2) : "Second schedule should contain section 2, but it does not.";

    
    }

    @Test
    void courseWithTwoOverlappingSections_shouldReturnEmptySchedule(){
        Section a1 = makeFullSection(
            "A1", "1", "202519", "1", "CAMPUS",
            DayOfWeek.MONDAY, "09:00", "10:00", "Edificio X Aula 1",
            List.of("PROF. A")
        );
        Section b1 = makeFullSection(
            "B1", "1", "202519", "1", "CAMPUS",
            DayOfWeek.MONDAY, "09:30", "10:30", "Edificio Y Aula 2",
            List.of("PROF. C")
        );

        List<List<Section>> candidates = List.of(List.of(a1), List.of(b1)); // Create a list of lists of Section objects, where each inner list represents a candidate section for the schedule.

        ScheduleService scheduleService = new ScheduleService(); // Create an instance of ScheduleService to use its methods for generating schedules based on course sections.

        List<List<Section>> schedules = scheduleService.generateAllSchedules(candidates); // Call the generateAllSchedules method of ScheduleService with the candidates list to get all possible schedules.

        // Assert that the size of the schedules list is 0, indicating that no schedules were generated due to overlapping sections. Section a1 and Section b1 overlap in time for different courses, so no valid schedule can be created.
        assert schedules.size() == 0 : "Expected 0 schedules, but got " + schedules.size();
        // Assert that the schedules list is empty, confirming that no valid schedules were generated
        assert schedules.isEmpty() : "Schedules should be empty, but it is not.";

    }
}
