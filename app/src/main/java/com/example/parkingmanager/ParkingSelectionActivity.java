package com.example.parkingmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ParkingSelectionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parkingselectionactivity);

        // Find views by their IDs
        TextView titleTextView = findViewById(R.id.textView_title2);
        Spinner spinner = findViewById(R.id.spinner1);
        ImageView imageViewSpot1 = findViewById(R.id.imageViewSpot1);
        ImageView imageViewSpot2 = findViewById(R.id.imageViewSpot2);
        ImageView imageViewSpot3 = findViewById(R.id.imageViewSpot3);
        ImageView imageViewSpot4 = findViewById(R.id.imageViewSpot4);
        ImageView imageViewSpot5 = findViewById(R.id.imageViewSpot5);

        Button backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add logic to navigate to HamburgerMenuActivity
                Intent intent = new Intent(ParkingSelectionActivity.this, HamburgerMenuActivity.class);
                startActivity(intent);
            }
        });

        titleTextView.setText("Select a Parking Spot");

        // Setup spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.parking_categories,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedCategory = parentView.getItemAtPosition(position).toString();
                // Handle spinner item selection
                Toast.makeText(ParkingSelectionActivity.this, "Selected Category: " + selectedCategory, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        imageViewSpot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle ImageView click
                navigateToSpotConfirmationActivity("1"); // Pass the spot number
            }
        });

        imageViewSpot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle ImageView click
                navigateToSpotConfirmationActivity("2"); // Pass the spot number
            }
        });

        imageViewSpot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle ImageView click
                navigateToSpotConfirmationActivity("3"); // Pass the spot number
            }
        });

        imageViewSpot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle ImageView click
                navigateToSpotConfirmationActivity("4"); // Pass the spot number
            }
        });

        imageViewSpot5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle ImageView click
                navigateToSpotConfirmationActivity("5");
            }
        });

        TableRow westGarageRow = findViewById(R.id.table_row_west_garage);
        westGarageRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle West Garage row click
                Toast.makeText(ParkingSelectionActivity.this, "West Garage clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToSpotConfirmationActivity(String spotNumber) {
        Intent intent = new Intent(this, SpotConfirmationActivity.class);
        intent.putExtra("SPOT_NUMBER", spotNumber);
        startActivity(intent);
    }
}

