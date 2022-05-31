package com.example.FlightBooking.model;

public class BookTicketBody {

    private int flightId;
    private String source;
    private String destination;
    private int person;

    public BookTicketBody() {
    }

    public BookTicketBody(int flightId, String source, String destination, int person) {
        this.flightId = flightId;
        this.source = source;
        this.destination = destination;
        this.person = person;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }
}
