package org.antop.jsonrpc.model;

import lombok.Data;

@Data
public class Appointment {
    private Slot slot;
    private Patient patient;

    public static Appointment of(Slot slot, Patient patient) {
        Appointment appointment = new Appointment();
        appointment.slot = slot;
        appointment.patient = patient;
        return appointment;
    }

}
