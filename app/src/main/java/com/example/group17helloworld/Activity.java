package com.example.group17helloworld;

public class Activity {
    public String name;
    public Double price;
    public String description;
    public String date;
    public Activity(){
        name = "";
        price = 0.0;
        description = "";
        date = "";
    }
    public Activity(String name, Double price, String description, String date){
        this.name = name;
        this.price = price;
        this.description = description;
        this.date = date;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setPrice(Double price){
        this.price = price;
    }
    public Double getPrice(){
        return price;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return date;
    }
}
