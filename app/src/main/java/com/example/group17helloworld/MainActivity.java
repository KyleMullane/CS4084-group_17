package com.example.group17helloworld;

import android.content.Intent;
import android.graphics.Color;
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
import android.view.ViewGroup;
import android.widget.*;
import androidx.core.content.ContextCompat;

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
            //database.addTrip(new Trip(0, "Ireland", "Switzerland", "21/3/2025", "24/3/2025", 500.0));
        } catch (Exception e) {
            Log.d("MainActivity", "Catch block triggered in addTrip attempt");
        }

        ArrayList<Trip> trips = database.getTrips();
        Trip testTrip = trips.get(0);
        Log.d("MainActivity", "Num trips in table is "+trips.size());
        //TextView tripText = findViewById(R.id.tripText);
        //tripText.setText("Leaving From: "+testTrip.getDeparture()+ "\nGoing to: "+testTrip.getDestination()+"\nDate: "+testTrip.getDateDeparture());

        TableLayout tableLayout = findViewById(R.id.tripTable);

        for (int i=0; i<trips.size(); i++)
        {
            int index = i;
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            row.setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            row.setPadding(10,10,10,10);

            LinearLayout verticalLayout = new LinearLayout(this);
            verticalLayout.setOrientation(LinearLayout.VERTICAL);
            /*verticalLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));*/


            TextView rowText = new TextView(this);
            rowText.setText("Leaving From: "+trips.get(i).getDeparture()+"\nGoing to: "+trips.get(i).getDestination()+"\nDate: "+trips.get(i).getDateDeparture());
            rowText.setPadding(8, 8, 8, 8);

            String[] dropdownItems = {"", "Add Transportation", "Add Accommodation", "Add Activities"};
            Spinner spinner = new Spinner(this);
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dropdownItems);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerAdapter);
            spinner.setScaleX(0.5f); // Scale width to 80%
            spinner.setScaleY(0.5f);

            Button viewDetailsButton = new Button(this);
            viewDetailsButton.setText("View Details");
            viewDetailsButton.setTextColor(Color.WHITE); // Change text color
            viewDetailsButton.setBackgroundResource(R.drawable.custom_button);
            viewDetailsButton.setPadding(0, 0, 0, 0); // Adjust padding
            viewDetailsButton.setScaleX(0.5f); // Scale width to 80%
            viewDetailsButton.setScaleY(0.5f);
            viewDetailsButton.setAllCaps(false);
            viewDetailsButton.setOnClickListener(v -> viewTripDetails(trips.get(index)));

            verticalLayout.addView(rowText);
            verticalLayout.addView(viewDetailsButton);
            row.addView(spinner);
            row.addView(verticalLayout);
            tableLayout.addView(row);

        }

    }
    public void viewTripDetails(Trip trip)
    {
        Intent intent = new Intent(this, ViewTripDetails.class);
        intent.putExtra("TripID", trip.getTripID());
        startActivity(intent);
    }
}