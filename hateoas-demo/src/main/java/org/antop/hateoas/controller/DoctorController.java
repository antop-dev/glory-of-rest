package org.antop.hateoas.controller;

import org.antop.hateoas.hateoas.Link;
import org.antop.hateoas.model.Slot;
import org.antop.hateoas.service.DoctorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class DoctorController {
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @GetMapping(value = "/doctors/{id}/slots")
    public OpenSlotListResponse openSlotList(
            @PathVariable("id") String doctor,
            @RequestParam(name = "date") String date,
            @RequestParam(name = "status") String status) {
        List<Slot> slots = service.openSlotList(doctor, LocalDate.parse(date, DATE_FORMATTER), status);
        for (Slot slot : slots) {
            slot.addLink(Link.of("/linkrels/slot/book", "/slots/" + slot.getId()));
        }
        return OpenSlotListResponse.of(slots);
    }


}
