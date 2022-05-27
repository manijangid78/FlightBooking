package com.example.FlightBooking.controller;

import com.example.FlightBooking.model.Booking;
import com.example.FlightBooking.model.Flight;
import com.example.FlightBooking.service.AppService;
import com.example.FlightBooking.service.MyUserDetailsService;
import com.sun.net.httpserver.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
public class AppController {

    @Autowired
    private AppService appService;



    @GetMapping("/test")
    public String test(){
        try {
            appService.addFlight();
            return "Done";
        }catch (Exception e){
            e.printStackTrace();
            return "Exception";
        }
    }

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

    @GetMapping("/bookTicket")
    public String bookTicket(@RequestParam("flight_id") int flightId, @RequestParam("source")String source,
                             @RequestParam("destination")String destination, @RequestParam("seatCount") int seatCount ,@RequestHeader("Authorization") String authorization){
        try {
//            final String authorization = httpRequest.getHeader("Authorization");
            if (authorization != null && authorization.toLowerCase().startsWith("basic")) {
                // Authorization: Basic base64credentials
                String base64Credentials = authorization.substring("Basic".length()).trim();
                byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
                String credentials = new String(credDecoded, StandardCharsets.UTF_8);
                // credentials = username:password
                System.out.println(credentials);
                final String[] values = credentials.split(":", 2);
                return appService.bookFlightTickets(flightId, source, destination, seatCount, values[0]);
            }else{
                return "User is not authenticated";
            }
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @GetMapping("/getBooking")
    public List<Booking> getBookings(@RequestHeader("Authorization") String authorization){
        try{
            if (authorization != null && authorization.toLowerCase().startsWith("basic")) {
                // Authorization: Basic base64credentials
                String base64Credentials = authorization.substring("Basic".length()).trim();
                byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
                String credentials = new String(credDecoded, StandardCharsets.UTF_8);
                // credentials = username:password
                System.out.println(credentials);
                final String[] values = credentials.split(":", 2);
                return appService.getBooking(values[0]);
            }else{
                return new ArrayList<>();
            }
        }catch (Exception e){
            return null;
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
