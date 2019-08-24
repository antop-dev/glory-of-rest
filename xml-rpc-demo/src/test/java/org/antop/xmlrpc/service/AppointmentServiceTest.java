package org.antop.xmlrpc.service;

import org.antop.xmlrpc.XmlRpcApplicationTests;
import org.antop.xmlrpc.model.Appointment;
import org.antop.xmlrpc.model.Patient;
import org.antop.xmlrpc.model.Slot;
import org.junit.Test;

import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class AppointmentServiceTest extends XmlRpcApplicationTests {

    @Test
    public void openSlotRequest() throws MalformedURLException {
        AppointmentService service = getDynamicProxy(AppointmentService.class);
        // request
        List<Slot> slots = service.openSlotRequest(LocalDate.now(), "mjones");
        // verify
        assertNotNull(slots);
        assertThat(slots, not(empty()));
        assertThat(slots.get(0).getStart(), equalTo(LocalTime.of(14, 0)));
        assertThat(slots.get(0).getEnd(), equalTo(LocalTime.of(14, 50)));
    }

    @Test
    public void appointmentRequest() throws MalformedURLException {
        AppointmentService service = getDynamicProxy(AppointmentService.class);
        // request
        Slot slot = Slot.of(LocalTime.of(16, 0), LocalTime.of(16, 50), "mjones");
        Patient patient = Patient.of("jsmith");
        Appointment appointment = service.appointmentRequest(slot, patient);

        assertThat(appointment.getSlot().getStart(), equalTo(slot.getStart()));
        assertThat(appointment.getSlot().getEnd(), equalTo(slot.getEnd()));
        assertThat(appointment.getSlot().getDoctor(), equalTo(slot.getDoctor()));
        assertThat(appointment.getPatient().getId(), equalTo(patient.getId()));
    }
}