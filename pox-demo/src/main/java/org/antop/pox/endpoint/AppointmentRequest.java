package org.antop.pox.endpoint;

import lombok.Data;
import org.antop.pox.model.Patient;
import org.antop.pox.model.Slot;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "appointmentRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class AppointmentRequest {
    @XmlElement(name = "slot")
    private Slot slot;
    @XmlElement(name = "patient")
    private Patient patient;
}
