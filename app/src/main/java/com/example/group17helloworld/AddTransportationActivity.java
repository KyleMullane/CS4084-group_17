package com.example.group17helloworld;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
        boolean exceptionTriggered = false;
        boolean dateExceptionTriggered = false;
        boolean arrivalDateExceptionTriggered = false;
        try
        {
            TextView emptyFieldsMessage = findViewById(R.id.dateErrorMessage);
            emptyFieldsMessage.setText("");
            // String departureLocation, String destination, String date, String departureTime, String arrivalTime, String type, double price, int tripID
            EditText departureText = findViewById(R.id.departureInput);
            String departure = departureText.getText().toString();
            Log.d("AddTransportationActivity", "departure is: "+departure);

            EditText destinationText = findViewById(R.id.destinationInput);
            String destination = destinationText.getText().toString();

            EditText dateText = findViewById(R.id.dateInput);
            String date = dateText.getText().toString();
            TextView dateErrorMessage = findViewById(R.id.dateErrorMessage);
            Date parsedDate;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                sdf.setLenient(false);
                parsedDate = sdf.parse(date);
                dateErrorMessage.setText("");
            }
            catch (Exception e)
            {
                exceptionTriggered = true;
                dateExceptionTriggered = true;
                dateErrorMessage.setText("Error with departure date format. Make sure to use the format YYYY-MM-DD, with no spaces. Sample date: 2025-05-02");
            }

            EditText departureTimeText = findViewById(R.id.departureTimeInput);
            String departureTime = departureTimeText.getText().toString();
            TextView departureTimeMessage = findViewById(R.id.departTimeMessage);
            Date parsedDepartureTime = new Date();
            String dateTime = "";
            try {
                departureTimeMessage.setText("");
                SimpleDateFormat timeSdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                timeSdf.setLenient(false);
                dateTime = date + "-" + departureTime;
                parsedDepartureTime = timeSdf.parse(dateTime);
            }
            catch (Exception e)
            {
                exceptionTriggered = true;
                if (!dateExceptionTriggered)
                {
                    departureTimeMessage.setText("Error with departure time format. Make sure to use HH:mm with the 24 hour clock, and no spaces. Sample time: 15:31");
                }
            }

            EditText arrivalDateText = findViewById(R.id.arrivalDateInput);
            String arrivalDate = arrivalDateText.getText().toString();
            TextView arrivalDateMessage = findViewById(R.id.arrivalDateMessage);
            Date parsedArrivalDate;
            try {
                SimpleDateFormat arrivalSdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                arrivalSdf.setLenient(false);
                parsedArrivalDate = arrivalSdf.parse(arrivalDate);
                arrivalDateMessage.setText("");
            }
            catch (Exception e)
            {
                exceptionTriggered = true;
                arrivalDateExceptionTriggered = true;
                arrivalDateMessage.setText("Error with arrival date format. Make sure to use the format YYYY-MM-DD, with no spaces. Sample date: 2025-05-02");
            }

            EditText arrivalTimeText = findViewById(R.id.arrivalTimeInput);
            String arrivalTime = arrivalTimeText.getText().toString();
            TextView arrivalTimeMessage = findViewById(R.id.arrivalTimeMessage);
            Date parsedArrivalTime = new Date();
            String arrivalDateTime = "";
            try {
                arrivalTimeMessage.setText("");
                SimpleDateFormat arrivalTimeSdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                arrivalTimeSdf.setLenient(false);
                arrivalDateTime = arrivalDate + "-" + arrivalTime;
                parsedArrivalTime = arrivalTimeSdf.parse(arrivalDateTime);
            }
            catch (Exception e)
            {
                exceptionTriggered = true;
                if (!arrivalDateExceptionTriggered)
                {
                    arrivalTimeMessage.setText("Error with arrival time format. Make sure to use HH:mm with the 24 hour clock, and no spaces. Sample time: 15:31");
                }
            }
            TextView wrongDates = findViewById(R.id.wrongDatesMessage);
            wrongDates.setText("");
            if (!exceptionTriggered)
            {
                if (parsedArrivalTime.before(parsedDepartureTime))
                {
                    exceptionTriggered = true;
                    wrongDates.setText("Error: arrival date/time is before departure date/time");
                }
            }


            EditText typeText = findViewById(R.id.typeInput);
            String type = typeText.getText().toString();

            EditText priceText = findViewById(R.id.priceInput);
            String priceString = priceText.getText().toString();
            TextView costMessage = findViewById(R.id.wrongCostMessage);
            double price = 0;
            try {
                costMessage.setText("");
                price = Double.parseDouble(priceString);
                if (price < 0)
                {
                    throw new Exception();
                }
            }
            catch (Exception e)
            {
                exceptionTriggered = true;
                costMessage.setText("Invalid cost. Make sure to enter a numerical value that is positive");
            }

            if (departure.isEmpty() || type.isEmpty() || destination.isEmpty())
            {
                throw new Exception();
            }
            if (!exceptionTriggered)
            {
                database.addTransportation(new Transportation(departure,destination,date,dateTime,arrivalDateTime,arrivalTime,type,price,trip.getTripID()));
                Log.d("AddTransportationActivity", "Adding transportation to database was successful");
                ArrayList<Transportation> transportationList = database.getTransportations();
                Log.d("AddTransportationActivity","First item in transportation list is "+transportationList.get(0));
                Intent homePageIntent = new Intent(this, HomePageActivity.class);
                startActivity(homePageIntent);
            }

        }
        catch (Exception e)
        {
            Log.d("AddTransportationActivity", "Catch block triggered in addTransportation attempt");
            TextView emptyFieldsMessage = findViewById(R.id.dateErrorMessage);
            emptyFieldsMessage.setText("Error: some fields not filled in");
        }



    }
}