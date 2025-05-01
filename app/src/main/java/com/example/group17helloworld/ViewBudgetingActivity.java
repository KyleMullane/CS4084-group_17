package com.example.group17helloworld;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        TextView titleText = findViewById(R.id.budgetingPageTitle);
        titleText.setText("View Your Budgeting Details Below!");
        TextView tableTitle = findViewById(R.id.tableTitle);
        tableTitle.setText("Budgeting for your trip to "+trip.getDestination()+": ");
        TextView intendedBudgetText = findViewById(R.id.intendedBudget);
        intendedBudgetText.setText("Intended Budget: €"+trip.getBudget());
        TextView actualCostText = findViewById(R.id.actualCost);
        actualCostText.setText("Logged Spendings: €"+totalCost);

        TextView transportationText = findViewById(R.id.transportationCost);
        TextView accommodationText = findViewById(R.id.accommodationCost);
        TextView activitiesText = findViewById(R.id.activitiesCost);

        transportationText.setText("Transportation Cost: €"+transportationCost);
        accommodationText.setText("Accommodation Cost: €"+accommodationCost);
        activitiesText.setText("Activities cost: €"+activitiesCost);
        TextView budgetFeedback = findViewById(R.id.budgetFeedback);
        if (totalCost > trip.getBudget())
        {

            double maxCost = Math.max(transportationCost, Math.max(accommodationCost, activitiesCost));
            if (transportationCost == maxCost && accommodationCost != maxCost && activitiesCost != maxCost)
            {
                budgetFeedback.setText("You are currently over budget by €"+(totalCost-trip.getBudget())+". Consider spending less on transportation");
            }
            else if (transportationCost != maxCost && accommodationCost == maxCost && activitiesCost != maxCost)
            {
                budgetFeedback.setText("You are currently over budget by €"+(totalCost-trip.getBudget())+". Consider spending less on accommodation");
            }
            else if (transportationCost != maxCost && accommodationCost != maxCost && activitiesCost == maxCost)
            {
                budgetFeedback.setText("You are currently over budget by €"+(totalCost-trip.getBudget())+". Consider spending less on activities");
            }
            else if (transportationCost == maxCost && accommodationCost == maxCost && activitiesCost != maxCost)
            {
                budgetFeedback.setText("You are currently over budget by €"+(totalCost-trip.getBudget())+". Consider spending less on transportation and accommodation");
            }
            else if (transportationCost == maxCost && accommodationCost != maxCost && activitiesCost == maxCost)
            {
                budgetFeedback.setText("You are currently over budget by €"+(totalCost-trip.getBudget())+". Consider spending less on transportation and activities");
            }
            else if (transportationCost != maxCost && accommodationCost == maxCost && activitiesCost == maxCost)
            {
                budgetFeedback.setText("You are currently over budget by €"+(totalCost-trip.getBudget())+". Consider spending less on accommodation and activities");
            }
            else
            {
                budgetFeedback.setText("You are currently over budget by €"+(totalCost-trip.getBudget())+". Reconsider your spendings!");
            }
        }
        else if (totalCost < trip.getBudget())
        {
            budgetFeedback.setText("Nice job! You are under budget by €"+(trip.getBudget()-totalCost));
        }
        else
        {
            budgetFeedback.setText("Nice job! You have spent the exact amount of your planned budget. Try not to spend any more!");
        }

    }

    public void sendToHomePage(View view)
    {
        Intent homePageIntent = new Intent(this, HomePageActivity.class);
        startActivity(homePageIntent);
    }


}