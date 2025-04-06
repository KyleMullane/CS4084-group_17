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
        try
        {
            EditText destinationText = findViewById(R.id.destinationInput);
            String destination = destinationText.getText().toString();

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
            //String departure, String destination, String dateDeparture, String dateReturn, Double budget

            database.addTrip(new Trip(departure,destination,dateDeparture,dateReturn,budget));
            Intent homePageIntent = new Intent(this, HomePageActivity.class);
            startActivity(homePageIntent);
        }
        catch (Exception e)
        {
            Log.d("CreateATripActivity", "Catch block triggered in addTrip attempt");
            TextView titleText = findViewById(R.id.createTripPageTitleText);
            titleText.setText("Error: trip creation not successful");
            TextView errorMessage = findViewById((R.id.createTripErrorMessage));
            errorMessage.setText("Make sure that required fields are filled in, that dates are formatted correctly, and that a number is submitted for the budget, not a word");
        }



    }
}