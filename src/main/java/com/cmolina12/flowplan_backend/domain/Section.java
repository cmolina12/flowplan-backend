package com.cmolina12.flowplan_backend.domain;

import java.util.List;

public class Section {
    private final String nrc;             // p.ej. "11060"
    private final String sectionId;       // p.ej. "1", "A", "B"
    private final String term;            // p.ej. "202519"
    private final String ptrm;            // p.ej. "1" o "8A"
    private final String campus;          // p.ej. "CAMPUS PRINCIPAL"
    private final List<Meeting> meetings; // horarios
    private final List<String> professors;// nombres de instructores

    public Section(String nrc,
                   String sectionId,
                   String term,
                   String ptrm,
                   String campus,
                   List<Meeting> meetings,
                   List<String> professors) {
        this.nrc = nrc;
        this.sectionId = sectionId;
        this.term = term;
        this.ptrm = ptrm;
        this.campus = campus;
        this.meetings = meetings;
        this.professors = professors;
    }

    public String getNrc() {
        return nrc;
    }

    public String getSectionId() {
        return sectionId;
    }

    public String getTerm() {
        return term;
    }

    public String getPtrm() {
        return ptrm;
    }

    public String getCampus() {
        return campus;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public List<String> getProfessors() {
        return professors;
    }
}
