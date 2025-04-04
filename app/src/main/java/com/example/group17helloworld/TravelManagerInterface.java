package com.example.group17helloworld;

import java.util.ArrayList;

public interface TravelManagerInterface {
        void createTrip(String destination, String departureDate, String returnDate, Double budget);
        ArrayList<Trip> getUpcomingTrips(); //when you hit the button of upcoming trips
        ArrayList<Trip> getCurrentTrips(); //when you hit the button of current trips
        ArrayList<Trip> getPastTrips(); //when you hit the button of past trips
        Trip getTrip(Integer tripIndex);
        void createAccommodation(Integer tripIndex, String name, String address, String checkinDate, String checkoutDate, Double price);
        void createActivity(Integer tripIndex, String name, String location, String date, String time, Double price);   //do we need parameters?? (I think so based on user input), use another class to save it to database?? --> save method is called in these methods
        void createTransportation(Integer tripIndex, String departureLocation, String destination, String date, String departureTime, String arrivalTime, String type, Double price);
        ArrayList<Accommodation> accessTripAccommodations(Integer tripIndex);
        ArrayList<Activity> accessTripActivities(Integer tripIndex);  //these 3 methods are used when you press the button to go to the page for each category
        ArrayList<Transportation> accessTripTransportation(Integer tripIndex);
        Accommodation viewAccommodation(Integer trip, Integer accommodation);
        Activity viewActivity(Integer trip, Integer activity);    //we will have a class that deals with formatting the information accessed by these methods
        Transportation viewTransport(Integer trip, Integer transport);
        void cancelTrip(Integer trip);
        void cancelAccommodation(Integer trip, Integer accommodation);
        void cancelActivity(Integer trip, Integer activity);  //deletes these from the database
        void cancelTransport(Integer trip, Integer transport);
        void updateAccommodation(Integer tripIndex, Integer accommodationIndex, String name, String address, String checkinDate, String checkoutDate, Double price);
        void updateActivity(Integer tripIndex, Integer activityIndex, String name, String location, String date, String time, Double price);   //maybe need more specific methods depending on what details you want to update
        void updateTransport(Integer tripIndex, Integer transportIndex, String departureLocation, String destination, String date, String departureTime, String arrivalTime, String type, Double price);

    }
