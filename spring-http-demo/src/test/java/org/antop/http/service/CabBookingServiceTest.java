package org.antop.http.service;

import org.antop.http.SpringHttpApplicationTests;
import org.antop.http.exception.BookingException;
import org.antop.http.model.Booking;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class CabBookingServiceTest extends SpringHttpApplicationTests {

    @Test
    public void bookRide() {
        String pickUpLocation = "Home";

        try {
            Booking booking = cabBookingservice().bookRide(pickUpLocation);

            assertNotNull(booking);
            assertNotNull(booking.getBookingCode());
        } catch (BookingException e) {
            assertThat(e.getMessage(), equalTo("Cab unavailable " + pickUpLocation));
        }

    }
}