package com.example.FlightBooking;

import com.example.FlightBooking.controller.AppController;
import com.example.FlightBooking.model.Flight;
import com.example.FlightBooking.service.AppService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.List;

@WebMvcTest(AppController.class)
public class ControllerTest {

    @MockBean
    private AppService appService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    private List<Flight> flightList = List.of(new Flight("1","jhodhpur","mumbai","delhi",5000,new Date(new java.util.Date().getTime())),
            new Flight("5","jaipur","delhi","",8000,new Date(new java.util.Date().getTime())));
    Flight flight = new Flight("8","jhodhpur","mumbai","delhi",5000,new Date(new java.util.Date().getTime()));

    @Test
    void Testing(){
        try {
            MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/bookTicket?flight_id=29&seatCount=1&source=jhodhpur&destination=delhi")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
            Assertions.assertThat(result).isNotNull();
            String userJson = result.getResponse().getContentAsString();
            Assertions.assertThat(userJson).isEqualToIgnoringCase("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
