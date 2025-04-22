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

    public boolean getStatus(){
        if (completed == 1){
            return true;
        }
        else{
            return false;
        }
    }

    public void setItem(String item){
        this.item = item;
    }

    public void setStatus(Integer completed){
        this.completed = completed;
    }

    //next step is implement this into database handler
}
