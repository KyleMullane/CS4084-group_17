package com.example.group17helloworld;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {
    private static DBHandler database;
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
        Context context = getApplicationContext();
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
            spinner.setScaleX(0.5f);
            spinner.setScaleY(0.5f);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedItem = parent.getItemAtPosition(position).toString();

                    switch (selectedItem) {
                        case "Add Transportation":
                            Log.d("HomePageActivity", "Add transportation selected");
                            Intent addTransportationIntent = new Intent(context, AddTransportationActivity.class);
                            addTransportationIntent.putExtra("TripID", trips.get(index).getTripID());
                            startActivity(addTransportationIntent);
                            break;
                        case "Add Accommodation":
                            Log.d("HomePageActivity", "Add accommodation selected");
                            break;
                        case "Add Activities":
                            Log.d("HomePageActivity", "Add activities selected");
                            break;
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

            row.addView(verticalLayout);
            row.addView(spinner);

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