package com.example.group17helloworld;

public class Transportation implements Comparable<Transportation> {

    public Integer transportationID;
    public String departureLocation;
    public String destination;
    public String date;
    public String departureTime;
    public String arrivalDate;
    public String arrivalTime;
    public String type;
    public double price;
    public int tripID;
    private DBHandler dbHandler;

    public Transportation(){
        transportationID = 0;
        departureLocation = "";
        destination = "";
        date = "";
        departureTime = "";
        arrivalDate = "";
        arrivalTime = "";
        type = "";
        price = 0.0;
        tripID = 0;
    }
    public Transportation(int transportationID, String departureLocation, String destination, String date, String departureTime, String arrivalDate, String arrivalTime, String type, double price, int tripID){
        this.transportationID = transportationID;
        this.departureLocation = departureLocation;
        this.destination = destination;
        this.date = date;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.type = type;
        this.price = price;
        this.tripID = tripID;
    }
    public Transportation(String departureLocation, String destination, String date, String departureTime, String arrivalDate, String arrivalTime, String type, double price, int tripID){
        //this.transportationID = transportationID;
        this.departureLocation = departureLocation;
        this.destination = destination;
        this.date = date;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.type = type;
        this.price = price;
        this.tripID = tripID;
    }
    public int getTransportationID() {
        return transportationID;
    }

    public void setTransportationID(int transportationID) {
        this.transportationID = transportationID;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
    public void setArrivalDate(String arrivalDate) {this.arrivalDate = arrivalDate;}
    public String getArrivalDate() {return this.arrivalDate;}
    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }
    @Override
    public int compareTo(Transportation other)
    {
        return this.departureTime.compareTo(other.getDepartureTime());
    }

    /*public void setTransportationID(Integer transportationID){
        this.transportationID = dbHandler.getTransportationID(getTripID(), getDate(), getDepartureTime()); //change to using dbHandler
    }
    public Integer getTransportationID() {
        return transportationID; //change to using dbHandler
    }
    public void setPrice(Double price){
        this.price = price;
        dbHandler.changeTransportationPrice(getTransportationID(), price);
    }
    public Double getPrice(){
        return price;
    }
    public void setDepartureLocation(String departureLocation){
        this.departureLocation = departureLocation;
        dbHandler.changeTransportationDepartureLocation(getTransportationID(), departureLocation);
    }
    public String getDepartureLocation(){
        return departureLocation;
    }
    public void setDestination(String destination){
        this.destination = destination;
        dbHandler.changeTransportationDestination(getTransportationID(), destination);
    }
    public String getDestination(){
        return destination;
    }
    public void setDate(String date){
        this.date = date;
        dbHandler.changeTransportationDate(getTransportationID(), date);
    }
    public String getDate(){
        return date;
    }
    public void setDepartureTime(String departureTime){
        this.departureTime = departureTime;
        dbHandler.changeTransportationDepartureTime(getTransportationID(), departureTime);
    }
    public String getDepartureTime() {
        return departureTime;
    }
    public void setArrivalTime(String arrivalTime){
        this.arrivalTime = arrivalTime;
        dbHandler.changeTransportationArrivalTime(getTransportationID(), arrivalTime);
    }
    public String getArrivalTime() {
        return arrivalTime;
    }
    public void setType(String type){
        this.type = type;
        dbHandler.changeTransportationType(getTransportationID(), type);
    }
    public String getType() {
        return type;
    }
    public void setTripID(Integer tripID){
        this.tripID = tripID;
    }
    public Integer getTripID() {
        return tripID;
    }*/
}
