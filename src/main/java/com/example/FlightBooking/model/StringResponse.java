package com.example.FlightBooking.model;

public class StringResponse {

    private String message;

    public StringResponse() {
    }
    public StringResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
