package org.antop.jsonrpc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalTime;

@Data
public class Slot {
    @JsonFormat(pattern = "HHmm")
    private LocalTime start;
    @JsonFormat(pattern = "HHmm")
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
