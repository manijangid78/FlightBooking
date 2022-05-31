package com.example.FlightBooking.repository;

import com.example.FlightBooking.model.Flight;

import java.sql.Date;
import java.util.List;

public interface FlightCustomRepository {
    public List<Flight> findFlights(String source, String destination, Date date);
}
