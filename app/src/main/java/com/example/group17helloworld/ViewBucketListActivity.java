package com.example.group17helloworld;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ViewBucketListActivity extends AppCompatActivity {
    private static DBHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_bucket_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Context context = getApplicationContext();
        database = DBHandler.getInstance(context);

        // Menu At Top of the Screen
        String[] menuItems = {"☰", "Create a Trip", "View Upcoming Trips", "View Past Trips", "View Bucket List", "View Favorites", "View Statistics", "Main Menu"};
        Spinner menuSpinner = findViewById(R.id.pastSpinner);
        ArrayAdapter<String> menuSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, menuItems);
        menuSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menuSpinner.setAdapter(menuSpinnerAdapter);


        menuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                switch (selectedItem) {
                    case "Create a Trip":
                        Log.d("HomePageActivity", "Create a Trip selected");
                        sendToCreateTripPage();
                        break;
                    case "View Upcoming Trips":
                        Log.d("HomePageActivity", "View Upcoming Trips selected");
                        sendToHomePage();
                        break;
                    case "View Past Trips":
                        sendToPastTripsPage();
                        break;
                    case "View Bucket List":
                        sendToBucketListPage();
                        break;
                    case "View Favorites":
                        sendToFavoritesPage();
                        break;
                    case "View Statistics":
                        sendToStatsPage();
                        break;
                    case "Main Menu":
                        sendToMainMenu();
                    default:
                        //Nothing
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        //Display checklist
        LinearLayout bucketListLayout = findViewById(R.id.bucketListLayout); //make sure this exists in xml

        ArrayList<BucketListItem> items = database.getBucketListItems();

        for (BucketListItem item : items) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(item.getItem());
            checkBox.setChecked(item.getStatus());

            Log.d("CHECKBOX_CREATION", "Creating checkbox for item: " + item.getItem());
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                Log.d("CHECKBOX_STATUS", "Checkbox clicked: " + isChecked);
                //item.setStatus(isChecked ? 1 : 0); //how do i change isChecked to handle 1s or 0s?
                if (isChecked)
                {
                    item.setStatus(1);
                }
                else
                {
                    item.setStatus(0);
                }
                database.changeStatus(item);
                checkBox.setChecked(item.getStatus());
                //bucketListLayout.addView(checkBox);
            });
            //checkBox.setChecked(item.getStatus());
            Button deletecheckBoxButton = new Button(this);
            deletecheckBoxButton.setText("Delete");
            deletecheckBoxButton.setTextColor(Color.WHITE); // Change text color
            deletecheckBoxButton.setBackgroundResource(R.drawable.delete_button);
            deletecheckBoxButton.setPadding(0, 0, 0, 0); // Adjust padding
            deletecheckBoxButton.setScaleX(0.5f); // Scale width to 80%
            deletecheckBoxButton.setScaleY(0.5f);
            deletecheckBoxButton.setAllCaps(false);
            LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(200, 100);
            deletecheckBoxButton.setLayoutParams(buttonParams);
            deletecheckBoxButton.setOnClickListener(v -> deleteItem(item.getID()));

            bucketListLayout.addView(checkBox);
            bucketListLayout.addView(deletecheckBoxButton);
        }

        // Add new item input
        LinearLayout newItemLayout = new LinearLayout(this);
        newItemLayout.setOrientation(LinearLayout.HORIZONTAL);

        EditText newItemEditText = new EditText(this);
        newItemEditText.setHint("Add new item");
        newItemEditText.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        Button addButton = new Button(this);
        addButton.setText("Add");

        boolean clicked = false;
        addButton.setOnClickListener(v -> {
            String description = newItemEditText.getText().toString().trim();
            if (!description.isEmpty()) {
                BucketListItem newItem = new BucketListItem(description, 0); // Constructor must match
                try {
                   int id = database.addItem(newItem);
                   newItem.setID(id);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


                CheckBox newCheckBox = new CheckBox(this);
                newCheckBox.setText(description);
                newCheckBox.setChecked(false); //idk if this will work cuz of booleans & 0/1s

                newCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    newItem.setStatus(1); //idk
                    database.changeStatus(newItem);
                });

                bucketListLayout.addView(newCheckBox, bucketListLayout.getChildCount() - 1); // before input
                newItemEditText.setText("");
            }
        });


        newItemLayout.addView(newItemEditText);
        newItemLayout.addView(addButton);
        bucketListLayout.addView(newItemLayout);
    }
    private void sendToCreateTripPage() {
        Intent createTripIntent = new Intent(this, CreateATripActivity.class);
        startActivity(createTripIntent);
    }
    private void sendToHomePage() {
        Intent homePageIntent = new Intent(this, HomePageActivity.class);
        startActivity(homePageIntent);
    }
    private void sendToPastTripsPage() {
        Intent pastTripsIntent = new Intent(this, ViewPastTripsActivity.class);
        startActivity(pastTripsIntent);
    }
    private void sendToBucketListPage() {
        Intent bucketListIntent = new Intent(this, ViewBucketListActivity.class);
        startActivity(bucketListIntent);
    }

    private void sendToFavoritesPage() {
        Intent favoritesIntent = new Intent(this, ViewFavoritesActivity.class);
        startActivity(favoritesIntent);
    }

    public void sendToMainMenu()
    {
        Intent mainMenuIntent = new Intent(this, MainActivity.class);
        startActivity(mainMenuIntent);
    }

    public void sendToStatsPage()
    {
        Intent statsPageIntent = new Intent(this, ViewStatsActivity.class);
        startActivity(statsPageIntent);
    }

    public void deleteItem(int ID)
    {
        database.deleteBucketListItem(ID);
        sendToBucketListPage();
    }
}
