package com.example.group17helloworld;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.view.View;
import android.widget.*;
import androidx.core.content.ContextCompat;

import com.example.group17helloworld.DBHandler;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static DBHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        Context context = getApplicationContext();
        Log.d("MainActivity", "Testing to see if printing to the terminal works for debugging purposes!");
        database = DBHandler.getInstance(context);
        try {
            //database.addTrip(new Trip(0, "Ireland", "Switzerland", "21/3/2025", "24/3/2025", 500.0));
        } catch (Exception e) {
            Log.d("MainActivity", "Catch block triggered in addTrip attempt");
        }

        ArrayList<Trip> trips = database.getTrips();
        Trip testTrip = trips.get(0);
        Log.d("MainActivity", "Num trips in table is "+trips.size());
        //TextView tripText = findViewById(R.id.tripText);
        //tripText.setText("Leaving From: "+testTrip.getDeparture()+ "\nGoing to: "+testTrip.getDestination()+"\nDate: "+testTrip.getDateDeparture());

        TableLayout tableLayout = findViewById(R.id.tripTable);

        for (int i=0; i<trips.size(); i++)
        {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            row.setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            TextView rowText = new TextView(this);
            rowText.setText("Leaving From: "+trips.get(i).getDeparture()+"\nGoing to: "+trips.get(i).getDestination()+"\nDate: "+trips.get(i).getDateDeparture());
            rowText.setPadding(8, 8, 8, 8);
            row.addView(rowText);
            tableLayout.addView(row);
        }

    }
}