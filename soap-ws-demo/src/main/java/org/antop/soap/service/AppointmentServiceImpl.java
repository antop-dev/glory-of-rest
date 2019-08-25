package org.antop.soap.service;

import org.antop.appointment_service.Appointment;
import org.antop.appointment_service.Patient;
import org.antop.appointment_service.Slot;
import org.antop.soap.exception.SlotNotAvailableException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Override
    public List<Slot> openSlotRequest(LocalDate date, String doctor) {
        Slot slot1 = new Slot();
        slot1.setStart(LocalTime.of(14, 0));
        slot1.setEnd(LocalTime.of(14, 50));
        slot1.setDoctor(doctor);

        Slot slot2 = new Slot();
        slot2.setStart(LocalTime.of(16, 0));
        slot2.setEnd(LocalTime.of(16, 50));
        slot2.setDoctor(doctor);

        List<Slot> slots = new ArrayList<>();
        slots.add(slot1);
        slots.add(slot2);

        return slots;
    }

    @Override
    public Appointment appointmentRequest(Slot slot, Patient patient) {
        if (slot.getDoctor().equals("antop")) {
            throw new SlotNotAvailableException();
        }

        Appointment appointment = new Appointment();
        appointment.setSlot(slot);
        appointment.setPatient(patient);

        return appointment;
    }

}