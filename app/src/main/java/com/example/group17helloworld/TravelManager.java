package com.example.group17helloworld;

public class TravelManager implements TravelManagerInterface{
    /*public User user;
    public void login(String username, String password){
        //finish later
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.getTrips(); //idk if that should work like this
    } //maybe return a user object
    public void signup(){
        //finish later
    } //maybe returns and saves a new user object
    public void logout(){
        //just clears user info
    }
    public void createTrip(String name, String date){
        Trip trip = new Trip();
        trip.setName(name);
        trip.setDate(date);
        //trip.setType(); //add some sort of function that can tell if it is upcoming, current, or past --> convert date
        user.addTrip(trip);
    }
    public ArrayList<Trip> getUpcomingTrips() {
        ArrayList<Trip> trips = user.getTrips(); //create a for loop that goes through trips & sorts them based on type
        ArrayList<Trip> upcoming = new ArrayList<Trip>();
        for (Trip trip : trips) {
            if (trip.getType().equals("upcoming")) {
                upcoming.add(trip);
            }
        }
        return upcoming;
    }//when you hit the button of upcoming trips
    public ArrayList<Trip> getCurrentTrips(){
        ArrayList<Trip> trips = user.getTrips(); //create a for loop that goes through trips & sorts them based on type
        ArrayList<Trip> current = new ArrayList<Trip>();
        for (Trip trip : trips) {
            if (trip.getType().equals("current")) {  //change all characters to lowercase
                current.add(trip);
            }
        }
        return current;
    } //when you hit the button of current trips
    public ArrayList<Trip> getPastTrips(){
        ArrayList<Trip> trips = user.getTrips(); //create a for loop that goes through trips & sorts them based on type
        ArrayList<Trip> past = new ArrayList<Trip>();
        for (Trip trip : trips) {
            if (trip.getType().equals("past")) {  //change all characters to lowercase
                past.add(trip);
            }
        }
        return past;
    } //when you hit the button of past trips

    Trip getTripDetails(Trip trip); //idk what this returns //if we want to display trip info on one screen return all 3 types??

    public void createAccommodation(Integer tripIndex, String name, Double price, String checkinDate, String checkoutDate, String address){
        Accommodation accommodation = new Accommodation(name, price, checkinDate, checkoutDate, address);
        user.getTrip(tripIndex).addAccommodation(accommodation); //0 for now but eventually need to get the proper index
    }
    public void createActivity(Integer tripIndex, String name, Double price, String description, String date){
        Activity activity = new Activity(name, price, description, date);
        user.getTrip(tripIndex).addActivity(activity); //0 for now but eventually get the proper trip
    }   //do we need parameters?? (I think so based on user input), use another class to save it to database?? --> save method is called in these methods
    public void createFlight(Integer tripIndex, Double price, String destination, String departureLocation, String flightID, String date){
        Flight flight = new Flight(price, destination, departureLocation, flightID, date);
        user.getTrip(tripIndex).addFlight(flight); //0 for now but eventually get the proper trip
    }
    public ArrayList<Accommodation> accessTripAccommodations(Integer tripIndex){
        return user.getTrip(tripIndex).getAccommodations(); //figure out index
    }
    public ArrayList<Activity> accessTripActivities(Integer tripIndex){
        return user.getTrip(tripIndex).getActivities(); //figure out index maybe change the index to trip name
    }  //these 3 methods are used when you press the button to go to the page for each category
    public ArrayList<Flight> accessTripFlights(Integer tripIndex){
        return user.getTrip(tripIndex).getFlights(); //in the other class will determine index by size/name & what button is clicked
    }
    public Accommodation viewAccommodation(Integer trip, Integer accommodation){
        return user.getTrip(trip).getAccommodation(accommodation);
    }
    public Activity viewActivity(Integer trip, Integer activity){
        return user.getTrip(trip).getActivity(activity);
    }    //we will have a class that deals with formatting the information accessed by these methods
    public Flight viewFlight(Integer trip, Integer flight){
        return user.getTrip(trip).getFlight(flight);
    }
    void cancelTrip();
    void cancelAccommodation();
    void cancelActivity();  //deletes these from the database
    void cancelFlight();
    void deleteUser(); //deletes from database & clears user info
    void updateAccommodation(Accommodation accommodation);
    void updateActivity(Activity activity);   //maybe need more specific methods depending on what details you want to update
    void updateFlight(Flight flight);
    void reviewTrip();
    void shareTripWithPublic(Trip trip); //might need a new entity set for shared trips so all trips to be displayed to public are in one place
    ArrayList<Trip> getSuggestedTripsLocation(String location);
    ArrayList<Trip> getSuggestedTripsRatings(Integer rating);*/
}

