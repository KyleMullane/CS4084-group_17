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
        try
        {
            //String name, String location, String date, String time, double price, String description, int tripID
            EditText nameText = findViewById(R.id.nameInput);
            String name = nameText.getText().toString();

            EditText locationText = findViewById(R.id.locationInput);
            String location = locationText.getText().toString();

            EditText dateText = findViewById(R.id.dateInput);
            String date = dateText.getText().toString();

            EditText timeText = findViewById(R.id.timeInput);
            String time = timeText.getText().toString();

            EditText priceText = findViewById(R.id.priceInput);
            String priceString = priceText.getText().toString();
            double price = Double.parseDouble(priceString);

            EditText descriptionText = findViewById(R.id.descriptionInput);
            String description = descriptionText.getText().toString();

            database.addActivity(new Activity(name,location,date,time,price,description,trip.getTripID()));
            Log.d("AddActivityActivity", "Adding activity to database was successful");
            ArrayList<Activity> activityList = database.getActivities();
            Log.d("AddActivityActivity","First item in activity list is "+activityList.get(0));
            Intent homePageIntent = new Intent(this, HomePageActivity.class);
            startActivity(homePageIntent);
        }
        catch (Exception e)
        {
            Log.d("AddActivityActivity", "Catch block triggered in addActivity attempt");
            TextView titleText = findViewById(R.id.addActivityTitleText);
            titleText.setText("Error: adding activity was not successful");
        }



    }
}