package com.example.group17helloworld;

import java.util.ArrayList;

/*public class TravelManager implements TravelManagerInterface{
    public ArrayList<Trip> trips;
    public void createTrip(String destination, String departureDate, String returnDate, Double budget){
        Trip trip = new Trip();
        trip.setDestination(destination);
        trip.setDateDeparture(departureDate);
        trip.setDateReturn(returnDate);
        trip.setBudget(budget);
        trips.add(trip);
    }
    public ArrayList<Trip> getUpcomingTrips() {
        ArrayList<Trip> upcoming = new ArrayList<Trip>();
        for (Trip trip : trips) {
            if (trip.getStatus().equals("upcoming")) {
                upcoming.add(trip);
            }
        }
        return upcoming;
    }//when you hit the button of upcoming trips
    public ArrayList<Trip> getCurrentTrips(){
        ArrayList<Trip> current = new ArrayList<Trip>();
        for (Trip trip : trips) {
            if (trip.getStatus().equals("current")) {  //change all characters to lowercase
                current.add(trip);
            }
        }
        return current;
    } //when you hit the button of current trips
    public ArrayList<Trip> getPastTrips(){
        ArrayList<Trip> past = new ArrayList<Trip>();
        for (Trip trip : trips) {
            if (trip.getStatus().equals("past")) {  //change all characters to lowercase
                past.add(trip);
            }
        }
        return past;
    } //when you hit the button of past trips

    public Trip getTrip(Integer tripIndex){
        return trips.get(tripIndex);
    } //for selecting a chosen trip on the main screen

    public void createAccommodation(Integer tripIndex, String name, String address, String checkinDate, String checkoutDate, Double price){
        Accommodation accommodation = new Accommodation();
        accommodation.setName(name);
        accommodation.setAddress(address);
        accommodation.setCheckInDate(checkinDate);
        accommodation.setCheckoutDate(checkoutDate);
        accommodation.setPrice(price);
        accommodation.setTripID(getTrip(tripIndex).getTripID());
        getTrip(tripIndex).addAccommodation(accommodation);
        //figure out how to set accommodationID in the object using DBHandler (not here)
    }
    public void createActivity(Integer tripIndex, String name, String location, String date, String time, Double price){
        Activity activity = new Activity();
        activity.setName(name);
        activity.setLocation(location);
        activity.setDate(date);
        activity.setTime(time);
        activity.setPrice(price);
        activity.setTripID(getTrip(tripIndex).getTripID());
        getTrip(tripIndex).addActivity(activity);
    }
    public void createTransportation(Integer tripIndex, String departureLocation, String destination, String date, String departureTime, String arrivalTime, String type, Double price){
        Transportation transport = new Transportation();
        transport.setDepartureLocation(departureLocation);
        transport.setDestination(destination);
        transport.setDate(date);
        transport.setDepartureTime(departureTime);
        transport.setArrivalTime(arrivalTime);
        transport.setType(type);
        transport.setPrice(price);
        transport.setTripID(getTrip(tripIndex).getTripID());
        getTrip(tripIndex).addTransportation(transport);
    }
    public ArrayList<Accommodation> accessTripAccommodations(Integer tripIndex){
        return getTrip(tripIndex).getAccommodations();
    }
    public ArrayList<Activity> accessTripActivities(Integer tripIndex){
        return getTrip(tripIndex).getActivities();
    }  //these 3 methods are used when you press the button to go to the page for each category
    public ArrayList<Transportation> accessTripTransportation(Integer tripIndex){
        return getTrip(tripIndex).getTransportation();
    }
    public Accommodation viewAccommodation(Integer trip, Integer accommodation){
        return getTrip(trip).getAccommodation(accommodation);
    }
    public Activity viewActivity(Integer trip, Integer activity){
        return getTrip(trip).getActivity(activity);
    }    //we will have a class that deals with formatting the information accessed by these methods
    public Transportation viewTransport(Integer trip, Integer transport){
        return getTrip(trip).getTransport(transport);
    }
    public void cancelTrip(Integer trip){
        trips.remove(trip);
    }
    public void cancelAccommodation(Integer trip, Integer accommodation){
        getTrip(trip).cancelAccommodation(accommodation);
    }
    public void cancelActivity(Integer trip, Integer activity){
        getTrip(trip).cancelActivity(activity);
    }  //deletes these from the database
    public void cancelTransport(Integer trip, Integer transport){
        getTrip(trip).cancelTransport(transport);
    }
    public void updateAccommodation(Integer tripIndex, Integer accommodationIndex, String name, String address, String checkinDate, String checkoutDate, Double price){
        Trip trip = trips.get(tripIndex);
        Accommodation accommodation = trip.getAccommodation(accommodationIndex);
        accommodation.setName(name);
        accommodation.setAddress(address);
        accommodation.setCheckInDate(checkinDate);
        accommodation.setCheckoutDate(checkoutDate);
        accommodation.setPrice(price);
    } //basically calls constructor to reset everything passed to it (excluding tripID & accommodationID) on screen will show text boxes you can edit and will resubmit all values each time even if you only edit one
    public void updateActivity(Integer tripIndex, Integer activityIndex, String name, String location, String date, String time, Double price){
        Trip trip = trips.get(tripIndex);
        Activity activity = trip.getActivity(activityIndex);
        activity.setName(name);
        activity.setLocation(location);
        activity.setDate(date);
        activity.setTime(time);
        activity.setPrice(price);
    }   //might need to go into setters and make sure they reset the database fields
    public void updateTransport(Integer tripIndex, Integer transportIndex, String departureLocation, String destination, String date, String departureTime, String arrivalTime, String type, Double price){
        Trip trip = trips.get(tripIndex);
        Transportation transport = trip.getTransport(transportIndex);
        transport.setDepartureLocation(departureLocation);
        transport.setDestination(destination);
        transport.setDate(date);
        transport.setDepartureTime(departureTime);
        transport.setArrivalTime(arrivalTime);
        transport.setType(type);
        transport.setPrice(price);
    } //need to go into setters and DBHandler and make methods to change the database

}*/

