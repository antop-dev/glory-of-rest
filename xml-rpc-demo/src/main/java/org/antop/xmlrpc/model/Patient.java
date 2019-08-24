package org.antop.xmlrpc.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Patient implements Serializable {
    private String id;

    public static Patient of(String id) {
        Patient patient = new Patient();
        patient.id = id;
        return patient;
    }
}
