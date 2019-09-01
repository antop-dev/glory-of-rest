package org.antop.verbs.controller;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "appointmentRequestFailure")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@EqualsAndHashCode(callSuper = true)
public class AppointmentRequestFailure extends AppointmentRequest {
    @XmlElement(name = "reason")
    private String reason;

    public AppointmentRequestFailure() {
    }

    public AppointmentRequestFailure(String reason) {
        this.reason = reason;
    }

}

