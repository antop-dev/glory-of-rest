package org.antop.http.service;

import org.antop.http.SpringHttpApplicationTests;
import org.antop.http.exception.SlotNotAvailableException;
import org.antop.http.model.Appointment;
import org.antop.http.model.Patient;
import org.antop.http.model.Slot;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class AppointmentServiceTest extends SpringHttpApplicationTests {

    @Test
    public void openSlotRequest() {
        String doctor = "mjones";

        AppointmentService service = appointmentService();
        List<Slot> slots = service.openSlotRequest(LocalDate.now(), doctor);

        assertThat(slots, not(IsEmptyCollection.empty()));
        assertThat(slots.size(), is(2));
        assertThat(slots.get(0).getStart(), equalTo(LocalTime.of(14, 0)));
        assertThat(slots.get(0).getEnd(), equalTo(LocalTime.of(14, 50)));
        assertThat(slots.get(0).getDoctor(), equalTo(doctor));
    }

    @Test
    public void appointmentRequest() {
        AppointmentService service = appointmentService();
        Slot slot = Slot.of(LocalTime.of(18, 0), LocalTime.of(18, 30), "mjones");
        Patient patient = Patient.of("jsmith");
        Appointment appointment = service.appointmentRequest(slot, patient);

        assertThat(appointment.getSlot().getStart(), equalTo(slot.getStart()));
        assertThat(appointment.getSlot().getEnd(), equalTo(slot.getEnd()));
        assertThat(appointment.getPatient().getId(), equalTo(patient.getId()));
    }

    @Test(expected = SlotNotAvailableException.class)
    public void appointmentRequestError() {
        AppointmentService service = appointmentService();
        Slot slot = Slot.of(LocalTime.of(18, 0), LocalTime.of(18, 30), "antop");
        Patient patient = Patient.of("jsmith");

        service.appointmentRequest(slot, patient);
    }
}