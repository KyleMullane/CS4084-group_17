package com.example.group17helloworld;

public class BucketListItem {
    public String item;
    public Integer completed;

    public BucketListItem(){
        item = "";
        completed = 0;
    }

    public BucketListItem(String item, Integer completed){
        this.item = item;
        this.completed = completed;
    }

    public String getItem(){
        return item;
    }

    public Integer getStatus(){
        return completed;
    }

    public void setItem(String item){
        this.item = item;
    }

    public void setStatus(Integer completed){
        this.completed = completed;
    }

    //next step is implement this into database handler
}
