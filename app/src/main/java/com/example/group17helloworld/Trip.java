package com.example.group17helloworld;

public class Trip {
    public String departureLocation;
    public String destinationLocation;
    public String dateDeparture;
    public String dateReturn;
    public String username;
    public String description;
    public double overallCost;
    public int tripID;


    public Trip(){
        departureLocation = "";
        destinationLocation = "";
        dateDeparture = "";
        dateReturn = "";
        username = "";
        description = "";
        overallCost = 0.0;
        tripID = 0;

    }

    public Trip(String departureLocation, String destinationLocation, String dateDeparture, String dateReturn, String username, String description, double overallCost, int tripID){
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
        this.dateDeparture = dateDeparture;
        this.dateReturn = dateReturn;
        this.username = username;
        this.description = description;
        this.overallCost = overallCost;
        this.tripID = tripID;
    }

    // Getters
    public String getDepartureLocation() {
        return departureLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public String getDateDeparture() {
        return dateDeparture;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return this.description;
    }

    public double getOverallCost() {
        return overallCost;
    }

    public int getTripID() {
        return tripID;
    }

    // Setters
    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public void setDateDeparture(String dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOverallCost(double overallCost) {
        this.overallCost = overallCost;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }


    //add a save trip method??
}
