package com.example.FlightBooking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Booking {

    @Id
    @GeneratedValue
    private int id;
    private Date bookingDate;
    private String fromLocation;
    private String toLocation;
    private int price;
    private Time sourceTime;
    private Time destinationTime;
    private String currentTimeStamp;
    private int personCount;
    private int flightId;
    private String username;
    private String status;

    public Booking() {
    }

    public Booking(Date bookingDate, String fromLocation, String toLocation, int price, Time sourceTime, Time destinationTime, int personCount, int flightId, String username) {
        this.bookingDate = bookingDate;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.price = price;
        this.sourceTime = sourceTime;
        this.destinationTime = destinationTime;
        this.personCount = personCount;
        this.username = username;
        this.flightId = flightId;
        this.currentTimeStamp = new java.util.Date().toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Time getSourceTime() {
        return sourceTime;
    }

    public void setSourceTime(Time sourceTime) {
        this.sourceTime = sourceTime;
    }

    public Time getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(Time destinationTime) {
        this.destinationTime = destinationTime;
    }

    public String getCurrentTimeStamp() {
        return currentTimeStamp;
    }

    public void setCurrentTimeStamp(String currentTimeStamp) {
        this.currentTimeStamp = currentTimeStamp;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
