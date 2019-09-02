package org.antop.hateoas.service;

import org.antop.hateoas.model.Slot;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
public class DoctorService {

    public List<Slot> openSlotList(String doctor, LocalDate date, String status) {
        return Arrays.asList(
                Slot.of(1234, date, LocalTime.of(14, 0), LocalTime.of(14, 50), doctor),
                Slot.of(5678, date, LocalTime.of(16, 0), LocalTime.of(16, 50), doctor)
        );
    }

}
