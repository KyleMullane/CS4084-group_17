package com.example.group17helloworld;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ViewBudgetingActivity extends AppCompatActivity {
    private static DBHandler database;
    private Trip trip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_budgeting);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Context context = getApplicationContext();
        database = DBHandler.getInstance(context);
        Intent intent = getIntent();
        int tripID = intent.getIntExtra("TripID",-1);
        trip = database.selectTripByID(tripID);

        calculateBudget();

    }

    public void calculateBudget()
    {
        ArrayList<Transportation> tripTransportation = database.selectTransportationByTripID(trip.getTripID());
        ArrayList<Accommodation> tripAccommodation = database.selectAccommodationByTripID(trip.getTripID());
        ArrayList<Activity> tripActivities = database.selectActivitiesByTripID(trip.getTripID());

        double transportationCost = 0;
        double accommodationCost = 0;
        double activitiesCost = 0;

        for (int i=0; i<tripTransportation.size(); i++)
        {
            transportationCost += tripTransportation.get(i).getPrice();
        }
        for (int i=0; i<tripAccommodation.size(); i++)
        {
            accommodationCost += tripAccommodation.get(i).getPrice();
        }
        for (int i=0; i<tripActivities.size(); i++)
        {
            activitiesCost += tripActivities.get(i).getPrice();
        }

       double totalCost = transportationCost + accommodationCost + activitiesCost;

        TextView intendedBudgetText = findViewById(R.id.intendedBudget);
        intendedBudgetText.setText("Your intended budget for this trip is: €"+trip.getBudget());
        TextView actualCostText = findViewById(R.id.actualCost);
        actualCostText.setText("Your logged spendings on this trip are: €"+totalCost);

        TextView transportationText = findViewById(R.id.transportationCost);
        TextView accommodationText = findViewById(R.id.accommodationCost);
        TextView activitiesText = findViewById(R.id.activitiesCost);

        transportationText.setText("You have spent €"+transportationCost+" on transportation");
        accommodationText.setText("You have spent €"+accommodationCost+" on accommodation");
        activitiesText.setText("You have spent €"+activitiesCost+" on activities");

    }


}