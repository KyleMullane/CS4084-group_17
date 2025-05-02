package com.example.group17helloworld;

public class Activity implements Comparable<Activity> {
    public int activityID;
    public String name;
    public String location;
    public String date;
    public String time;
    public double price;
    public String description;
    public int tripID;
    private DBHandler dbHandler;

    public Activity(){
        activityID = 0;
        name = "";
        location = "";
        date = "";
        time = "";
        price = 0.0;
        description = "";
        tripID = 0;
    }
    public Activity(int activityID, String name, String location, String date, String time, double price, String description, int tripID){
        this.activityID = activityID;
        this.name = name;
        this.location = location;
        this.date = date;
        this.time = time;
        this.price = price;
        this.description = description;
        this.tripID = tripID;
    }

    public Activity(String name, String location, String date, String time, double price, String description, int tripID){
        this.activityID = 0;
        this.name = name;
        this.location = location;
        this.date = date;
        this.time = time;
        this.price = price;
        this.description = description;
        this.tripID = tripID;
    }

    // Getters and Setters
    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }
    @Override
    public int compareTo(Activity other)
    {
        return this.time.compareTo(other.getTime());
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
