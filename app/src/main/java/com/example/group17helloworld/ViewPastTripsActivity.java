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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class ViewPastTripsActivity extends AppCompatActivity {
    private static DBHandler database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_past_trips);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Context context = getApplicationContext();
        database = DBHandler.getInstance(context);
        // Menu At Top of the Screen
        String[] menuItems = {"☰", "Create a Trip", "View Upcoming Trips", "View Past Trips", "View Bucket List"};
        Spinner menuSpinner = findViewById(R.id.pastSpinner);
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
        //LinearLayout topOfHomePage = findViewById(R.id.topOfHomePageLayout);
        //topOfHomePage.addView(menuSpinner);


        ArrayList<Trip> trips = database.getTrips();
        ArrayList<Trip> pastTrips = new ArrayList<Trip>();

        Log.d("MainActivity", "Num trips in table is "+trips.size());
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
                Log.d("HomePageActivity","The trip with TripID "+trips.get(index).getTripID()+" is upcoming");
            } else if (currentDate.after(parsedDate)) {
                Log.d("HomePageActivity","The trip with TripID "+trips.get(index).getTripID()+" is past");
                pastTrips.add(trips.get(index));
            } else {
                Log.d("HomePageActivity","The trip with TripID "+trips.get(index).getTripID()+" is today");
            }
        }

        Collections.sort(pastTrips);
        Collections.reverse(pastTrips);
        if (!pastTrips.isEmpty())
        {
            displayPastTrips(pastTrips, context);
        }


    }


    public void displayPastTrips(ArrayList<Trip> pastTrips, Context context)
    {
        TableLayout tableLayout = findViewById(R.id.pastTripTable);

        for (int i=0; i<pastTrips.size(); i++)
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
            rowText.setText("Leaving From: "+pastTrips.get(i).getDeparture()+"\nGoing to: "+pastTrips.get(i).getDestination()+"\nDate: "+pastTrips.get(i).getDateDeparture());
            rowText.setPadding(8, 8, 8, 8);


            String[] dropdownItems = {"Options", "Add Transportation", "Add Accommodation", "Add Activities"};
            Spinner spinner = new Spinner(this);
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dropdownItems);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerAdapter);
            spinner.setScaleX(0.7f);
            spinner.setScaleY(0.7f);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedItem = parent.getItemAtPosition(position).toString();

                    switch (selectedItem) {
                        case "Add Transportation":
                            Log.d("HomePageActivity", "Add transportation selected");
                            Intent addTransportationIntent = new Intent(context, AddTransportationActivity.class);
                            addTransportationIntent.putExtra("TripID", pastTrips.get(index).getTripID());
                            startActivity(addTransportationIntent);
                            break;
                        case "Add Accommodation":
                            Log.d("HomePageActivity", "Add accommodation selected");
                            Intent addAccommodationIntent = new Intent(context, AddAccommodationActivity.class);
                            addAccommodationIntent.putExtra("TripID", pastTrips.get(index).getTripID());
                            startActivity(addAccommodationIntent);
                            break;
                        case "Add Activities":
                            Log.d("HomePageActivity", "Add activities selected");
                            Intent addActivityIntent = new Intent(context, AddActivityActivity.class); //lol
                            addActivityIntent.putExtra("TripID", pastTrips.get(index).getTripID());
                            startActivity(addActivityIntent);
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
            viewDetailsButton.setOnClickListener(v -> viewTripDetails(pastTrips.get(index)));

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
}