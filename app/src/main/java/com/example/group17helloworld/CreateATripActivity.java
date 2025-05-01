package com.example.group17helloworld;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class CreateATripActivity extends AppCompatActivity {
    private static DBHandler database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_atrip);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Context context = getApplicationContext();
        database = DBHandler.getInstance(context);
    }

    public void createTrip(View view)
    {
        //super.onCreate(savedInstanceState);
        Log.d("CreateATripActivity", "onCreate() called");
        try
        {
            EditText destinationText = findViewById(R.id.destinationInput);
            String destination = destinationText.getText().toString();
            //Log.d("CreateTrip", "Destination: " + destination);

            EditText departureText = findViewById(R.id.departureInput);
            String departure = departureText.getText().toString();

            EditText dateDepartureText = findViewById(R.id.dateDepartureInput);
            String dateDeparture = dateDepartureText.getText().toString();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date parsedDepartureDate = sdf.parse(dateDeparture);

            EditText dateReturnText = findViewById(R.id.dateReturnInput);
            String dateReturn = dateReturnText.getText().toString();

            if (!(dateReturn.isEmpty()))
            {
                Date parsedReturnDate = sdf.parse(dateReturn);
            }

            EditText budgetText = findViewById(R.id.budgetInput);
            String budgetString = budgetText.getText().toString();
            double budget = Double.parseDouble(budgetString);

            Integer isFavorite = 0;
            //String departure, String destination, String dateDeparture, String dateReturn, Double budget

            Trip trip = new Trip(); //might cause the ID to be stored incorrectly
            trip.setDestination(destination);
            trip.setDeparture(departure);
            trip.setDateDeparture(dateDeparture);
            trip.setDateReturn(dateReturn);
            trip.setBudget(budget);
            trip.setFavoriteStatus(isFavorite);

            database.addTrip(trip);
            //database.addTrip(new Trip(departure, destination, dateDeparture, dateReturn, budget, isFavorite));
            Intent homePageIntent = new Intent(this, HomePageActivity.class);
            startActivity(homePageIntent);
        }
        catch (Exception e) {
            Log.e("CreateTrip", "Error adding trip to database: ", e); // This will print the stack trace
            Log.e("CreateTrip", "Error message: " + e.getMessage()); // This will print the specific error message
            TextView titleText = findViewById(R.id.createTripPageTitleText);
            titleText.setText("Error: trip creation not successful");
            TextView errorMessage = findViewById((R.id.createTripErrorMessage));
            errorMessage.setText("Error during trip creation: " + e.getMessage());
        }



    }
}