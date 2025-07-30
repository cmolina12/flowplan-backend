package com.cmolina12.senehorario_backend.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import lombok.Getter;

public class Meeting {
    @Getter private final DayOfWeek day;     // LUNES, MARTES, ...
    @Getter private final LocalTime start;   // hora de inicio
    @Getter private final LocalTime end;     // hora de fin
    @Getter private final String location;   // edificio + aula

    public Meeting(DayOfWeek day,
                   LocalTime start,
                   LocalTime end,
                   String location) {
        this.day = day;
        this.start = start;
        this.end = end;
        this.location = location;
    }

}