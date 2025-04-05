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

import java.util.ArrayList;

public class AddTransportationActivity extends AppCompatActivity {
    private static DBHandler database;
    private Trip trip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_transportation);
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
    }

    public void addTransportation(View view)
    {
        try
        {
            // String departureLocation, String destination, String date, String departureTime, String arrivalTime, String type, double price, int tripID
            EditText destinationText = findViewById(R.id.departureInput);
            String destination = destinationText.getText().toString();

            EditText departureText = findViewById(R.id.destinationInput);
            String departure = departureText.getText().toString();

            EditText dateText = findViewById(R.id.dateInput);
            String date = dateText.getText().toString();

            EditText departureTimeText = findViewById(R.id.departureTimeInput);
            String departureTime = departureTimeText.getText().toString();

            EditText arrivalTimeText = findViewById(R.id.arrivalTimeInput);
            String arrivalTime = arrivalTimeText.getText().toString();

            EditText typeText = findViewById(R.id.typeInput);
            String type = typeText.getText().toString();

            EditText priceText = findViewById(R.id.priceInput);
            String priceString = priceText.getText().toString();
            double price = Double.parseDouble(priceString);

            database.addTransportation(new Transportation(departure,destination,date,departureTime,arrivalTime,type,price,trip.getTripID()));
            Log.d("AddTransportationActivity", "Adding transportation to database was successful");
            ArrayList<Transportation> transportationList = database.getTransportations();
            Log.d("AddTransportationActivity","First item in transportation list is "+transportationList.get(0));
            Intent homePageIntent = new Intent(this, HomePageActivity.class);
            startActivity(homePageIntent);
        }
        catch (Exception e)
        {
            Log.d("AddTransportationActivity", "Catch block triggered in addTransportation attempt");
            TextView titleText = findViewById(R.id.addTransportationTitleText);
            titleText.setText("Error: adding transportation was not successful");
        }



    }
}