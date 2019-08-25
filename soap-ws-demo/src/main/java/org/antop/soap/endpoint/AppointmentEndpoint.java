package org.antop.soap.endpoint;

import org.antop.appointment_service.*;
import org.antop.soap.service.AppointmentService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class AppointmentEndpoint {
    public static final String NAMESPACE_URI = "http://antop.org/appointment-service";

    private final AppointmentService appointmentService;

    public AppointmentEndpoint(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "openSlotListRequest")
    @ResponsePayload
    public OpenSlotListResponse openSlotList(@RequestPayload OpenSlotListRequest request) {
        if (request.getDate() == null) {
            throw new IllegalArgumentException("date is invalid");
        }
        if (request.getDoctor() == null) {
            throw new IllegalArgumentException("doctor is invalid");
        }

        OpenSlotListResponse response = new OpenSlotListResponse();
        List<Slot> slots = appointmentService.openSlotRequest(request.getDate(), request.getDoctor());
        response.getSlot().addAll(slots);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "appointmentRequest")
    @ResponsePayload
    public AppointmentResponse appointment(@RequestPayload AppointmentRequest request) {
        Appointment appointment = appointmentService.appointmentRequest(request.getSlot(), request.getPatient());

        AppointmentResponse response = new AppointmentResponse();
        response.setSlot(appointment.getSlot());
        response.setPatient(appointment.getPatient());

        return response;
    }

}
