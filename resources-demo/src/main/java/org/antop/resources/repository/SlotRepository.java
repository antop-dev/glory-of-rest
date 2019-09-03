package org.antop.resources.repository;

import org.antop.resources.model.Slot;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SlotRepository {

    private final List<Slot> slots = new ArrayList<>();

    @PostConstruct
    void init() {
        List<Slot> slots = Arrays.asList(
                Slot.of(1234, LocalTime.of(14, 0), LocalTime.of(14, 50), "mjones"),
                Slot.of(5678, LocalTime.of(16, 0), LocalTime.of(16, 50), "mjones"),
                Slot.of(9999, LocalTime.of(16, 0), LocalTime.of(16, 50), "antop")
        );
        this.slots.addAll(slots);
    }

    public List<Slot> getSlots(String doctor, LocalDate date) {
        return slots.stream().filter(it -> it.getDoctor().equals(doctor)).collect(Collectors.toList());
    }

    public Slot getSlot(int id) {
        return slots.stream().filter(it -> it.getId() == id).findFirst().orElse(Slot.NONE);
    }

}
