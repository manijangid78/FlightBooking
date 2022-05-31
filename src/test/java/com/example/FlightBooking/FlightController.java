package com.example.FlightBooking;

import com.example.FlightBooking.constants.Constant;
import com.example.FlightBooking.controller.AppController;
import com.example.FlightBooking.model.Booking;
import com.example.FlightBooking.model.Flight;
import com.example.FlightBooking.model.SearchBody;
import com.example.FlightBooking.model.SearchResponse;
import com.example.FlightBooking.repository.BookingRepository;
import com.example.FlightBooking.repository.FlightRepository;
import com.example.FlightBooking.service.AppService;
import com.example.FlightBooking.service.MyUserDetailsService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.awt.print.Book;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith()
//@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AppController.class)
public class FlightController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppService appService;

    @MockBean
    private MyUserDetailsService myUserDetailsService;

    private List<Flight> flightList = List.of(new Flight("1","jhodhpur","mumbai","delhi",5000,new Date(new java.util.Date().getTime())),
            new Flight("5","jaipur","delhi","",8000,new Date(new java.util.Date().getTime())));
    private Flight flight = new Flight("8","jhodhpur","mumbai","delhi",5000,new Date(new java.util.Date().getTime()));
    private SearchResponse searchResponse = new SearchResponse(new ArrayList<>(Arrays.asList(flight)));
    private Booking booking = new Booking(Date.valueOf("2022-05-30"), "jhodpur","delhi",8000,Time.valueOf("20:03:10"),Time.valueOf("23:03:10"),2,0,"mani");
    private Object SearchBody;
//    private Object Booking;

    @Test
    public void searchFlight() throws Exception {
        Date date = Date.valueOf("2022-05-23");
        Mockito.when(appService.getFlightsBySourceDestinationAndDate(new SearchBody("jhodpur","mumbai",date))).thenReturn(searchResponse);
        String token = "mani:mani";
        mockMvc.perform(MockMvcRequestBuilders.get("/search").contentType(
                        MediaType.APPLICATION_JSON).content("{\n" +
                "\t\"source\": \"jhodpur\",\n" +
                "\t\"destination\": \"mumbai\",\n" +
                "\t\"date\": \"2022-05-23\"\n" +
                "}")).andExpect(
                status().isOk());
    }

//    @Test
//    public void bookTickets() throws Exception {
//        Mockito.when(appService.bookFlightTickets(Mockito.any(), Mockito.anyString())).thenReturn(Constant.BookingDone);
//        mockMvc.perform(MockMvcRequestBuilders.post("/bookTicket").contentType(
//                MediaType.APPLICATION_JSON).content("{\n" +
//                "    \"flightId\":36,\n" +
//                "    \"person\":3,\n" +
//                "    \"source\":\"jaipur\",\n" +
//                "    \"destination\":\"mumbai\"\n" +
//                "}")).andExpect(status().isOk());
//    }

    @Test
    public void getAllFLights() throws Exception {
        Mockito.when(appService.getFlights()).thenReturn(new SearchResponse(flightList));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/getAllFlights")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flights[0].flightName", is("1")))
                .andExpect(jsonPath("$.flights[0].fromLocation", is("jhodhpur")));
    }

    @Test
    public void cancelBooking() throws Exception {
        Mockito.when(appService.cancelFlight(Mockito.anyInt())).thenReturn(Constant.CancelMsg);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/cancelBooking")
                .param("ticketId","43")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> Assertions.assertEquals(Constant.CancelMsg,result.getResponse().getContentAsString()));
    }




}
