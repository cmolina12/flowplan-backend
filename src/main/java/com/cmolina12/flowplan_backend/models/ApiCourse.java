package com.cmolina12.flowplan_backend.models;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class ApiCourse {
     @Getter @Setter private String llave; // 'llave' is a unique identifier for the course section, which is used to reference the course in the system.
     @Getter @Setter private String nrc; // 'nrc' stands for 'Número de Registro de Clase', which is a unique number assigned to each course section in the system.

     @JsonProperty("class") // 'class' is a reserved keyword in Java, so we use @JsonProperty to map it to a different name.
     @Getter @Setter private String clazz; // 'clazz' is used to represent the class type or category of the course, such as 'IIND' for Industrial Engineering.
     @Getter @Setter private String course; // 'course' represents the course code, such as '2201', which is a unique identifier for the course subject.
     @Getter @Setter private String section; // 'section' represents the specific section of the course, such as '1', which can indicate different times or instructors for the same course.
     @Getter @Setter private String credits; // 'credits' indicates the number of academic credits assigned to the course, such as '3', which is a measure of the course's workload and value in the curriculum.
     @Getter @Setter private String title; // 'title' is the name of the course, such as 'CONTROL DE PRODUCCION', which describes the content or focus of the course.
     @Getter @Setter private String maxenrol; // 'maxenrol' indicates the maximum number of students that can enroll in the course section, such as '40', which helps manage class sizes.
     @Getter @Setter private String enrolled; // 'enrolled' indicates the current number of students enrolled in the course section, such as '12', which helps track class participation.
     @Getter @Setter private String term; // 'term' represents the academic term in which the course is offered, such as '202519', which can indicate the year and semester.
     @Getter @Setter private String ptrm; // 'ptrm' stands for 'Periodo de Tiempo', which indicates the time period of the course, such as '1' for a full term.
     @Getter @Setter private String ptrmdesc; // 'ptrmdesc' is a description of the time period, such as 'PERIODO COMPLETO', which provides more context about the course duration.
     @Getter @Setter private String seatsavail; // 'seatsavail' indicates the number of available seats in the course section, such as '28', which helps students know if they can still enroll.
     @Getter @Setter private String campus; // 'campus' indicates the campus where the course is offered, such as 'CAMPUS PRINCIPAL', which helps students locate the course.
     @Getter @Setter private String projenrl; // 'projenrl' indicates the projected enrollment for the course section, such as '0', which can help in planning and resource allocation.
     @Getter @Setter  private List<Schedule> schedules; // 'schedules' is a list of Schedule objects that represent the class schedule, including times, days, and locations for the course section.
     @Getter @Setter private List<Instructor> instructors; // 'instructors' is a list of Instructor objects that represent the instructors teaching the course section, including their names and identifiers.
     @Getter @Setter private String levele; // 'levele' indicates the level of the course, such as 'PRE' for undergraduate courses, which helps categorize the course in the curriculum.
     @Getter @Setter private String comments; // 'comments' can contain additional information or notes about the course section, which may be useful for students or administrators.
     @Getter @Setter private List<Attr> attr; // 'attr' is a list of Attr objects that represent additional attributes or characteristics of the course section, such as special requirements or designations.

}
