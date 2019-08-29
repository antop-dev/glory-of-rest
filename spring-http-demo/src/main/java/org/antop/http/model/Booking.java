package org.antop.http.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Booking implements Serializable {
    private String bookingCode;

    public Booking(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    @Override
    public String toString() {
        return String.format("Ride confirmed: code '%s'.", bookingCode);
    }

}
