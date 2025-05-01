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
        boolean exceptionTriggered = false;
        //super.onCreate(savedInstanceState);
        Log.d("CreateATripActivity", "onCreate() called");
        try
        {
            TextView errorMessage = findViewById((R.id.emptyFieldsErrorMessage));
            errorMessage.setText("");
            EditText destinationText = findViewById(R.id.destinationInput);
            String destination = destinationText.getText().toString();
            //Log.d("CreateTrip", "Destination: " + destination);

            EditText departureText = findViewById(R.id.departureInput);
            String departure = departureText.getText().toString();

            EditText dateDepartureText = findViewById(R.id.dateDepartureInput);
            String dateDeparture = dateDepartureText.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            TextView dateErrorMessage = findViewById(R.id.dateErrorMessage);
            dateErrorMessage.setText("");
            try {
                Date parsedDepartureDate = sdf.parse(dateDeparture);
            }
            catch (Exception e)
            {
                exceptionTriggered = true;
                dateErrorMessage.setText("Error with departure date format. Make sure to use the format YYYY-MM-DD, with no spaces. Sample date: 2025-05-02");
            }


            EditText dateReturnText = findViewById(R.id.dateReturnInput);
            String dateReturn = dateReturnText.getText().toString();

            if (!(dateReturn.isEmpty()))
            {
                try {
                    Date parsedReturnDate = sdf.parse(dateReturn);
                }
                catch (Exception e)
                {
                    dateErrorMessage.setText("Error with return date format. Make sure to use the format YYYY-MM-DD, with no spaces. Sample date: 2025-05-02");
                }

            }

            EditText budgetText = findViewById(R.id.budgetInput);
            String budgetString = budgetText.getText().toString();
            TextView budgetErrorMessage = findViewById(R.id.budgetErrorMessage);
            budgetErrorMessage.setText("");
            double budget = 0.0;
            try {
                budget = Double.parseDouble(budgetString);
                if (budget < 0)
                {
                    throw new Exception();
                }
            }
            catch (Exception e)
            {
                exceptionTriggered = true;
                budgetErrorMessage.setText("Invalid budget. Make sure to input a numerical value that is positive");
            }
            if (departure.isEmpty() || destination.isEmpty())
            {
                throw new Exception();
            }

            Integer isFavorite = 0;
            //String departure, String destination, String dateDeparture, String dateReturn, Double budget
            if (!exceptionTriggered)
            {
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

        }
        catch (Exception e) {
            Log.e("CreateTrip", "Error adding trip to database: ", e); // This will print the stack trace
            Log.e("CreateTrip", "Error message: " + e.getMessage()); // This will print the specific error message
            TextView errorMessage = findViewById((R.id.emptyFieldsErrorMessage));
            errorMessage.setText("Error: not all required fields are filled in");
        }



    }
}