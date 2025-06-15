package com.cmolina12.flowplan_backend.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


public class ApiCourse {
    private String llave;
    private String nrc;

    @JsonProperty("class")
    private String clazz;
    private String course;
    private String section;
    private String credits;
    private String title;
    private String maxenrol;
    private String enrolled;
    private String term;
    private String ptrm;
    private String ptrmdesc;
    private String seatsavail;
    private String campus;
    private String projenrl;
    private List<Schedule> schedules;
    private List<Instructor> instructors;
    private String levele;
    private String comments;
    private List<Attr> attr;

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
