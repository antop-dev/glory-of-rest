package org.antop.verbs.controller;

import org.antop.verbs.model.Appointment;
import org.antop.verbs.service.SlotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class SlotController {
    private final SlotService service;

    public SlotController(SlotService service) {
        this.service = service;
    }

    @PostMapping(path = "/slots/{id}")
    public ResponseEntity<AppointmentRequest> slots(@PathVariable("id") int slotId, @RequestBody AppointmentRequest request) {
        try {
            Appointment appointment = service.appointment(slotId, request.getPatient().getId());
            return ResponseEntity.created(URI.create("/slots/" + appointment.getSlot().getId() + "/appointment")).body(appointment);
        } catch (Exception e) {
            AppointmentRequestFailure failure = new AppointmentRequestFailure(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(failure);
        }
    }

}
