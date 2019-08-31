package org.antop.resources.repository;

import org.antop.resources.model.Slot;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SlotRepository {

    private final List<Slot> slots = new ArrayList<>();

    @PostConstruct
    void init() {
        int sequence = 0;
        String[] doctors = new String[]{"mjones", "antop"};

        for (String doctor : doctors) {
            for (int hour = 9; hour < 18; hour++) {
                Slot slot = Slot.of(sequence++, LocalTime.of(hour, 0), LocalTime.of(hour, 40), doctor);
                slots.add(slot);
            }
        }
    }

    public List<Slot> getSlots(String doctor, LocalDate date) {
        if (doctor.equals("antop")) {
            return Collections.emptyList();
        }

        return slots.stream().filter(it -> it.getDoctor().equals(doctor)).collect(Collectors.toList());
    }

    public Slot getSlot(int id) {
        return slots.stream().filter(it -> it.getId() == id).findFirst().orElse(Slot.NONE);
    }

}
