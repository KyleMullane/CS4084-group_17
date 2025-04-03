package com.example.group17helloworld;

import java.util.ArrayList;

public class Trip {
    public String destination;
    public String dateDeparture;
    public String dateReturn;
    public double budget;
    //public ArrayList plans; //list of transports, accommodations, & activities, won't have plans in an arraylist, the tripID will be in each plan tho
    public Integer tripID;


    public Trip(){
        destination = "";
        dateDeparture = "";
        dateReturn = "";
        budget = 0.0;
        tripID = 0;
        //plans = new ArrayList<Object>();
    }

    public Trip(Integer tripID, String destination, String dateDeparture, String dateReturn, Double budget){
        this.destination = destination;
        this.dateDeparture = dateDeparture;
        this.dateReturn = dateReturn;
        this.budget = budget;
        this.tripID = tripID;
        //this.plans = plans;
    }

    // Getters

    public String getDestination() {
        return destination;
    }

    public String getDateDeparture() {
        return dateDeparture;
    }

    public String getDateReturn() {
        return dateReturn;
    }


    public double getBudget() {
        return budget;
    }

    public int getTripID() {
        return tripID;
    }

    // Setters

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDateDeparture(String dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

//    public void addAccommodation(Accommodation accommodation){
//        plans.add(accommodation);
//    }
//
//    public void addActivity(Activity activity){
//        plans.add(activity);
//    }
//
//    public void addTransportation(Transportation transportation){
//        plans.add(transportation);
//    }
//
//    public ArrayList<Object> getPlans(){
//        return plans;
//    }
}
