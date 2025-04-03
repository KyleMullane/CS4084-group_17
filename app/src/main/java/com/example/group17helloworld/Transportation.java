package com.example.group17helloworld;

public class Transportation {

    public Integer transportationID;
    public String departureLocation;
    public String destination;
    public String date;
    public String departureTime;
    public String arrivalTime;
    public String type;
    public Double price;
    public Integer tripID;

    public Transportation(){
        transportationID = 0;
        departureLocation = "";
        destination = "";
        date = "";
        departureTime = "";
        arrivalTime = "";
        type = "";
        price = 0.0;
        tripID = 0;
    }
    public Transportation(Integer transportationID, String departureLocation, String destination, String date, String departureTime, String arrivalTime, String type, Double price, Integer tripID){
        this.transportationID = transportationID;
        this.departureLocation = departureLocation;
        this.destination = destination;
        this.date = date;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.type = type;
        this.price = price;
        this.tripID = tripID;
    }
    public void setTransportationID(Integer transportationID){
        this.transportationID = transportationID;
    }
    public Integer getTransportationID() {
        return transportationID;
    }
    public void setPrice(Double price){
        this.price = price;
    }
    public Double getPrice(){
        return price;
    }
    public void setDepartureLocation(String departureLocation){
        this.departureLocation = departureLocation;
    }
    public String getDepartureLocation(){
        return departureLocation;
    }
    public void setDestination(String destination){
        this.destination = destination;
    }
    public String getDestination(){
        return destination;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return date;
    }
    public void setDepartureTime(String departureTime){
        this.departureTime = departureTime;
    }
    public String getDepartureTime() {
        return departureTime;
    }
    public void setArrivalTime(String arrivalTime){
        this.arrivalTime = arrivalTime;
    }
    public String getArrivalTime() {
        return arrivalTime;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public void setTripID(Integer tripID){
        this.tripID = tripID;
    }
    public Integer getTripID() {
        return tripID;
    }
}
