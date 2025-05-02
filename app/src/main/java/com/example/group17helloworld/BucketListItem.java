package com.example.group17helloworld;

import android.util.Log;

public class BucketListItem {
    public String item;
    public Integer completed;
    private int ID;

    public BucketListItem(){
        item = "";
        completed = 0;
    }

    public BucketListItem(String item, Integer completed){
        this.item = item;
        this.completed = completed;
    }

    public BucketListItem(int ID, String item, int completed)
    {
        this.item = item;
        this.completed = completed;
        this.ID = ID;
    }

    public String getItem(){
        return item;
    }

    public int getID()
    {
        return this.ID;
    }


    public void setID(int ID)
    {
        this.ID = ID;
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
        Log.d("BucketListItem","setStatus just set completed to "+completed+" for the item "+this.item+" which has ID "+this.ID);
        this.completed = completed;
    }

    //next step is implement this into database handler
}
