package com.example.FlightBooking.service;

import com.example.FlightBooking.dao.BookingDao;
import com.example.FlightBooking.dao.FlightDao;
import com.example.FlightBooking.model.Booking;
import com.example.FlightBooking.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class AppService {

    @Autowired
    private FlightDao flightDao;

    @Autowired
    private BookingDao bookingDao;

    public String addFlight(){
        flightDao.addFlight(new Flight("3","jaipur","banglore","delhi",5000,new Date(new java.util.Date().getTime())));
        flightDao.addFlight(new Flight("4","delhi","banglore","mumbai",6000,new Date(new java.util.Date().getTime())));
        flightDao.addFlight(new Flight("5","jaipur","delhi","",8000,new Date(new java.util.Date().getTime())));
        flightDao.addFlight(new Flight("6","jaipur","banglore","mumbai",7000,new Date(new java.util.Date().getTime())));
        return "true";
    }

    public List<Object> getFlightsBySourceDestinationAndDate(String source, String destination, Date date){
        List<Object> objects = null;
        try{
            objects = flightDao.getFlightsBySourceDestinationAndDate(source,destination,date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return objects;
    }

    public List<Booking> getBooking(String username){
        return bookingDao.getBooking(username);
    }

    public String bookFlightTickets(int flightId, String source, String destination, int seatCount, String username){
        Flight flight = flightDao.getFlightById(flightId);
        if(flight!=null && flight.getSeatAvailableCount()>=seatCount){
            flight.setSeatAvailableCount(flight.getSeatAvailableCount()-seatCount);
            flightDao.addFlight(flight);
            bookingDao.bookTicket(new Booking(flight.getDate(), source, destination,
                                    flight.getPrice()*seatCount, flight.getSourceTime(), flight.getDestinationTime(), seatCount, username));
            return "Done";
        }else{
            return "In this fight, there is no available seats";
        }
    }

    public List<Flight> getFlights(){
        return flightDao.getFlights();
    }
}
