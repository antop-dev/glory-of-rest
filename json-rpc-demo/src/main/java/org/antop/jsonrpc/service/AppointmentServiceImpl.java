package org.antop.jsonrpc.service;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.antop.jsonrpc.exception.SlotNotAvailableException;
import org.antop.jsonrpc.model.Appointment;
import org.antop.jsonrpc.model.Patient;
import org.antop.jsonrpc.model.Slot;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
@AutoJsonRpcServiceImpl
public class AppointmentServiceImpl implements AppointmentService {

    @Override
    public List<Slot> openSlotRequest(LocalDate date, String doctor) {
        return Arrays.asList(
                Slot.of(LocalTime.of(14, 0), LocalTime.of(14, 50), doctor),
                Slot.of(LocalTime.of(16, 0), LocalTime.of(16, 50), doctor)
        );
    }

    @Override
    public Appointment appointmentRequest(Slot slot, Patient patient) throws SlotNotAvailableException {
        Appointment appointment = Appointment.of(slot, patient);
        if (slot.getDoctor().equals("antop")) {
            throw new SlotNotAvailableException();
        }
        return appointment;
    }

}