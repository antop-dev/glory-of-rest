package org.antop.http.service;

import org.antop.http.exception.BookingException;
import org.antop.http.model.Booking;

public interface CabBookingService {
    Booking bookRide(String pickUpLocation) throws BookingException;
}
