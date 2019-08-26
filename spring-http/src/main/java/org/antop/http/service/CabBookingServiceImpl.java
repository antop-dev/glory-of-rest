package org.antop.http.service;

import org.antop.http.exception.BookingException;
import org.antop.http.model.Booking;

import java.util.Random;
import java.util.UUID;

public class CabBookingServiceImpl implements CabBookingService {
    private final Random random = new Random();

    @Override
    public Booking bookRide(String pickUpLocation) throws BookingException {
        if (random() < 0.3) {
            throw new BookingException("Cab unavailable " + pickUpLocation);
        }
        return new Booking(UUID.randomUUID().toString());
    }

    private int random() {
        return random.nextInt();
    }

}
