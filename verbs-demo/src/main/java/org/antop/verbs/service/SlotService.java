package org.antop.verbs.service;

import org.antop.verbs.exception.SlotNotAvailableException;
import org.antop.verbs.model.Appointment;
import org.antop.verbs.model.Patient;
import org.antop.verbs.model.Slot;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class SlotService {

    public Appointment appointment(int slotId, String patient) {
        String doctor = slotId == 9999 ? "antop" : "mjones";
        Slot slot = Slot.of(slotId, LocalTime.of(14, 0), LocalTime.of(14, 50), doctor);

        if (slot.getDoctor().equals("antop")) {
            throw new SlotNotAvailableException();
        }

        Appointment appointment = new Appointment();
        appointment.setSlot(slot);
        appointment.setPatient(Patient.of(patient));
        return appointment;
    }

}
