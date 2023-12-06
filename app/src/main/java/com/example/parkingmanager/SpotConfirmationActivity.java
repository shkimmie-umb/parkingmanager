package com.example.parkingmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SpotConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotconfirmation);

        Button home_Btn = findViewById(R.id.home_btn);


        home_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpotConfirmationActivity.this, HamburgerMenuActivity.class);
                startActivity(intent);
            }
        });

        // Get the spot number from the Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("SPOT_NUMBER") && intent.hasExtra("FACILITY_NAME")) {
            String spotNumber = intent.getStringExtra("SPOT_NUMBER");
            String facilityName = intent.getStringExtra("FACILITY_NAME");

            // Display the facility name and spot number
            TextView titleTextView = findViewById(R.id.textView_title2);
            titleTextView.setText("YOU'VE PARKED AT " + facilityName + ":");

            TextView spotNumberTextView = findViewById(R.id.textView_spot_num);
            spotNumberTextView.setText("#" + spotNumber);

            TextView floorNumberTextView = findViewById(R.id.textView_floor_num);
            floorNumberTextView.setText("Floor:" +floorNumberTextView);
        }
        if (intent != null && intent.hasExtra("SELECTED_CATEGORY")) {
            String selectedCategory = intent.getStringExtra("SELECTED_CATEGORY");

            // Update the floor number TextView
            TextView floorNumberTextView = findViewById(R.id.textView_floor_num);
            floorNumberTextView.setText("Floor: " + selectedCategory);
        }
    }
}