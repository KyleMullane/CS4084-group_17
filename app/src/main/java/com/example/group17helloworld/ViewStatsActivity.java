package com.example.group17helloworld;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

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
        displayStats();
    }

    public void displayStats()
    {
        ArrayList<Trip> trips = database.getTrips();
        ArrayList<Trip> upcomingTrips = new ArrayList<Trip>();
        ArrayList<Trip> todaysTrips = new ArrayList<Trip>();
        ArrayList<Trip> pastTrips = new ArrayList<Trip>();
        initializeTrips(trips,upcomingTrips,todaysTrips,pastTrips);
        ArrayList<Transportation> transportations = database.getTransportations();
        ArrayList<Accommodation> accommodations = database.getAccommodations();
        ArrayList<Activity> activities = database.getActivities();

        int numTrips = trips.size();
        int numUpcomingTrips = upcomingTrips.size();
        int numTodaysTrips = todaysTrips.size();
        int numPastTrips = pastTrips.size();
        int numTransportations = transportations.size();
        int numAccommodations = accommodations.size();
        int numActivities = activities.size();

        double totalBudget = 0.0;
        double totalTransportationCost = 0.0;
        double totalAccommodationCost = 0.0;
        double totalActivitiesCost = 0.0;
        double totalCost = totalTransportationCost + totalAccommodationCost + totalActivitiesCost;
        for (int i=0; i<trips.size(); i++)
        {
            totalBudget += trips.get(i).getBudget();
        }

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
}