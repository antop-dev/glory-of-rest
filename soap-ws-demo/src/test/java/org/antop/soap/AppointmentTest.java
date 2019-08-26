package org.antop.soap;

import org.antop.appointment_service.*;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.SoapFaultClientException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppointmentTest extends WebServiceGatewaySupport {
    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        setDefaultUri("http://localhost:" + port + "/ws/appointment");

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.antop.appointment_service");

        setMarshaller(marshaller);
        setUnmarshaller(marshaller);
    }

    @Test
    public void openSlotList() {
        String doctor = "mjones";
        OpenSlotListResponse response = openSlotListRequest(LocalDate.now(), doctor);

        List<Slot> slots = response.getSlot();
        assertThat(slots, not(IsEmptyCollection.empty()));
        assertThat(slots.size(), is(2));
        assertThat(slots.get(0).getStart(), equalTo(LocalTime.of(14, 0)));
        assertThat(slots.get(0).getEnd(), equalTo(LocalTime.of(14, 50)));
        assertThat(slots.get(0).getDoctor(), equalTo(doctor));
    }

    @Test
    public void appointment() {
        Slot slot = new Slot();
        slot.setStart(LocalTime.of(17, 0));
        slot.setEnd(LocalTime.of(17, 45));
        slot.setDoctor("mjones");

        Patient patient = new Patient();
        patient.setId("jsmith");

        AppointmentResponse response = appointmentRequest(slot, patient);

        assertThat(response.getSlot().getStart(), equalTo(slot.getStart()));
        assertThat(response.getSlot().getEnd(), equalTo(slot.getEnd()));
        assertThat(response.getPatient().getId(), equalTo(patient.getId()));
    }

    @Test
    public void appointmentError() {
        Slot slot = new Slot();
        slot.setStart(LocalTime.of(17, 0));
        slot.setEnd(LocalTime.of(17, 45));
        slot.setDoctor("antop"); // error

        Patient patient = new Patient();
        patient.setId("jsmith");

        try {
            appointmentRequest(slot, patient);
            Assert.fail("Can not succeed."); // 성공하면 안된다.
        } catch (SoapFaultClientException e) {
            assertThat(e.getFaultStringOrReason(), equalTo("Slot not available"));
        }
    }

    public OpenSlotListResponse openSlotListRequest(LocalDate date, String doctor) {
        OpenSlotListRequest request = new OpenSlotListRequest();
        request.setDate(date);
        request.setDoctor(doctor);
        return (OpenSlotListResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public AppointmentResponse appointmentRequest(Slot slot, Patient patient) {
        AppointmentRequest request = new AppointmentRequest();
        request.setSlot(slot);
        request.setPatient(patient);
        return (AppointmentResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

}
