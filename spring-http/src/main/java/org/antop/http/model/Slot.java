package org.antop.http.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;

@Data
public class Slot implements Serializable {
    private LocalTime start;
    private LocalTime end;
    private String doctor;

    public static Slot of(LocalTime start, LocalTime end, String doctor) {
        Slot slot = new Slot();
        slot.start = start;
        slot.end = end;
        slot.doctor = doctor;
        return slot;
    }

}
