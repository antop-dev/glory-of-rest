package org.antop.hateoas.controller;

import lombok.Data;
import org.antop.hateoas.model.Patient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "appointmentRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class AppointmentRequest {
    @XmlElement(name = "patient")
    private Patient patient;
}
