package com.example.group17helloworld;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Context;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private static DBHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //database.deleteTripTable();
        //database.createTripTable();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toast toast = Toast.makeText(getApplicationContext(), "onCreate Called", Toast.LENGTH_LONG);
        toast.show();

        //database.deleteTripTable();
        //database.createTripTable();

        Context context = getApplicationContext();
        Log.d("MainActivity", "Testing to see if printing to the terminal works for debugging purposes!");
        database = DBHandler.getInstance(context);

//        database.deleteBucketListTable();
//        database.deleteActivityTable();
//        database.deleteAccommodationTable();
//        database.deleteTransportationTable();
//        database.deleteTripTable();

        //database.createTripTable();
//        database.createTripTable();
//        database.createTransportationTable();
//        database.createActivitiesTable();
//        database.createAccommodationTable();
//        database.createBucketListTable();

    }
    public void sendToHomePage(View view)
    {
        Intent homePageIntent = new Intent(this, HomePageActivity.class);
        startActivity(homePageIntent);
    }

    public void sendToCreateTripPage(View view)
    {
        Intent createTripIntent = new Intent(this, CreateATripActivity.class);
        startActivity(createTripIntent);
    }

    public void sendToPastTripsPage(View view)
    {
        Intent pastTripsIntent = new Intent(this, ViewPastTripsActivity.class);
        startActivity(pastTripsIntent);
    }

    public void sendToBucketListPage(View view)
    {
        Intent bucketListIntent = new Intent(this, ViewBucketListActivity.class);
        startActivity(bucketListIntent);
    }

    public void sendToFavoritesPage(View view){
        Intent favoritesIntent = new Intent(this, ViewFavoritesActivity.class);
        startActivity(favoritesIntent);
    }
}