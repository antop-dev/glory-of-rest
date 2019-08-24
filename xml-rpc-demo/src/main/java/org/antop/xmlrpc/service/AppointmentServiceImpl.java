package org.antop.xmlrpc.service;

import org.antop.xmlrpc.metadata.XmlRpc;
import org.antop.xmlrpc.model.Appointment;
import org.antop.xmlrpc.model.Patient;
import org.antop.xmlrpc.model.Slot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@XmlRpc("Appointment")
public class AppointmentServiceImpl implements AppointmentService {

    @Override
    public List<Slot> openSlotRequest(LocalDate date, String doctor) {
        return Arrays.asList(
                Slot.of(LocalTime.of(14, 0), LocalTime.of(14, 50), doctor),
                Slot.of(LocalTime.of(16, 0), LocalTime.of(16, 50), doctor)
        );
    }

    @Override
    public Appointment appointmentRequest(Slot slot, Patient patient) {
        Appointment appointment = Appointment.of(slot, patient);
        return appointment;
    }

}