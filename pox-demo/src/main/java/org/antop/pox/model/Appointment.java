package org.antop.pox.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "appointment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Appointment {
    @XmlElement(name = "slot")
    private Slot slot;
    @XmlElement(name = "patient")
    private Patient patient;
}
