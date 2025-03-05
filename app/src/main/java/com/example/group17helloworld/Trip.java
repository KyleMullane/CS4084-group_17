package com.example.group17helloworld;

import java.util.ArrayList;

public class Trip {
    public String name;
    //public enum type{upcoming, current, past;};
    public String type;
    public String date;
    public ArrayList<Accommodation> accommodations;
    public ArrayList<Activity> activities;  //i'm wondering if these 3 are unecessary if we use the array list of these lists
    public ArrayList<Flight> flights;
    public Double cost;
    public ArrayList<Object> tripInfo; //array list with each of the sub lists inside

    public Trip(){
        name = "";
        type = "upcoming";
        date = "";
        accommodations = new ArrayList<Accommodation>();
        activities = new ArrayList<Activity>();
        flights = new ArrayList<Flight>();
        cost = 0.0;
        tripInfo = new ArrayList<>();
    }

    public Trip(String name, String type, String date, ArrayList<Accommodation> accommodations, ArrayList<Activity> activities, ArrayList<Flight> flights, Double cost){
        this.name = name;
        this.type = type;
        this.date = date;
        this.accommodations = accommodations;
        this.activities = activities;
        this.flights = flights;
        this.cost = cost;
        this.tripInfo = new ArrayList<Object>();
        tripInfo.add(accommodations);
        tripInfo.add(activities);
        tripInfo.add(flights);
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return type;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return date;
    }
    public void addAccommodation(Accommodation accommodation){
        accommodations.add(accommodation);
    }
    public ArrayList<Accommodation> getAccommodations(){
        return accommodations;
    }
    public Accommodation getAccommodation(int index){
        return accommodations.get(index); //check!!
    }
    public void addActivity(Activity activity){
        activities.add(activity);
    }
    public ArrayList<Activity> getActivities(){
        return activities;
    }
    public Activity getActivity(int index){
        return activities.get(index);
    }
    public void addFlight(Flight flight){
        flights.add(flight);
    }
    public ArrayList<Flight> getFlights(){
        return flights;
    }
    public Flight getFlight(int index){
        return flights.get(index);
    }
    public void setCost(Double cost){
        this.cost = cost;
    } //can set by getting cost from activity, accommodation, & flight objects??
    public Double getCost(){
        return cost;
    }
    public void calculateCost(){
        Double total = 0.0;
        for (Accommodation accommodation : accommodations){
            total += accommodation.getPrice();
        }
        for (Activity activity : activities){
            total += activity.getPrice();
        }
        for (Flight flight : flights){
            total += flight.getPrice();
        }
        this.cost = total;
    }

    public void addTripInfo(ArrayList<Object> info){
        tripInfo.add(info);
    }
    public ArrayList<Object> getTripInfo(){
        return tripInfo;
    }


    //add a save trip method??
}
