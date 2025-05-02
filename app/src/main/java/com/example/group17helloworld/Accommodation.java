package com.example.group17helloworld;

public class Accommodation implements Comparable<Accommodation> {
        public String name;
        public String type;
        public String address;
        public String checkinDate;
        public String checkoutDate;
        public double price;
        public int accommodationID;
        public int tripID;
        private DBHandler dbHandler;

        public Accommodation(){ //just never use this constructor
            name = "";
            type="";
            address = "";
            checkinDate = "";
            checkoutDate = "";
            price = 0.0;
            accommodationID = 0;
            tripID = 0;
        }

        public Accommodation(int accommodationID, String name, String type, String address, String checkinDate, String checkoutdate, double price, int tripID){
            this.accommodationID = accommodationID; //use DBHandler to set?
            this.name = name;
            this.type = type;
            this.address = address;
            this.checkinDate = checkinDate;
            this.checkoutDate = checkoutdate;
            this.price = price;
            this.tripID = tripID;
        }

    public Accommodation(String name, String type, String address, String checkinDate, String checkoutdate, double price, int tripID){
        this.accommodationID = 0;
        this.name = name;
        this.type = type;
        this.address = address;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutdate;
        this.price = price;
        this.tripID = tripID;
    }

    // Getters and Setters
    public int getAccommodationID() {
        return accommodationID;
    }

    public void setAccommodationID(int accommodationID) {
        this.accommodationID = accommodationID;
    }

    public String getType()
    {
        return this.type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    @Override
    public int compareTo(Accommodation other)
    {
        return this.checkinDate.compareTo(other.getCheckinDate());
    }


        /*public void setName(String name){
            this.name = name;
            dbHandler.changeAccommodationName(getAccommodationID(), name);
        }
        public String getName(){
            return name;
        }
        public void setPrice(Double price){
            this.price = price;
            dbHandler.changeAccommodationPrice(getAccommodationID(), price);
        }
        public Double getPrice(){
            return price;
        }
        public void setCheckInDate(String checkinDate){
            this.checkinDate = checkinDate;
            dbHandler.changeAccommodationCheckin(getAccommodationID(), checkinDate);
        }
        public String getCheckinDate(){
            return checkinDate;
        }
        public void setCheckoutDate(String checkoutDate){
            this.checkoutDate = checkoutDate;
            dbHandler.changeAccommodationCheckout(getAccommodationID(), checkoutDate);
        }
        public String getCheckoutDate(){
            return checkoutDate;
        }
        public void setAddress(String address){
            this.address = address;
            dbHandler.changeAccommodationAddress(getAccommodationID(), address);
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
            this.accommodationID = dbHandler.getAccommodationID(getTripID(), getCheckinDate(), getCheckoutDate()); //change to using the dbHandler to access the id somehow/set it upon creation
        }

        public Integer getAccommodationID(){
            return accommodationID;
        }*/
}
