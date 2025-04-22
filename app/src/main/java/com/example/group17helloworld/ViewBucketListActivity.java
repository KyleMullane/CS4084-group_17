package com.example.group17helloworld;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ViewBucketListActivity extends AppCompatActivity {
//    private static DBHandler database;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_view_bucket_list);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        Context context = getApplicationContext();
//        database = DBHandler.getInstance(context);
//        // Menu At Top of the Screen
//        String[] menuItems = {"☰", "Create a Trip", "View Upcoming Trips", "View Past Trips", "View Bucket List"};
//        Spinner menuSpinner = findViewById(R.id.pastSpinner);
//        ArrayAdapter<String> menuSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, menuItems);
//        menuSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        menuSpinner.setAdapter(menuSpinnerAdapter);
//
//
//        menuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//        {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItem = parent.getItemAtPosition(position).toString();
//
//                switch (selectedItem) {
//                    case "Create a Trip":
//                        Log.d("HomePageActivity", "Create a Trip selected");
//                        sendToCreateTripPage();
//                        break;
//                    case "View Upcoming Trips":
//                        Log.d("HomePageActivity", "View Upcoming Trips selected");
//                        sendToHomePage();
//                        break;
//                    case "View Past Trips":
//                        sendToPastTripsPage();
//                        break;
//                    case "View Bucket List":
//                        sendToBucketListPage();
//                        break;
//                    default:
//                        //Nothing
//                        break;
//                }
//            }
//            @Override
//            public void onNothingSelected (AdapterView<?> parent)
//            {
//                // Do nothing
//            }
//        });
//
//        ArrayList<BucketListItem> items = database.getBucketListItems();
//
//        //not sure what to do after this atm
}
