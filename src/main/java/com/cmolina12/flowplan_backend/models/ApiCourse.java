package com.cmolina12.flowplan_backend.models;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ApiCourse represents a course in the system with various attributes such as course key, section, credits, title, schedules, instructors, etc.
 * It is used to map the JSON response from the API to a Java object.
 */

 /**
  * Example JSON structure:
   {"rn":"1","llave":"10149202519","nrc":"10149","class":"IIND","course":"2201","section":"1","credits":"3","title":"CONTROL DE PRODUCCION","maxenrol":"40","enrolled":"12","term":"202519","ptrm":"1","ptrmdesc":"PERIODO COMPLETO","seatsavail":"28","campus":"CAMPUS PRINCIPAL","projenrl":"0","schedules":[{"time_ini":"1100","time_fin":"1250","classroom":".SD_715","l":null,"m":"M","i":"I","j":"J","v":null,"s":null,"d":null,"date_ini":"2025-06-03 00:00:00","date_fin":"2025-07-26 00:00:00","building":".Edif. J.M.Santodomingo (SD)","patron":"2"}],"instructors":[{"name":"MARTINEZ CONTRERAS JOHANA MILENA","ind":"Y"}],"levele":"PRE","comments":null,"attr":[]}
  */


public class ApiCourse {
    private String llave; // 'llave' is a unique identifier for the course section, which is used to reference the course in the system.
    private String nrc; // 'nrc' stands for 'Número de Registro de Clase', which is a unique number assigned to each course section in the system.

    @JsonProperty("class") // 'class' is a reserved keyword in Java, so we use @JsonProperty to map it to a different name.
    private String clazz; // 'clazz' is used to represent the class type or category of the course, such as 'IIND' for Industrial Engineering.
    private String course; // 'course' represents the course code, such as '2201', which is a unique identifier for the course subject.
    private String section; // 'section' represents the specific section of the course, such as '1', which can indicate different times or instructors for the same course.
    private String credits; // 'credits' indicates the number of academic credits assigned to the course, such as '3', which is a measure of the course's workload and value in the curriculum.
    private String title; // 'title' is the name of the course, such as 'CONTROL DE PRODUCCION', which describes the content or focus of the course.
    private String maxenrol; // 'maxenrol' indicates the maximum number of students that can enroll in the course section, such as '40', which helps manage class sizes.
    private String enrolled; // 'enrolled' indicates the current number of students enrolled in the course section, such as '12', which helps track class participation.
    private String term; // 'term' represents the academic term in which the course is offered, such as '202519', which can indicate the year and semester.
    private String ptrm; // 'ptrm' stands for 'Periodo de Tiempo', which indicates the time period of the course, such as '1' for a full term.
    private String ptrmdesc; // 'ptrmdesc' is a description of the time period, such as 'PERIODO COMPLETO', which provides more context about the course duration.
    private String seatsavail; // 'seatsavail' indicates the number of available seats in the course section, such as '28', which helps students know if they can still enroll.
    private String campus; // 'campus' indicates the campus where the course is offered, such as 'CAMPUS PRINCIPAL', which helps students locate the course.
    private String projenrl; // 'projenrl' indicates the projected enrollment for the course section, such as '0', which can help in planning and resource allocation.
    private List<Schedule> schedules; // 'schedules' is a list of Schedule objects that represent the class schedule, including times, days, and locations for the course section.
    private List<Instructor> instructors; // 'instructors' is a list of Instructor objects that represent the instructors teaching the course section, including their names and identifiers.
    private String levele; // 'levele' indicates the level of the course, such as 'PRE' for undergraduate courses, which helps categorize the course in the curriculum.
    private String comments; // 'comments' can contain additional information or notes about the course section, which may be useful for students or administrators.
    private List<Attr> attr; // 'attr' is a list of Attr objects that represent additional attributes or characteristics of the course section, such as special requirements or designations.

    // Getters and Setters

    public String getLlave() {
        return llave;
    }
    public void setLlave(String llave) {
        this.llave = llave;
    }

    public String getNrc() {
        return nrc;
    }
    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getClazz() {
        return clazz;
    }
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }

    public String getSection() {
        return section;
    }
    public void setSection(String section) {
        this.section = section;
    }

    public String getCredits() {
        return credits;
    }
    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaxenrol() {
        return maxenrol;
    }
    public void setMaxenrol(String maxenrol) {
        this.maxenrol = maxenrol;
    }

    public String getEnrolled() {
        return enrolled;
    }
    public void setEnrolled(String enrolled) {
        this.enrolled = enrolled;
    }

    public String getTerm() {
        return term;
    }
    public void setTerm(String term) {
        this.term = term;
    }

    public String getPtrm() {
        return ptrm;
    }
    public void setPtrm(String ptrm) {
        this.ptrm = ptrm;
    }

    public String getPtrmdesc() {
        return ptrmdesc;
    }
    public void setPtrmdesc(String ptrmdesc) {
        this.ptrmdesc = ptrmdesc;
    }

    public String getSeatsavail() {
        return seatsavail;
    }
    public void setSeatsavail(String seatsavail) {
        this.seatsavail = seatsavail;
    }

    public String getCampus() {
        return campus;
    }
    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getProjenrl() {
        return projenrl;
    }
    public void setProjenrl(String projenrl) {
        this.projenrl = projenrl;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }
    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }
    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    public String getLevele() {
        return levele;
    }
    public void setLevele(String levele) {
        this.levele = levele;
    }

    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<Attr> getAttr() {
        return attr;
    }
    public void setAttr(List<Attr> attr) {
        this.attr = attr;
    }

    
}
