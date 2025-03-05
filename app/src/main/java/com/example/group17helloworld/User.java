package com.example.group17helloworld;

import java.util.ArrayList;

public class User {
    public String firstName;
    public String lastName;
    public String username;
    public String password;
    public ArrayList<Trip> trips;
    public User(){
        firstName = "";
        lastName = "";
        username = "";
        password = "";
        trips = new ArrayList<Trip>();
    }

    public User(String firstName, String lastName, String username, String password, ArrayList<Trip> trips){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.trips = trips;
    }

    public void setFirstName(String name){
        firstName = name;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setLastName(String name){
        lastName = name;
    }
    public String getLastName(){
        return lastName;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
    public void setPassword(String pass){
        password = pass;
    }
    public String getPassword(){
        return password;
    }

    public void addTrip(Trip trip){
        trips.add(trip);
    }
    public ArrayList<Trip> getTrips(){
        return trips;
    }
    public Trip getTrip(int index){ //maybe change to a for loop that searches for the trips name
        return trips.get(index);
    }
    //might need a method to get preexisting user info??
    public ArrayList<Object> getUserInfo(){
        //finish
    }
}
