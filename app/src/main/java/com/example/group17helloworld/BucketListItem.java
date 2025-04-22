package com.example.group17helloworld;

public class BucketListItem {
    public String item;
    public boolean completed;

    public BucketListItem(){
        item = "";
        completed = false;
    }

    public BucketListItem(String item, boolean completed){
        this.item = item;
        this.completed = completed;
    }

    public String getItem(){
        return item;
    }

    public boolean getStatus(){
        return completed;
    }

    public void setItem(String item){
        this.item = item;
    }

    public void setStatus(boolean completed){
        this.completed = completed;
    }

    //next step is implement this into database handler
}
