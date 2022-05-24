package com.example.FlightBooking.repository;

import com.example.FlightBooking.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer>, FlightCustomRepository {

}

