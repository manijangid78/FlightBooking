package com.example.FlightBooking.repository;

import java.sql.Date;
import java.util.List;

public interface FlightCustomRepository {
    public List<Object> findFlights(String source, String destination, Date date);
}
