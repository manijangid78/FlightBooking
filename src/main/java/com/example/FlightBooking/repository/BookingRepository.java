package com.example.FlightBooking.repository;

import com.example.FlightBooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
