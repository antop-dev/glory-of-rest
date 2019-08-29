package org.antop.http.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Appointment implements Serializable {
    private Slot slot;
    private Patient patient;

    public static Appointment of(Slot slot, Patient patient) {
        Appointment appointment = new Appointment();
        appointment.slot = slot;
        appointment.patient = patient;
        return appointment;
    }

}
