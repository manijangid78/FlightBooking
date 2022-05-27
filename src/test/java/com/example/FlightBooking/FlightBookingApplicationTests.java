package com.example.FlightBooking;

import com.example.FlightBooking.controller.AppController;
import com.example.FlightBooking.model.Booking;
import com.example.FlightBooking.model.Flight;
import com.example.FlightBooking.repository.BookingRepository;
import com.example.FlightBooking.repository.FlightRepository;
import com.example.FlightBooking.service.AppService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
//@WebMvcTest(AppController.class)
class FlightBookingApplicationTests {

	@MockBean
	private AppService appService;

//	@Autowired
//	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private FlightRepository flightRepository;

	@MockBean
	private BookingRepository bookingRepository;

	private List<Flight> flightList = List.of(new Flight("1","jhodhpur","mumbai","delhi",5000,new Date(new java.util.Date().getTime())),
			new Flight("5","jaipur","delhi","",8000,new Date(new java.util.Date().getTime())));
	Flight flight = new Flight("8","jhodhpur","mumbai","delhi",5000,new Date(new java.util.Date().getTime()));


	@Test
	void contextLoads() {
	}

	@Test
	void searchFlights(){
		String sourceLocation = "jhodhpur";
		String destinationLocation = "delhi";
		Date date = new Date(new java.util.Date().getTime());
		List<Object> objectList = new ArrayList<>();
		objectList.add(new Flight("1","jhodhpur","banglore","delhi, mumbai",5000,new Date(new java.util.Date().getTime())));
		objectList.add(new Flight("1","jhodhpur","mumbai","delhi",5000,new Date(new java.util.Date().getTime())));
		Mockito.when(flightRepository.findFlights(sourceLocation,destinationLocation,date)).thenReturn(objectList);
		Assertions.assertEquals(2, flightRepository.findFlights(sourceLocation,destinationLocation, date).size());
	}

	@Test
	void bookTicket(){
		String sourceLocation = "jhodhpur";
		String destinationLocation = "delhi";
		Booking newBooking = new Booking(new Date(new java.util.Date().getTime()), sourceLocation, destinationLocation, 2000, Time.valueOf("20:03:10"), Time.valueOf("20:03:10"),1, "mani");
		Mockito.when(bookingRepository.save(newBooking)).thenReturn(newBooking);
		Assertions.assertEquals(sourceLocation,newBooking.getFromLocation());
	}

	@Test
	void getAllFLights(){
		Mockito.when(flightRepository.findAll()).thenReturn(flightList);
		Assertions.assertEquals(2,flightRepository.findAll().size());
	}

//	@Test
//	void Testing(){
//		try {
//			MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/bookTicket?flight_id=29&seatCount=11&source=jhodhpur&destination=delhi")
//					.contentType(MediaType.APPLICATION_JSON)
//					.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
//					.andReturn();
//			org.assertj.core.api.Assertions.assertThat(result).isNotNull();
//			String userJson = result.getResponse().getContentAsString();
//			org.assertj.core.api.Assertions.assertThat(userJson).isEqualToIgnoringCase("");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	@Test
//	void bookTicket12() throws MalformedURLException {
//		String sourceLocation = "jhodhpur";
//		String destinationLocation = "delhi";
//		Date date = new Date(new java.util.Date().getTime());

//        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/bookTicket?flight_id=29&seatCount=11&source=jhodhpur&destination=delhi")
//                .contentType(MediaType.APPLICATION_JSON)
//
//                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        org.junit.jupiter.api.Assertions.assertEquals("Done", response.getBody());
//	}

//	@Test
//	void searchF(){
//		Mockito.when(flightRepository.findFlights(sour))
//	}

}
