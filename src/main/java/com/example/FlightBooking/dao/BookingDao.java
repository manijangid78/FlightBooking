package com.example.FlightBooking.dao;

import com.example.FlightBooking.model.Booking;
import com.example.FlightBooking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingDao {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking bookTicket(Booking booking){
        return bookingRepository.save(booking);
    }
}
