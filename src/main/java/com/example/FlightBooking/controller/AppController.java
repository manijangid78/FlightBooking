package com.example.FlightBooking.controller;

import com.example.FlightBooking.constants.Constant;
import com.example.FlightBooking.model.*;
import com.example.FlightBooking.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@RestController
public class AppController {

    @Autowired
    private AppService appService;

    // Add new flights
    @GetMapping("/addFlight")
    public ResponseEntity<String> test(){
        String response = appService.addFlight();
        return new ResponseEntity<String>(response,HttpStatus.OK);
    }

    // search flights based on source, destination and date
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestBody SearchBody searchBody){
        SearchResponse searchResponse = appService.getFlightsBySourceDestinationAndDate(searchBody);
        if(searchResponse!=null) {
            return new ResponseEntity<SearchResponse>(searchResponse, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>(Constant.InternalServerError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // book flight tickets
    @PostMapping("/bookTicket")
    public ResponseEntity<String> bookTicket(@RequestBody BookTicketBody bookTicketBody , @RequestHeader("Authorization") String authorization){
//        checking decoding username and password
        if (authorization != null && authorization.toLowerCase().startsWith("basic")) {
            // Authorization: Basic base64credentials
            String base64Credentials = authorization.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            // credentials = username:password
            final String[] values = credentials.split(":", 2);
//            call funtion at service level
            String bookTicketRes = appService.bookFlightTickets(bookTicketBody, values[0]);
            if(bookTicketRes.equals(Constant.BookingDone)){
                return new ResponseEntity<String>(bookTicketRes, HttpStatus.OK);
            }else{
                return new ResponseEntity<String>(Constant.InternalServerError,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<String>(Constant.Unauthenticated,HttpStatus.UNAUTHORIZED);
        }
    }

    // get all booking done by the user
    @GetMapping("/getBooking")
    public ResponseEntity<?> getBookings(@RequestHeader("Authorization") String authorization){
        // authorisation check  and decode the username and password
        if (authorization != null && authorization.toLowerCase().startsWith("basic")) {
            // Authorization: Basic base64credentials
            String base64Credentials = authorization.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            // credentials = username:password
            final String[] values = credentials.split(":", 2);
            BookingResponse bookingResponse =appService.getBooking(values[0]);
            if(bookingResponse.getBookings()!=null){
                return new ResponseEntity<BookingResponse>(bookingResponse,HttpStatus.OK);
            }else{
                return new ResponseEntity<String>(Constant.InternalServerError,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<String>(Constant.Unauthenticated,HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getAllFlights")
    public ResponseEntity<SearchResponse> getFlights(){
        SearchResponse flights = appService.getFlights();
        return new ResponseEntity<>(flights,HttpStatus.OK);
    }

    @PutMapping("/cancelBooking")
    public ResponseEntity<String> cancelBooking(@RequestParam("ticketId")int ticketId){
        String response = appService.cancelFlight(ticketId);
        if(response.equals(Constant.CancelMsg)){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
