package com.example.group17helloworld;

public class User {

    public String username;
    public String password;
    public User()
    {
        this.username = "";
        this.password = "";
    }

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
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


}
