package com.example.group17helloworld;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ViewStatsActivity extends AppCompatActivity {
    private static DBHandler database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_stats);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Context context = getApplicationContext();
        database = DBHandler.getInstance(context);
        String[] menuItems = {"☰", "Create a Trip", "View Upcoming Trips", "View Past Trips", "View Bucket List", "View Favorites", "View Statistics", "Main Menu"};
        Spinner menuSpinner = findViewById(R.id.menuSpinner);
        ArrayAdapter<String> menuSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, menuItems);
        menuSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menuSpinner.setAdapter(menuSpinnerAdapter);


        menuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                switch (selectedItem) {
                    case "Create a Trip":
                        Log.d("HomePageActivity", "Create a Trip selected");
                        sendToCreateTripPage();
                        break;
                    case "View Upcoming Trips":
                        Log.d("HomePageActivity", "View Upcoming Trips selected");
                        sendToHomePage();
                        break;
                    case "View Past Trips":
                        sendToPastTripsPage();
                        break;
                    case "View Bucket List":
                        sendToBucketListPage();
                        break;
                    case "View Favorites":
                        sendToFavoritesPage();
                        break;
                    case "View Statistics":
                        sendToStatsPage();
                        break;
                    case  "Main Menu":
                        sendToMainMenu();
                    default:
                        //Nothing
                        break;
                }
            }
            @Override
            public void onNothingSelected (AdapterView<?> parent)
            {
                // Do nothing
            }
        });
        displayStats();
    }

    public void displayStats()
    {
        ArrayList<Trip> trips = database.getTrips();
        ArrayList<Trip> upcomingTrips = new ArrayList<Trip>();
        ArrayList<Trip> todaysTrips = new ArrayList<Trip>();
        ArrayList<Trip> pastTrips = new ArrayList<Trip>();
        ArrayList<Trip> favTrips = database.getFavoriteTrips();
        ArrayList<BucketListItem> bucketItems = database.getBucketListItems();
        initializeTrips(trips,upcomingTrips,todaysTrips,pastTrips);
        ArrayList<Transportation> transportations = database.getTransportations();
        ArrayList<Accommodation> accommodations = database.getAccommodations();
        ArrayList<Activity> activities = database.getActivities();

        int numTrips = trips.size();
        int numUpcomingTrips = upcomingTrips.size();
        int numTodaysTrips = todaysTrips.size();
        int numPastTrips = pastTrips.size();
        int numFavs = favTrips.size();
        int numBucketItems = bucketItems.size();
        int numTransportations = transportations.size();
        int numAccommodations = accommodations.size();
        int numActivities = activities.size();


        double totalBudget = 0.0;
        double totalTransportationCost = 0.0;
        double totalAccommodationCost = 0.0;
        double totalActivitiesCost = 0.0;
        int numBucketItemsCompleted = 0;
        for (int i=0; i<numBucketItems; i++)
        {
            if (bucketItems.get(i).getStatus())
            {
                numBucketItemsCompleted++;
            }
        }
        for (int i=0; i<trips.size(); i++)
        {
            totalBudget += trips.get(i).getBudget();
        }
        for (int i=0; i<transportations.size(); i++)
        {
            totalTransportationCost += transportations.get(i).getPrice();
        }
        for (int i=0; i<accommodations.size(); i++)
        {
            totalAccommodationCost += accommodations.get(i).getPrice();
        }
        for (int i=0; i<activities.size(); i++)
        {
            totalActivitiesCost += activities.get(i).getPrice();
        }
        double totalCost = totalTransportationCost + totalAccommodationCost + totalActivitiesCost;

        TextView totalTripText = findViewById(R.id.totalTrips);
        totalTripText.setMaxWidth(700);
        totalTripText.setSingleLine(false);
        totalTripText.setEllipsize(null);
        totalTripText.setText("Total Trips: "+numTrips);

        TextView pastTripText = findViewById(R.id.totalPastTrips);
        pastTripText.setText("Past Trips: "+numPastTrips);
        pastTripText.setMaxWidth(700);
        pastTripText.setSingleLine(false);
        pastTripText.setEllipsize(null);

        TextView favTripText = findViewById(R.id.totalFavTrips);
        favTripText.setText("Favorite Trips: "+numFavs);
        favTripText.setMaxWidth(700);
        favTripText.setSingleLine(false);
        favTripText.setEllipsize(null);

        TextView upcomingTripText = findViewById(R.id.totalUpcomingTrips);
        upcomingTripText.setMaxWidth(700);
        upcomingTripText.setSingleLine(false);
        upcomingTripText.setEllipsize(null);
        upcomingTripText.setText("Upcoming Trips: "+numUpcomingTrips);

        TextView todayTripText = findViewById(R.id.totalTodayTrips);
        todayTripText.setMaxWidth(700);
        todayTripText.setSingleLine(false);
        todayTripText.setEllipsize(null);
        todayTripText.setText("Today's Trips: "+numTodaysTrips);

        TextView totalTransportationText = findViewById(R.id.totalTransportations);
        totalTransportationText.setMaxWidth(700);
        totalTransportationText.setSingleLine(false);
        totalTransportationText.setEllipsize(null);
        totalTransportationText.setText("Total Transportation Logged: "+numTransportations);

        TextView totalAccommodationText = findViewById(R.id.totalAccommodations);
        totalAccommodationText.setMaxWidth(700);
        totalAccommodationText.setSingleLine(false);
        totalAccommodationText.setEllipsize(null);
        totalAccommodationText.setText("Total Accommodation Logged: "+numAccommodations);

        TextView totalActivitiesText = findViewById(R.id.totalActivities);
        totalActivitiesText.setMaxWidth(700);
        totalActivitiesText.setSingleLine(false);
        totalActivitiesText.setEllipsize(null);
        totalActivitiesText.setText("Total Activities Logged: "+numActivities);

        TextView totalCostText = findViewById(R.id.totalMoneySpent);
        totalCostText.setMaxWidth(700);
        totalCostText.setSingleLine(false);
        totalCostText.setEllipsize(null);
        totalCostText.setText("Total Money Spent: €"+totalCost);

        TextView totalBudgetText = findViewById(R.id.totalMoneyBudgeted);
        totalBudgetText.setMaxWidth(700);
        totalBudgetText.setSingleLine(false);
        totalBudgetText.setEllipsize(null);
        totalBudgetText.setText("Total Money Budgeted: €"+totalBudget);

        TextView totalTransportationCostText = findViewById(R.id.transportationCost);
        totalTransportationCostText.setMaxWidth(700);
        totalTransportationCostText.setSingleLine(false);
        totalTransportationCostText.setEllipsize(null);
        totalTransportationCostText.setText("Total Transportation Cost: €"+totalTransportationCost);

        TextView totalAccommodationCostText = findViewById(R.id.accommodationCost);
        totalAccommodationCostText.setMaxWidth(700);
        totalAccommodationCostText.setSingleLine(false);
        totalAccommodationCostText.setEllipsize(null);
        totalAccommodationCostText.setText("Total Accommodation Cost: €"+totalAccommodationCost);

        TextView totalActivitiesCostText = findViewById(R.id.activitiesCost);
        totalActivitiesCostText.setMaxWidth(700);
        totalActivitiesCostText.setSingleLine(false);
        totalActivitiesCostText.setEllipsize(null);
        totalActivitiesCostText.setText("Total Activities Cost: €"+totalActivitiesCost);

        TextView totalBucketText = findViewById(R.id.totalBucketItems);
        totalBucketText.setMaxWidth(700);
        totalBucketText.setSingleLine(false);
        totalBucketText.setEllipsize(null);
        totalBucketText.setText("Total Bucket List Items: "+numBucketItems);

        TextView doneBucketText = findViewById(R.id.bucketItemsCompleted);
        doneBucketText.setMaxWidth(700);
        doneBucketText.setSingleLine(false);
        doneBucketText.setEllipsize(null);
        doneBucketText.setText("Total Bucket List Items Completed: "+numBucketItemsCompleted);
    }

    public void initializeTrips(ArrayList<Trip> trips, ArrayList<Trip> upcomingTrips, ArrayList<Trip> todaysTrips, ArrayList<Trip> pastTrips)
    {
        for (int i=0; i<trips.size();i++)
        {
            int index = i;
            String inputDate = trips.get(index).getDateDeparture();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date parsedDate = null;
            try {
                parsedDate = sdf.parse(inputDate);  // Convert string to Date
            } catch (ParseException e) {
                e.printStackTrace();
                Log.d("HomePageActivity", "Date was incorrectly input");
            }
            String todayStr = sdf.format(new Date());
            Date currentDate = new Date();
            try
            {
                currentDate = sdf.parse(todayStr);
            }
            catch (Exception e) {}

            if (currentDate.before(parsedDate)) {
                Log.d("ViewStatsActivity","The trip with TripID "+trips.get(index).getTripID()+" is upcoming");
                upcomingTrips.add(trips.get(index));
            } else if (currentDate.after(parsedDate)) {
                pastTrips.add(trips.get(index));
                Log.d("ViewStatsActivity","The trip with TripID "+trips.get(index).getTripID()+" is past");
            } else {
                Log.d("ViewStatsActivity","The trip with TripID "+trips.get(index).getTripID()+" is today");
                todaysTrips.add(trips.get(index));
            }
        }
    }

    public void viewTripDetails(Trip trip)
    {
        Intent intent = new Intent(this, ViewTripDetails.class);
        intent.putExtra("TripID", trip.getTripID());
        startActivity(intent);
    }

    public void sendToHomePage()
    {
        Intent homePageIntent = new Intent(this, HomePageActivity.class);
        startActivity(homePageIntent);
    }

    public void sendToCreateTripPage()
    {
        Intent createTripIntent = new Intent(this, CreateATripActivity.class);
        startActivity(createTripIntent);
    }

    public void sendToPastTripsPage()
    {
        Intent pastTripsIntent = new Intent(this, ViewPastTripsActivity.class);
        startActivity(pastTripsIntent);
    }

    public void sendToBucketListPage()
    {
        Intent bucketListIntent = new Intent(this, ViewBucketListActivity.class);
        startActivity(bucketListIntent);
    }

    public void sendToFavoritesPage()
    {
        Intent favoritesIntent = new Intent(this, ViewFavoritesActivity.class);
        startActivity(favoritesIntent);
    }
    public void sendToMainMenu()
    {
        Intent mainMenuIntent = new Intent(this, MainActivity.class);
        startActivity(mainMenuIntent);
    }
    public void sendToStatsPage()
    {
        Intent statsPageIntent = new Intent(this, ViewStatsActivity.class);
        startActivity(statsPageIntent);
    }
}