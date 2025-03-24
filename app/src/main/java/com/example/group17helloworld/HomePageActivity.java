package com.example.group17helloworld;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {
    DBHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String username = intent.getStringExtra(LogInActivity.KEY_VALUE);
        TextView target = findViewById(R.id.greetingView);
        target.setText("Hello, " + username + "!");
        Context context = getApplicationContext();
        database = DBHandler.getInstance(context);
        //String departureLocation, String destinationLocation, String dateDeparture, String dateReturn, String username, String description, double overallCost, int tripID
        try {
            database.addTrip(new Trip("Ireland", "Switzerland", "21/3/2025", "24/3/2025", "jmcgettrick", "Incredible experience!!", 500.50, 3));
        } catch (Exception e) {
            Log.d("HomePageActivity", "Catch block triggered in addTrip attempt");
        }

        ArrayList<Trip> trips = database.getTrips();
        Trip testTrip = trips.get(0);
        TextView tripText = findViewById(R.id.tripText);
        tripText.setText("Test trip: Departure: "+testTrip.getDepartureLocation()+" Destination: "+testTrip.getDestinationLocation()+" Date Departure: "+testTrip.getDateDeparture()+" Date Return: "+testTrip.getDateReturn()+" Username: "+testTrip.getUsername()+" Description: "+testTrip.getDescription()+" Trip ID: "+testTrip.getTripID());


    }
}