package org.antop.pox.service;

import org.antop.pox.endpoint.AppointmentRequest;
import org.antop.pox.exception.SlotNotAvailableException;
import org.antop.pox.model.Appointment;
import org.antop.pox.model.Patient;
import org.springframework.stereotype.Service;

@Service
public class SlotService {

    public Appointment appointment(AppointmentRequest request) {
        if (request.getSlot().getDoctor().equals("antop")) {
            throw new SlotNotAvailableException();
        }

        Appointment appointment = new Appointment();
        appointment.setSlot(request.getSlot());
        appointment.setPatient(Patient.of(request.getPatient().getId()));
        return appointment;
    }

}
