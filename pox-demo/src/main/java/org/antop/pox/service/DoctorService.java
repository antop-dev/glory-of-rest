package org.antop.pox.service;

import org.antop.pox.endpoint.OpenSlotRequest;
import org.antop.pox.model.Slot;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
public class DoctorService {

    public List<Slot> openSlotList(OpenSlotRequest request) {
        return Arrays.asList(
                Slot.of(LocalTime.of(14, 0), LocalTime.of(14, 50), request.getDoctor()),
                Slot.of(LocalTime.of(16, 0), LocalTime.of(16, 50), request.getDoctor())
        );
    }

}
