package org.antop.resources.service;

import org.antop.resources.model.Appointment;
import org.antop.resources.model.Patient;
import org.antop.resources.model.Slot;
import org.antop.resources.repository.SlotRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SlotService {
    private final SlotRepository slotRepository;

    public SlotService(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    public Optional<Appointment> appointmentRequest(int slotId, String patient) {
        Slot slot = slotRepository.getSlot(slotId);
        if (slot == Slot.NONE || slot.getDoctor().equals("antop")) {
            return Optional.empty();
        }

        Appointment appointment = new Appointment();
        appointment.setSlot(slot);
        appointment.setPatient(Patient.of(patient));
        return Optional.of(appointment);
    }

}
