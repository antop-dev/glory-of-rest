package org.antop.hateoas.model;

import lombok.Data;
import org.antop.hateoas.hateoas.HasLink;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "appointment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Appointment extends HasLink {
    @XmlElement(name = "slot")
    private Slot slot;
    @XmlElement(name = "patient")
    private Patient patient;
}
