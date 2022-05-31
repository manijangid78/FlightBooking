package com.example.FlightBooking.service;

import com.example.FlightBooking.constants.Constant;
import com.example.FlightBooking.dao.BookingDao;
import com.example.FlightBooking.dao.FlightDao;
import com.example.FlightBooking.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppService {

    @Autowired
    private FlightDao flightDao;

    @Autowired
    private BookingDao bookingDao;

    // add new flights
    public String addFlight(){
        flightDao.addFlight(new Flight("3",Constant.Jaipur,Constant.Banglore,Constant.Delhi,5000,new Date(new java.util.Date().getTime())));
        flightDao.addFlight(new Flight("4",Constant.Delhi,Constant.Banglore,Constant.Mumbai,6000,new Date(new java.util.Date().getTime())));
        flightDao.addFlight(new Flight("5",Constant.Jaipur,Constant.Delhi,"",8000,new Date(new java.util.Date().getTime())));
        flightDao.addFlight(new Flight("6",Constant.Jaipur,Constant.Banglore,Constant.Mumbai,7000,new Date(new java.util.Date().getTime())));
        return Constant.FlightsAdded;
    }

    // search flights based on source, destination and date
    public SearchResponse getFlightsBySourceDestinationAndDate(SearchBody searchBody){
        List<Flight> objects = null;
        try{
            objects = flightDao.getFlightsBySourceDestinationAndDate(searchBody.getSource(),searchBody.getDestination(),searchBody.getDate());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new SearchResponse(objects);
    }

    // get all booking form db  based on username at service level
    public BookingResponse getBooking(String username){
        try {
            return new BookingResponse(bookingDao.getBooking(username));
        }catch (Exception e){
            e.printStackTrace();
            return new BookingResponse();
        }
    }

    // for book flight tickets
    @Transactional
    public String bookFlightTickets(BookTicketBody bookTicketBody, String username){
        try {
//            get flight detials based on flight id
            Optional<Flight> optionalFlight = flightDao.getFlightById(bookTicketBody.getFlightId());
            if(optionalFlight.isPresent()){
                Flight flight = optionalFlight.get();
                if (flight != null && flight.getSeatAvailableCount() >= bookTicketBody.getPerson()) {
                    flight.setSeatAvailableCount(flight.getSeatAvailableCount() - bookTicketBody.getPerson());
                    flightDao.addFlight(flight);
//                    add a booking
                    bookingDao.bookTicket(new Booking(flight.getDate(), bookTicketBody.getSource(), bookTicketBody.getDestination(),
                            flight.getPrice() * bookTicketBody.getPerson(), flight.getSourceTime(), flight.getDestinationTime(), bookTicketBody.getPerson(),bookTicketBody.getFlightId(), username));
                    return Constant.BookingDone;
                } else {
                    return Constant.NoSeatAvailable;
                }
            }else{
                return Constant.InternalServerError;
            }
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public SearchResponse getFlights(){
        SearchResponse searchResponse=null;
        try{
            List<Flight> flights = flightDao.getFlights();
            if(flights!=null){
                searchResponse = new SearchResponse(flights);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return searchResponse;
    }

    @Transactional
    public String cancelFlight(int ticketId){
        try {
            Optional<Booking> optionalBooking = bookingDao.getBookingById(ticketId);
            if (optionalBooking.isPresent()) {
                Booking booking = optionalBooking.get();
                if (booking != null && booking.getStatus()==null) {
                    Optional<Flight> optionalFlight = flightDao.getFlightById(booking.getFlightId());
                    if (optionalFlight.isPresent()) {
                        Flight flight = optionalFlight.get();
                        flight.setSeatAvailableCount(flight.getSeatAvailableCount() + booking.getPersonCount());
                        flightDao.addFlight(flight);
                    }
                    booking.setStatus(Constant.TicketCanceled);
                    bookingDao.bookTicket(booking);
                    return Constant.CancelMsg;
                } else {
                    return Constant.InternalServerError;
                }
            } else {
                return Constant.InternalServerError;
            }
        }catch (Exception e){
            return Constant.InternalServerError;
        }
    }
}
