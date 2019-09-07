package org.antop.verbs.model;

import lombok.Data;
import org.antop.verbs.controller.AppointmentRequest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "appointment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Appointment extends AppointmentRequest {
    @XmlElement(name = "slot")
    private Slot slot;
}
