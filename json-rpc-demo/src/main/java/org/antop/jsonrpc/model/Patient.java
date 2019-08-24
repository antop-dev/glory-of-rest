package org.antop.jsonrpc.model;

import lombok.Data;

@Data
public class Patient {
    private String id;

    public static Patient of(String id) {
        Patient patient = new Patient();
        patient.id = id;
        return patient;
    }
}
