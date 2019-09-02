package org.antop.hateoas.controller;

import org.antop.hateoas.model.Appointment;
import org.antop.hateoas.service.SlotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;

@RestController
public class SlotController {
    private final SlotService service;

    public SlotController(SlotService service) {
        this.service = service;
    }

    @PostMapping(path = "/slots/{id}")
    public ResponseEntity<?> slots(@PathVariable("id") int slotId, @RequestBody AppointmentRequest request) {
        try {
            Appointment appointment = service.appointment(slotId, request.getPatient().getId());
            appointment.addLink("/linkrels/appointment/cancel", "/slots/" + appointment.getSlot().getId() + "/appointment");
            appointment.addLink("/linkrels/appointment/addTest", "/slots/" + appointment.getSlot().getId() + "/appointment/tests");
            appointment.addLink("self", "/slots/" + appointment.getSlot().getId() + "/appointment");
            appointment.addLink("/linkrels/appointment/changeTime", "/doctors/" + appointment.getSlot().getDoctor()
                    + "/slots?date=" + appointment.getSlot().getDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "&status=open");
            appointment.addLink("/linkrels/appointment/updateContactInfo", "/patients/" + request.getPatient().getId() + "/contactInfo");
            appointment.addLink("/linkrels/help", "/help/appointment");

            return ResponseEntity.ok(appointment);
        } catch (Exception e) {
            AppointmentRequestFailure failure = new AppointmentRequestFailure(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(failure);
        }
    }

}
