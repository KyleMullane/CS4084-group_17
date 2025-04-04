package com.example.group17helloworld;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Context;
import android.view.View;
import android.widget.*;

import com.example.group17helloworld.DBHandler;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static DBHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toast toast = Toast.makeText(getApplicationContext(), "onCreate Called", Toast.LENGTH_LONG);
        toast.show();

        Context context = getApplicationContext();
        Log.d("MainActivity", "Testing to see if printing to the terminal works for debugging purposes!");
        database = DBHandler.getInstance(context);
        try {
            database.addTrip(new Trip(0, "Ireland", "Switzerland", "21/3/2025", "24/3/2025", 500.0));
        } catch (Exception e) {
            Log.d("MainActivity", "Catch block triggered in addTrip attempt");
        }

        ArrayList<Trip> trips = database.getTrips();
        Trip testTrip = trips.get(0);
        TripTableFragment tripTableFragment = (TripTableFragment) getSupportFragmentManager().findFragmentById(R.id.TripTableFragment);
        TextView tripText = findViewById(R.id.tripText);
        tripText.setText("Test trip: Departure: " + testTrip.getDeparture() + " Destination: " + testTrip.getDestination() + " Date Departure: " + testTrip.getDateDeparture() + " Date Return: " + testTrip.getDateReturn() + " Budget: " + testTrip.getBudget() + " Trip ID: " + testTrip.getTripID());

    }
}