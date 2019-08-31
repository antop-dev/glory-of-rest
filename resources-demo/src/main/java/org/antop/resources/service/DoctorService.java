package org.antop.resources.service;

import org.antop.resources.model.Slot;
import org.antop.resources.repository.SlotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DoctorService {
    private final SlotRepository slotRepository;

    public DoctorService(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    public List<Slot> openSlotListRequest(String doctor, LocalDate date) {
        return slotRepository.getSlots(doctor, date);
    }

}
