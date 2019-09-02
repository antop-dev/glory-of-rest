package org.antop.hateoas.service;

import org.antop.hateoas.exception.SlotNotAvailableException;
import org.antop.hateoas.model.Appointment;
import org.antop.hateoas.model.Patient;
import org.antop.hateoas.model.Slot;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class SlotService {

    public Appointment appointment(int slotId, String patient) {
        String doctor = slotId == 9999 ? "antop" : "mjones";
        Slot slot = Slot.of(slotId, LocalDate.of(2010, 10, 04),
                LocalTime.of(14, 0),
                LocalTime.of(14, 50), doctor);

        if (slot.getDoctor().equals("antop")) {
            throw new SlotNotAvailableException();
        }

        Appointment appointment = new Appointment();
        appointment.setSlot(slot);
        appointment.setPatient(Patient.of(patient));
        return appointment;
    }

}
