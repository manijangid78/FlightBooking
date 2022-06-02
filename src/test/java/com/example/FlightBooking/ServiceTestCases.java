package com.example.FlightBooking;

import com.example.FlightBooking.constants.Constant;
import com.example.FlightBooking.dao.BookingDao;
import com.example.FlightBooking.dao.FlightDao;
import com.example.FlightBooking.model.*;
import com.example.FlightBooking.repository.BookingRepository;
import com.example.FlightBooking.repository.FlightRepository;
import com.example.FlightBooking.service.AppService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ServiceTestCases {

    @InjectMocks
    private AppService appService;

    @Mock
    private BookingDao bookingDao;

    @Mock
    private FlightDao flightDao;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private BookingRepository bookingRepository;

    private List<Flight> flightList = List.of(new Flight("1","jhodhpur","mumbai","delhi",5000,new Date(new java.util.Date().getTime())),
            new Flight("5","jaipur","delhi","",8000,new Date(new java.util.Date().getTime())));
    private Flight flight = new Flight("8","jhodhpur","mumbai","delhi",5000,new Date(new java.util.Date().getTime()));
    private SearchResponse searchResponse = new SearchResponse(new ArrayList<>(Arrays.asList(flight)));
    private Booking booking = new Booking(Date.valueOf("2022-05-30"), "jhodpur","delhi",8000, Time.valueOf("20:03:10"),Time.valueOf("23:03:10"),2,0,"mani");
    private List<Booking> bookingList = List.of(
            new Booking(Date.valueOf("2022-05-30"), "jhodpur","delhi",8000,Time.valueOf("20:03:10"),Time.valueOf("23:03:10"),2,4,"mani"),
            new Booking(Date.valueOf("2022-05-30"), "jaipur","delhi",8000,Time.valueOf("20:03:10"),Time.valueOf("23:03:10"),2,3,"mani")
    );

    @Test
    void contextLoads() {
    }

    @Test
    public void searchTest(){
        Mockito.when(flightDao.getFlightsBySourceDestinationAndDate(Mockito.anyString(), Mockito.anyString(), Mockito.any())).thenReturn(flightList);
        SearchBody searchBody = new SearchBody("jodhpur","delhi",Date.valueOf("2022-05-23"));
        Assertions.assertEquals("1",appService.getFlightsBySourceDestinationAndDate(searchBody).getFlights().get(0).getFlightName());
    }

    @Test
    public void cancelBookingService(){
        Mockito.when(bookingDao.getBookingById(Mockito.anyInt())).thenReturn(java.util.Optional.ofNullable(booking));
        Mockito.when(flightDao.getFlightById(Mockito.anyInt())).thenReturn(java.util.Optional.ofNullable(flight));
        Assertions.assertEquals(Constant.CancelMsg,appService.cancelFlight(44));
    }

    @Test
    public void getAllFlightsTest(){
        Mockito.when(flightDao.getFlights()).thenReturn(flightList);
        Assertions.assertEquals("1",appService.getFlights().getFlights().get(0).getFlightName());
    }

    @Test
    public void getBookingTest(){
        Mockito.when(bookingDao.getBooking(Mockito.anyString())).thenReturn(bookingList);
        Assertions.assertEquals("jhodpur",appService.getBooking("mani").getBookings().get(0).getFromLocation());
    }

    @Test
    public void bookTicketTest(){
        Mockito.when(bookingDao.bookTicket(booking)).thenReturn(booking);
        Mockito.when(flightDao.getFlightById(Mockito.anyInt())).thenReturn(java.util.Optional.ofNullable(flight));
        BookTicketBody bookTicketBody = new BookTicketBody(2,"jodhpur","delhi",2);
        Assertions.assertEquals(Constant.BookingDone,appService.bookFlightTickets(bookTicketBody,"mani"));
    }

}
