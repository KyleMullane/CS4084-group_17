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
        database = new DBHandler(context);
        User jason = new User("jmcgettrick","1234");
        try
       {
            database.addUser(jason);
        }
        catch (Exception e)
        {
            Log.e("MainActivity","Error: user already exists!");
        }



        ArrayList<User> users = new ArrayList<User>();
        users = database.getUsers();
        for (int i=0; i<users.size(); i++)
        {
            Log.d("MainActivity", "Names in user database are: "+users.get(i).getUsername());
        }
    }

    public void logIn(View view)
    {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }
}