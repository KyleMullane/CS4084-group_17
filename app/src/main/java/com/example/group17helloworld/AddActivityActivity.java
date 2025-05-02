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
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AddActivityActivity extends AppCompatActivity {
    private static DBHandler database;
    private Trip trip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_activity);
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


    public void addActivity(View view)
    {
        boolean exceptionTriggered = false;
        boolean dateExceptionTriggered = false;
        try
        {
            TextView emptyViewMessage = findViewById(R.id.addDateErrorMessage);
            emptyViewMessage.setText("");
            //String name, String location, String date, String time, double price, String description, int tripID
            EditText nameText = findViewById(R.id.nameInput);
            String name = nameText.getText().toString();

            EditText locationText = findViewById(R.id.locationInput);
            String location = locationText.getText().toString();

            EditText dateText = findViewById(R.id.dateInput);
            String date = dateText.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date parsedDate = new Date();
            TextView dateMessage = findViewById(R.id.addDateErrorMessage);
            dateMessage.setText("");
            try {
                sdf.setLenient(false);
                parsedDate = sdf.parse(date);
            }
            catch (Exception e)
            {
                exceptionTriggered = true;
                dateExceptionTriggered = true;
                dateMessage.setText("Error with the date format. Make sure to use the format YYYY-MM-DD, with no spaces. Sample date: 2025-05-02");
            }

            EditText timeText = findViewById(R.id.timeInput);
            String time = timeText.getText().toString();
            TextView timeMessage = findViewById(R.id.addTimeErrorMessage);
            String dateTime = "";
            Date parsedTime = new Date();
            try {
                timeMessage.setText("");
                SimpleDateFormat timeSdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                timeSdf.setLenient(false);
                dateTime = date + "-" + time;
                parsedTime = timeSdf.parse(dateTime);
            }
            catch (Exception e)
            {
                exceptionTriggered = true;
                if (!dateExceptionTriggered)
                {
                    timeMessage.setText("Error with time format. Make sure to use HH:mm with the 24 hour clock, and no spaces. Sample time: 15:31");
                }
            }

            EditText priceText = findViewById(R.id.priceInput);
            String priceString = priceText.getText().toString();
            TextView priceMessage = findViewById(R.id.priceErrorMessage);
            double price = 0.0;
            try {
                priceMessage.setText("");
                price = Double.parseDouble(priceString);
                price = Math.round(price * 100.0) / 100.0;
                if (price < 0)
                {
                    throw new Exception();
                }
            }
            catch (Exception e)
            {
                exceptionTriggered = true;
                priceMessage.setText("Invalid price. Make sure to enter a numerical value that is positive");
            }

            EditText descriptionText = findViewById(R.id.descriptionInput);
            String description = descriptionText.getText().toString();
            Date tripDepartureDate = new Date();
            Date tripReturnDate = new Date();
            if (!exceptionTriggered)
            {
                try {
                    tripDepartureDate = sdf.parse(trip.getDateDeparture());
                    if (parsedDate.before(tripDepartureDate))
                    {
                        exceptionTriggered = true;
                        dateMessage.setText("Error: activity date is not in the range of your trip's dates");
                    }
                    if (!trip.getDateReturn().isEmpty())
                    {
                        tripReturnDate = sdf.parse(trip.getDateReturn());
                        if (tripReturnDate.before(parsedDate))
                        {
                            exceptionTriggered = true;
                            dateMessage.setText("Error: activity date is not in the range of your trip's dates");
                        }
                    }
                }
                catch (Exception e) {}
            }
            if (name.isEmpty() || location.isEmpty() || description.isEmpty())
            {
                throw new Exception();
            }

            if (!exceptionTriggered)
            {
                database.addActivity(new Activity(name,location,date,dateTime,price,description,trip.getTripID()));
                Log.d("AddActivityActivity", "Adding activity to database was successful");
                ArrayList<Activity> activityList = database.getActivities();
                Log.d("AddActivityActivity","First item in activity list is "+activityList.get(0));
                Intent homePageIntent = new Intent(this, HomePageActivity.class);
                startActivity(homePageIntent);
            }

        }
        catch (Exception e)
        {
            Log.d("AddActivityActivity", "Catch block triggered in addActivity attempt");
            TextView emptyViewMessage = findViewById(R.id.addDateErrorMessage);
            emptyViewMessage.setText("Error: some fields are not filled in");
        }



    }

    public void sendToHomePage(View view)
    {
        Intent homePageIntent = new Intent(this, HomePageActivity.class);
        startActivity(homePageIntent);
    }
}