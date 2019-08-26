package org.antop.http.service;

import org.antop.http.exception.SlotNotAvailableException;
import org.antop.http.model.Appointment;
import org.antop.http.model.Patient;
import org.antop.http.model.Slot;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Override
    public List<Slot> openSlotRequest(LocalDate date, String doctor) {
        return Arrays.asList(
                Slot.of(LocalTime.of(14, 0), LocalTime.of(14, 50), doctor),
                Slot.of(LocalTime.of(17, 0), LocalTime.of(17, 50), doctor)
        );
    }

    @Override
    public Appointment appointmentRequest(Slot slot, Patient patient) {
        if (slot.getDoctor().equals("antop")) {
            throw new SlotNotAvailableException();
        }

        return Appointment.of(
                Slot.of(slot.getStart(), slot.getEnd(), slot.getDoctor()),
                Patient.of(patient.getId())
        );
    }

}