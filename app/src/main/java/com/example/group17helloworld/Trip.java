package com.example.group17helloworld;

import java.util.ArrayList;

public class Trip implements Comparable<Trip> {
    public String departure;
    public String destination;
    public String dateDeparture;
    public String dateReturn;
    public double budget;
    public Integer tripID;
    public ArrayList<Accommodation> accommodations;
    public ArrayList<Activity> activities;
    public ArrayList<Transportation> transportation;
    private DBHandler dbHandler;

    public Integer isFavorite;


    public Trip(){
        departure = "";
        destination = "";
        dateDeparture = "";
        dateReturn = "";
        budget = 0.0;
        tripID = 0;
        isFavorite = 0;
    }

    public Trip(int tripID, String departure, String destination, String dateDeparture, String dateReturn, Double budget, Integer isFavorite){
        this.tripID = tripID; //get rid of because this is auto-increment
        this.departure = departure;
        this.destination = destination;
        this.dateDeparture = dateDeparture;
        this.dateReturn = dateReturn;
        this.budget = budget;
        this.isFavorite = isFavorite;
    }

    public Trip(String departure, String destination, String dateDeparture, String dateReturn, Double budget, Integer isFavorite){
        tripID = 0;
        this.departure = departure;
        this.destination = destination;
        this.dateDeparture = dateDeparture;
        this.dateReturn = dateReturn;
        this.budget = budget;
        this.isFavorite = isFavorite;
    }

    // Getters ..

    public String getDeparture() { return departure; }
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

    public boolean getIsFavorite(){
        if (isFavorite == 1){
            return true;
        }
        else{
            return false;
        }
    }

    // Setters

    /*public void setTripID(){
        tripID = dbHandler.getTripID(getDateDeparture(), getDateReturn());
    }*/
    public void setDeparture(String departure) { this.departure = departure; }
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

    public void setFavoriteStatus(int isFavorite){
        this.isFavorite = isFavorite;
    }

    @Override
    public int compareTo(Trip other)
    {
        return this.dateDeparture.compareTo(other.dateDeparture);
    }

    /*public void addAccommodation(Accommodation accommodation){
        accommodations.add(accommodation);
    }

    public void addActivity(Activity activity){
        activities.add(activity);
    }

    public void addTransportation(Transportation transportation){
        this.transportation.add(transportation);
    }

    public ArrayList<Accommodation> getAccommodations(){
        return accommodations;
    }
    public ArrayList<Activity> getActivities(){
        return activities;
    }
    public ArrayList<Transportation> getTransportation(){
        return transportation;
    }
    public Accommodation getAccommodation(Integer accommodationIndex){
        return getAccommodations().get(accommodationIndex);
    }
    public Activity getActivity(Integer activityIndex){
        return getActivities().get(activityIndex);
    }
    public Transportation getTransport(Integer transportIndex){
        return getTransportation().get(transportIndex);
    }
    public void cancelAccommodation(Integer accommodationIndex){
        accommodations.remove(accommodationIndex); //also has to call DBHandler to delete from database
        Integer accommodationID = accommodations.get(accommodationIndex).getAccommodationID();
        dbHandler.deleteAccommodation(accommodationID);
    }
    public void cancelActivity(Integer activityIndex){
        accommodations.remove(activityIndex);
        Integer activityID = activities.get(activityIndex).getActivityID();
        dbHandler.deleteActivity(activityID);
    }
    public void cancelTransport(Integer transportIndex){
        transportation.remove(transportIndex);
        Integer transportID = transportation.get(transportIndex).getTransportationID();
        dbHandler.deleteTransport(transportID);
    }*/
}
