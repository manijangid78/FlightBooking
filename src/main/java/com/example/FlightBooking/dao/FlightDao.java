package com.example.FlightBooking.dao;

import com.example.FlightBooking.model.Flight;
import com.example.FlightBooking.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Component
public class FlightDao {

    @Autowired
    private FlightRepository flightRepository;


    public String addFlight(Flight flight){
        flightRepository.save(flight);
        return "Done";
    }


    public List<Object> getFlightsBySourceDestinationAndDate(String source, String destination, Date date){
        return flightRepository.findFlights(source, destination, date);
    }

    public Flight getFlightById(int flightId){
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        Flight flight = null;
        if(!optionalFlight.isEmpty()) {
            flight = optionalFlight.get();
        }
        return flight;
    }

    public List<Flight> getFlights(){
        return flightRepository.findAll();
    }



}
