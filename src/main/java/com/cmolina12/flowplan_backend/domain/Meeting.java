package com.cmolina12.flowplan_backend.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Meeting {
    private final DayOfWeek day;     // LUNES, MARTES, ...
    private final LocalTime start;   // hora de inicio
    private final LocalTime end;     // hora de fin
    private final String location;   // edificio + aula

    public Meeting(DayOfWeek day,
                   LocalTime start,
                   LocalTime end,
                   String location) {
        this.day = day;
        this.start = start;
        this.end = end;
        this.location = location;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public String getLocation() {
        return location;
    }
}