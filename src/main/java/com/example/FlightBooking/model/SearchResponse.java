package com.example.FlightBooking.model;

import java.util.List;

public class SearchResponse {

    private List<Flight> flights;

    public SearchResponse() {
    }

    public SearchResponse(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
