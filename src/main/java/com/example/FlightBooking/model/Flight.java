package com.example.FlightBooking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Flight {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "flight_name")
    private String flightName;
    @Column(name = "from_location")
    private String fromLocation;
    @Column(name = "to_location")
    private String toLocation;
    @Column(name = "connecting_locations")
    private String connectingLocations;
    @Column(name = "date")
    private Date date;
    @Column(name = "price")
    private int price;
    @Column(name = "source_time")
    private Time sourceTime;
    @Column(name = "destination_time")
    private Time destinationTime;
    @Column(name = "current_time_stamp")
    private String currentTimeStamp;
    @Column(name = "seat_available_count")
    private int seatAvailableCount;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "flight_id", referencedColumnName = "id")
//    private FlightSeat flightSeat;

    public Flight() {
    }

    public Flight(String flightName, String fromLocation, String toLocation, String connectingLocations,int price, Date date) {
        this.id=0;
        this.flightName = flightName;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.connectingLocations = connectingLocations;
        this.date = date;
        this.price = price;
        this.sourceTime = Time.valueOf("20:03:10");
        this.destinationTime = Time.valueOf("23:03:10");
        this.currentTimeStamp = new java.util.Date().toString();
        this.seatAvailableCount = 10;
//        this.flightSeat = new FlightSeat();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
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

    public String getConnectingLocations() {
        return connectingLocations;
    }

    public void setConnectingLocations(String connectingLocations) {
        this.connectingLocations = connectingLocations;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public int getSeatAvailableCount() {
        return seatAvailableCount;
    }

    public void setSeatAvailableCount(int seatAvailableCount) {
        this.seatAvailableCount = seatAvailableCount;
    }

    //    public FlightSeat getFlightSeat() {
//        return flightSeat;
//    }
//
//    public void setFlightSeat(FlightSeat flightSeat) {
//        this.flightSeat = flightSeat;
//    }
}
