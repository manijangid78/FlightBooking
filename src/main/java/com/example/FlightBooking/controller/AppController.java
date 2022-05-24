package com.example.FlightBooking.controller;

import com.example.FlightBooking.model.Flight;
import com.example.FlightBooking.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class AppController {

    @Autowired
    private AppService appService;

//    @GetMapping("/test")
//    public String test(){
//        try {
//            appService.addFlight();
//            return "Done";
//        }catch (Exception e){
//            e.printStackTrace();
//            return "Exception";
//        }
//
//    }

    @GetMapping("/search")
    public List<Object> search(@RequestParam("source") String source, @RequestParam("destination")String destination, @RequestParam("Date") Date date){
        List<Object> objects = null;
        try {
            objects =  appService.getFlightsBySourceDestinationAndDate(source,destination,date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return objects;
    }

    @PostMapping("/bookTicket")
    public String bookTicket(@RequestParam("flight_id") int flightId, @RequestParam("source")String source,
                      @RequestParam("destination")String destination, @RequestParam("seatCount") int seatCount ){
        try {
            return appService.bookFlightTickets(flightId, source, destination, seatCount);
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }



    @GetMapping("/getAllFlights")
    public List<Flight> getFlights(){
        List<Flight> flights = null;
        try {
            flights = appService.getFlights();
        }catch (Exception e){
            e.printStackTrace();
        }
        return flights;
    }
}
