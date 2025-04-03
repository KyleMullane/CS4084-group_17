package com.example.group17helloworld;

public class Accommodation {
        public String name;
        public String address;
        public String checkinDate;
        public String checkoutDate;
        public Double price;
        public Integer accommodationID;
        public Integer tripID;

        public Accommodation(){ //just never use this constructor
            name = "";
            address = "";
            checkinDate = "";
            checkoutDate = "";
            price = 0.0;
            accommodationID = 0;
            tripID = 0;
        }

        public Accommodation(Integer accommodationID, String name, String address, String checkinDate, String checkoutdate, Double price, Integer tripID){
            this.accommodationID = accommodationID;
            this.name = name;
            this.address = address;
            this.checkinDate = checkinDate;
            this.checkoutDate = checkoutdate;
            this.price = price;
            this.tripID = tripID;
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
        public void setCheckInDate(String checkinDate){
            this.checkinDate = checkinDate;
        }
        public String getCheckinDate(){
            return checkinDate;
        }
        public void setCheckoutDate(String checkoutDate){
            this.checkoutDate = checkoutDate;
        }
        public String getCheckoutDate(){
            return checkoutDate;
        }
        public void setAddress(String address){
            this.address = address;
        }
        public String getAddress(){
            return address;
        }

        public void setTripID(Integer tripID){
            this.tripID = tripID;
        }

        public Integer getTripID(){
            return tripID;
        }

        public void setAccommodationID(Integer accommodationID){
            this.accommodationID = accommodationID;
        }

        public Integer getAccommodationID(){
            return accommodationID;
        }
    }
