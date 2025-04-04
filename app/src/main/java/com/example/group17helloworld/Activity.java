package com.example.group17helloworld;

public class Activity {
    public Integer activityID;
    public String name;
    public String location;
    public String date;
    public String time;
    public Double price;
    public Integer tripID;
    private DBHandler dbHandler;

    public Activity(){
        activityID = 0;
        name = "";
        location = "";
        date = "";
        time = "";
        price = 0.0;
        tripID = 0;
    }
    public Activity(Integer activityID, String name, String location, String date, String time, Double price, Integer tripID){
        this.activityID = activityID;
        this.name = name;
        this.location = location;
        this.date = date;
        this.time = time;
        this.price = price;
        this.tripID = tripID;
    }
    /*public void setName(String name){
        this.name = name;
        dbHandler.changeActivityName(getActivityID(), name);
    }
    public String getName(){
        return name;
    }
    public void setPrice(Double price){
        this.price = price;
        dbHandler.changeActivityPrice(getActivityID(), price);
    }
    public Double getPrice(){
        return price;
    }
    public void setActivityID(Integer activityID){
        this.activityID = dbHandler.getActivityID(getTripID(), getDate(), getTime()); //change to dbHandler accessing the activity ID ??
    }
    public Integer getActivityID(){
        return activityID;
    }
    public void setLocation(String location){
        this.location = location;
        dbHandler.changeActivityLocation(getActivityID(), location);
    }
    public String getLocation(){
        return location;
    }
    public void setTime(String time){
        this.time = time;
        dbHandler.changeActivityTime(getActivityID(), time);
    }
    public String getTime(){
        return time;
    }
    public void setTripID(Integer tripID){
        this.tripID = tripID;
    }
    public Integer getTripID(){
        return tripID;
    }
    public void setDate(String date){
        this.date = date;
        dbHandler.changeActivityDate(getActivityID(), date);
    }
    public String getDate(){
        return date;
    }*/

}
