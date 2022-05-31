package com.example.FlightBooking.model;

import java.util.List;

public class BookingResponse {

    private List<Booking> bookings;

    public BookingResponse() {
    }

    public BookingResponse(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
