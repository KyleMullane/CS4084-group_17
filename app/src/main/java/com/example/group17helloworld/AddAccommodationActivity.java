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

public class AddAccommodationActivity extends AppCompatActivity {
    private static DBHandler database;
    private Trip trip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_accommodation);
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


    public void addAccommodation(View view)
    {
        try
        {
            //String name, String type, String address, String checkinDate, String checkoutdate, double price, int tripID
            EditText nameText = findViewById(R.id.nameInput);
            String name = nameText.getText().toString();

            EditText typeText = findViewById(R.id.accommodationTypeInput);
            String type = typeText.getText().toString();

            EditText addressText = findViewById(R.id.addressInput);
            String address = addressText.getText().toString();

            EditText checkInDateText = findViewById(R.id.checkInInput);
            String checkInDate = checkInDateText.getText().toString();

            EditText checkOutDateText = findViewById(R.id.checkOutInput);
            String checkOutDate = checkOutDateText.getText().toString();

            EditText priceText = findViewById(R.id.priceInput);
            String priceString = priceText.getText().toString();
            double price = Double.parseDouble(priceString);

            database.addAccommodation(new Accommodation(name,type,address,checkInDate,checkOutDate,price,trip.getTripID()));
            Log.d("AddAccommodationActivity", "Adding accommodation to database was successful");
            ArrayList<Accommodation> accommodationList = database.getAccommodations();
            Log.d("AddAccommodationActivity","First item in accommodation list is "+accommodationList.get(0));
            Intent homePageIntent = new Intent(this, HomePageActivity.class);
            startActivity(homePageIntent);
        }
        catch (Exception e)
        {
            Log.d("AddAccommodationActivity", "Catch block triggered in addAccommodation attempt");
            TextView titleText = findViewById(R.id.addAccommodationTitleText);
            titleText.setText("Error: adding accommodation was not successful");
        }


    }
}