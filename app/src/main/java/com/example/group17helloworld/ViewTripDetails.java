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

public class ViewTripDetails extends AppCompatActivity {
    private static DBHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_trip_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    Context context = getApplicationContext();
    database = DBHandler.getInstance(context);
    Intent intent = getIntent();
    int tripID = intent.getIntExtra("TripID");
    TextView target = findViewById(R.id.greetingView);
    target.setText("Hello, " + name + "!");


}