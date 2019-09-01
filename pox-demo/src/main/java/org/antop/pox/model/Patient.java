package org.antop.pox.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "patient")
@XmlAccessorType(XmlAccessType.FIELD)
public class Patient {
    @XmlAttribute(name = "id")
    private String id;

    public static Patient of(String id) {
        Patient patient = new Patient();
        patient.id = id;
        return patient;
    }
}
