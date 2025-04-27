package com.example.group17helloworld;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class ViewFavoritesActivity extends AppCompatActivity {
    private static DBHandler database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_favorites);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Context context = getApplicationContext();
        database = DBHandler.getInstance(context);
        // Menu At Top of the Screen
        String[] menuItems = {"☰", "Create a Trip", "View Upcoming Trips", "View Past Trips", "View Bucket List", "View Favorites"};
        Spinner menuSpinner = findViewById(R.id.pastSpinner);
        ArrayAdapter<String> menuSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, menuItems);
        menuSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menuSpinner.setAdapter(menuSpinnerAdapter);


        menuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            boolean isFirstSelection = true; // Add this

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isFirstSelection) {
                    isFirstSelection = false;
                    return;
                }

                String selectedItem = parent.getItemAtPosition(position).toString();

                switch (selectedItem) {
                    case "Create a Trip":
                        sendToCreateTripPage();
                        break;
                    case "View Upcoming Trips":
                        sendToHomePage();
                        break;
                    case "View Past Trips":
                        sendToPastTripsPage();
                        break;
                    case "View Bucket List":
                        sendToBucketListPage();
                        break;
                    case "View Favorites":
                        // Already here! Don't do anything
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });


        ArrayList<Trip> trips = database.getFavoriteTrips();

        //Log.d("MainActivity", "Num trips in table is "+trips.size());
        for (int i=0; i<trips.size();i++)
        {
            int index = i;
            String inputDate = trips.get(index).getDateDeparture();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date parsedDate = null;
            try {
                parsedDate = sdf.parse(inputDate);  // Convert string to Date
            } catch (ParseException e) {
                e.printStackTrace();
                Log.d("HomePageActivity", "Date was incorrectly input");
            }
            String todayStr = sdf.format(new Date());
            Date currentDate = new Date();
            try
            {
                currentDate = sdf.parse(todayStr);
            }
            catch (Exception e) {}

            if (currentDate.before(parsedDate)) {
                Log.d("HomePageActivity","The trip with TripID "+trips.get(index).getTripID()+" is upcoming");
            } else if (currentDate.after(parsedDate)) {
                Log.d("HomePageActivity","The trip with TripID "+trips.get(index).getTripID()+" is past");
                //trips.add(trips.get(index));
            } else {
                Log.d("HomePageActivity","The trip with TripID "+trips.get(index).getTripID()+" is today");
            }
        }

        Collections.sort(trips);
        Collections.reverse(trips);
        if (!trips.isEmpty())
        {
            displayFavoriteTrips(trips, context);
        }


    }


    public void displayFavoriteTrips(ArrayList<Trip> favoriteTrips, Context context) {
        TableLayout tableLayout = findViewById(R.id.favoriteTripTable);

        for (int i = 0; i < favoriteTrips.size(); i++) {
            int index = i;

            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            row.setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            row.setPadding(10, 10, 10, 10);

            // Set up the vertical layout
            LinearLayout verticalLayout = new LinearLayout(this);
            verticalLayout.setOrientation(LinearLayout.VERTICAL);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            verticalLayout.setLayoutParams(layoutParams);

            TextView rowText = new TextView(this);
            rowText.setText("Leaving From: " + favoriteTrips.get(i).getDeparture() + "\nGoing to: " + favoriteTrips.get(i).getDestination() + "\nDate: " + favoriteTrips.get(i).getDateDeparture());
            rowText.setPadding(8, 8, 8, 8);

            String[] dropdownItems = {"Options", "Add Transportation", "Add Accommodation", "Add Activities"};
            Spinner spinner = new Spinner(this);
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dropdownItems);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerAdapter);
            spinner.setScaleX(0.7f);
            spinner.setScaleY(0.7f);

            Button viewDetailsButton = new Button(this);
            viewDetailsButton.setText("View Details");
            viewDetailsButton.setTextColor(Color.WHITE);
            viewDetailsButton.setBackgroundResource(R.drawable.custom_button);
            viewDetailsButton.setPadding(0, 0, 0, 0);
            viewDetailsButton.setScaleX(0.5f);
            viewDetailsButton.setScaleY(0.5f);
            viewDetailsButton.setAllCaps(false);
            viewDetailsButton.setOnClickListener(v -> viewTripDetails(favoriteTrips.get(index)));

            // Retrieve the saved comment and locked status from the database
            String savedComment = database.getComment(favoriteTrips.get(i).getTripID());
            boolean isCommentLocked = database.isCommentLocked(favoriteTrips.get(i).getTripID());

            EditText commentEditText = new EditText(this);
            commentEditText.setHint("Add a comment...");
            commentEditText.setText(savedComment);
            commentEditText.setPadding(8, 8, 8, 8);

            if (isCommentLocked) {
                // Disable the EditText if the comment is locked
                commentEditText.setFocusable(false);
                commentEditText.setClickable(false);
                commentEditText.setBackgroundColor(Color.LTGRAY); // Optional: Change background color to indicate it's non-editable
            }

            Button saveCommentButton = new Button(this);
            saveCommentButton.setText("Save Comment");
            saveCommentButton.setTextSize(12);
            saveCommentButton.setBackgroundResource(R.drawable.custom_button);
            saveCommentButton.setTextColor(Color.WHITE);
            saveCommentButton.setScaleX(0.7f);
            saveCommentButton.setScaleY(0.7f);
            saveCommentButton.setAllCaps(false);

            // Hide the save button if the comment is already saved and locked
            if (!savedComment.isEmpty() && isCommentLocked) {
                saveCommentButton.setVisibility(View.GONE); // Hide button if comment is locked
            } else {
                saveCommentButton.setVisibility(View.VISIBLE); // Show button if comment is not saved or locked
            }

            saveCommentButton.setOnClickListener(v -> {
                String comment = commentEditText.getText().toString().trim();

                if (!comment.isEmpty() && !isCommentLocked) {
                    // Save the comment to the database and lock it
                    try {
                        database.addComment(favoriteTrips.get(index), comment);

                        // Disable the EditText after saving
                        commentEditText.setFocusable(false);
                        commentEditText.setClickable(false);
                        commentEditText.setBackgroundColor(Color.LTGRAY); // Optional: Change background color

                        // Hide the save button
                        saveCommentButton.setVisibility(View.GONE); // Hide save button

                        // Optionally, change the hint text
                        commentEditText.setHint("Comment Saved");

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            // Add everything to the vertical layout
            verticalLayout.addView(rowText);
            verticalLayout.addView(viewDetailsButton);
            verticalLayout.addView(commentEditText);
            verticalLayout.addView(saveCommentButton);

            // Add the vertical layout to the row
            row.addView(verticalLayout);
            row.addView(spinner);

            // Finally, add the row to the table layout
            tableLayout.addView(row);
        }
    }



    public void viewTripDetails(Trip trip)
    {
        Intent intent = new Intent(this, ViewTripDetails.class);
        intent.putExtra("TripID", trip.getTripID());
        startActivity(intent);
    }

    public void sendToHomePage()
    {
        Intent homePageIntent = new Intent(this, HomePageActivity.class);
        startActivity(homePageIntent);
    }

    public void sendToCreateTripPage()
    {
        Intent createTripIntent = new Intent(this, CreateATripActivity.class);
        startActivity(createTripIntent);
    }

    public void sendToPastTripsPage()
    {
        Intent pastTripsIntent = new Intent(this, ViewPastTripsActivity.class);
        startActivity(pastTripsIntent);
    }

    public void sendToBucketListPage()
    {
        Intent bucketListIntent = new Intent(this, ViewBucketListActivity.class);
        startActivity(bucketListIntent);
    }

    public void sendToFavoritesPage(){
        Intent favoritesIntent = new Intent(this, ViewFavoritesActivity.class);
        startActivity(favoritesIntent);
    }
}