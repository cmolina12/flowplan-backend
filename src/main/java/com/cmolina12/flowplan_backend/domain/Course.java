package com.cmolina12.flowplan_backend.domain;

import java.util.ArrayList;
import java.util.List;
public class Course {
    private final String code; // The course code, e.g., "CS101"
    private final String title; // The course name, e.g., "Introduction to Computer Science"
    private final List<Section> sections = new ArrayList<>(); // A list of sections for this course

    public Course(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String getCode() {
        return code;
    }
    
    public String getTitle() {
        return title;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void addSection(Section section) {
        sections.add(section); // Adds a section to the course
    }
}
