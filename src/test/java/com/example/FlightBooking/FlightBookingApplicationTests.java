package com.example.FlightBooking;

import com.example.FlightBooking.model.Flight;
import com.example.FlightBooking.repository.FlightRepository;
import com.example.FlightBooking.service.AppService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FlightBookingApplicationTests {

	@MockBean
	private AppService appService;

	@MockBean
	private FlightRepository flightRepository;

	@Autowired
	private TestRestTemplate restTemplate;


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
	void bookTicket() throws MalformedURLException {

		String sourceLocation = "jhodhpur";
		String destinationLocation = "delhi";
		Date date = new Date(new java.util.Date().getTime());
		ResponseEntity<String> response = restTemplate.getForEntity(
				new URL("http://localhost:" + 8080 + "/test").toString(), String.class);
		Assertions.assertEquals("Done", response.getBody());

	}

//	@Test
//	void searchF(){
//		Mockito.when(flightRepository.findFlights(sour))
//	}

}
