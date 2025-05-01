package com.example.group17helloworld;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.view.View;
import android.widget.*;
import androidx.core.content.ContextCompat;

import com.example.group17helloworld.DBHandler;

import java.util.ArrayList;
import java.util.Collections;

public class ViewTripDetails extends AppCompatActivity {
    private static DBHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_trip_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Context context = getApplicationContext();
        database = DBHandler.getInstance(context);
        Intent intent = getIntent();
        int tripID = intent.getIntExtra("TripID",-1);
        Trip trip = database.selectTripByID(tripID);

        ArrayList<Transportation> tripTransportations = database.selectTransportationByTripID(tripID);
        ArrayList<Accommodation> tripAccommodations = database.selectAccommodationByTripID(tripID);
        ArrayList<Activity> tripActivities = database.selectActivitiesByTripID(tripID);

        TextView tripDetails = findViewById(R.id.tripDetails);
        tripDetails.setText("Departure: " + trip.getDeparture() + "\nDestination: " + trip.getDestination() + "\nDate Departure: " + trip.getDateDeparture() + "\nDate Return: " + trip.getDateReturn() + "\nBudget: " + trip.getBudget() + "\nTrip ID: " + trip.getTripID());


        TableLayout transportationTable = findViewById(R.id.transportationTable);
        if (tripTransportations.isEmpty())
        {
            TextView noTransportation = findViewById(R.id.noTransportation);
            noTransportation.setText("You currently have no transportation added for this trip");
        }
        Collections.sort(tripTransportations);
        for (int i=0; i<tripTransportations.size(); i++)
        {
            int index = i;
            TableRow transportationRow = new TableRow(this);
            transportationRow.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            transportationRow.setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            transportationRow.setPadding(10,10,10,10);

            TextView transportationRowText = new TextView(this);
            transportationRowText.setText(tripTransportations.get(index).getType()+" from "+tripTransportations.get(index).getDepartureLocation()+" to "+tripTransportations.get(index).getDestination()+"\n Date: "+tripTransportations.get(index).getDate()+" \n Departure Time: "+tripTransportations.get(index).getDepartureTime()+" \n Arrival Date: "+tripTransportations.get(index).getArrivalDate()+" \n Arrival Time: "+tripTransportations.get(index).getArrivalTime()+" \n Price: €"+tripTransportations.get(index).getPrice());
            transportationRow.addView(transportationRowText);
            transportationTable.addView(transportationRow);
        }

        TableLayout accommodationTable = findViewById(R.id.accommodationTable);
        if (tripAccommodations.isEmpty())
        {
            TextView noAccommodation = findViewById(R.id.noAccommodation);
            noAccommodation.setText("You currently have no accommodation added for this trip");
        }
        for (int i=0; i<tripAccommodations.size(); i++)
        {
            int index = i;
            TableRow accommodationRow = new TableRow(this);
            accommodationRow.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            accommodationRow.setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            accommodationRow.setPadding(10,10,10,10);

            TextView accommodationRowText = new TextView(this);
            accommodationRowText.setText(tripAccommodations.get(index).getName()+" located at "+tripAccommodations.get(index).getAddress()+" \n Type: "+tripAccommodations.get(index).getType()+" \n Check In Date: "+tripAccommodations.get(index).getCheckinDate()+" \n Check Out Date: "+tripAccommodations.get(index).getCheckoutDate()+" \n Price: €"+tripAccommodations.get(index).getPrice());
            accommodationRow.addView(accommodationRowText);
            accommodationTable.addView(accommodationRow);
        }

        TableLayout activityTable = findViewById(R.id.activityTable);
        if (tripActivities.isEmpty())
        {
            TextView noActivity = findViewById(R.id.noActivity);
            noActivity.setText("You currently have no activities added for this trip");
        }
        for (int i=0; i<tripActivities.size(); i++)
        {
            int index = i;
            TableRow activityRow = new TableRow(this);
            activityRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            activityRow.setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            activityRow.setPadding(10,10,10,10);

            TextView activityRowText = new TextView(this);
            activityRowText.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            activityRowText.setMaxWidth(800);
            activityRowText.setText(tripActivities.get(index).getName()+"\n Location: "+tripActivities.get(index).getLocation()+"\n Date: "+tripActivities.get(index).getDate()+"\n Time: "+tripActivities.get(index).getTime()+"\n Price: €"+tripActivities.get(index).getPrice()+"\n Description: "+tripActivities.get(index).getDescription());
            activityRowText.setSingleLine(false);
            activityRowText.setEllipsize(null);
            activityRow.addView(activityRowText);
            activityTable.addView(activityRow);
        }

    }

    public void sendToHomePage(View view)
    {
        Intent homePageIntent = new Intent(this, HomePageActivity.class);
        startActivity(homePageIntent);
    }
}