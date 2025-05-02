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
        boolean exceptionTriggered = false;
        try
        {
            TextView emptyFieldMessage = findViewById(R.id.checkInDateErrorMessage);
            emptyFieldMessage.setText("");
            //String name, String type, String address, String checkinDate, String checkoutdate, double price, int tripID
            EditText nameText = findViewById(R.id.nameInput);
            String name = nameText.getText().toString();

            EditText typeText = findViewById(R.id.accommodationTypeInput);
            String type = typeText.getText().toString();

            EditText addressText = findViewById(R.id.addressInput);
            String address = addressText.getText().toString();

            EditText checkInDateText = findViewById(R.id.checkInInput);
            String checkInDate = checkInDateText.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date parsedDate = new Date();
            TextView checkInMessage = findViewById(R.id.checkInDateErrorMessage);
            checkInMessage.setText("");
            try {
                sdf.setLenient(false);
                parsedDate = sdf.parse(checkInDate);
                checkInMessage.setText("");
            }
            catch (Exception e)
            {
                exceptionTriggered = true;
                checkInMessage.setText("Error with check in date format. Make sure to use the format YYYY-MM-DD, with no spaces. Sample date: 2025-05-02");
            }
            EditText checkOutDateText = findViewById(R.id.checkOutInput);
            String checkOutDate = checkOutDateText.getText().toString();
            TextView checkOutMessage = findViewById(R.id.checkOutDateErrorMessage);
            Date checkOutDateDate = new Date();
            try {
                sdf.setLenient(false);
                checkOutDateDate = sdf.parse(checkOutDate);
                checkOutMessage.setText("");
            }
            catch (Exception e)
            {
                exceptionTriggered = true;
                checkOutMessage.setText("Error with check out date format. Make sure to use the format YYYY-MM-DD, with no spaces. Sample date: 2025-05-02");
            }
            EditText priceText = findViewById(R.id.priceInput);
            String priceString = priceText.getText().toString();
            TextView priceMessage = findViewById(R.id.priceErrorMessage);
            double price = 0.0;
            try {
                priceMessage.setText("");
                price = Double.parseDouble(priceString);
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


            if (!exceptionTriggered)
            {
                if (checkOutDateDate.before(parsedDate))
                {
                    exceptionTriggered = true;
                    checkInMessage.setText("Check out date before check in date");
                }
            }

            if (name.isEmpty() || type.isEmpty() || address.isEmpty())
            {
                throw new Exception();
            }

            if (!exceptionTriggered)
            {
                database.addAccommodation(new Accommodation(name,type,address,checkInDate,checkOutDate,price,trip.getTripID()));
                Log.d("AddAccommodationActivity", "Adding accommodation to database was successful");
                ArrayList<Accommodation> accommodationList = database.getAccommodations();
                Log.d("AddAccommodationActivity","First item in accommodation list is "+accommodationList.get(0));
                Intent homePageIntent = new Intent(this, HomePageActivity.class);
                startActivity(homePageIntent);
            }

        }
        catch (Exception e)
        {
            Log.d("AddAccommodationActivity", "Catch block triggered in addAccommodation attempt");
            TextView emptyFieldMessage = findViewById(R.id.checkInDateErrorMessage);
            emptyFieldMessage.setText("Not all fields filled in");
        }


    }

    public void sendToHomePage(View view)
    {
        Intent homePageIntent = new Intent(this, HomePageActivity.class);
        startActivity(homePageIntent);
    }
}