package com.cmolina12.senehorario_backend.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class Course {
    @Getter private final String code; // The course code, e.g., "CS101"
    @Getter private final String title; // The course name, e.g., "Introduction to Computer Science"
    @Getter private final List<Section> sections = new ArrayList<>(); // A list of sections for this course

    public Course(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public void addSection(Section section) {
        sections.add(section); // Adds a section to the course
    }
}
