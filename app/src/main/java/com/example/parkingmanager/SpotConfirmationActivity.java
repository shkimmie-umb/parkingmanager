package com.example.parkingmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SpotConfirmationActivity extends AppCompatActivity {
    String spotNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotconfirmation);

        Button home_Btn = findViewById(R.id.home_btn);
        Button payment_Btn = findViewById(R.id.payment_btn);






        payment_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpotConfirmationActivity.this, ParkingPassPurchaseActivity.class);
                startActivity(intent);
            }
        });
        home_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpotConfirmationActivity.this, ParkingSelectionActivity.class);
                startActivity(intent);
            }
        });

        // Get the spot number from the Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("SPOT_NUMBER") && intent.hasExtra("FACILITY_NAME")) {
            spotNumber = intent.getStringExtra("SPOT_NUMBER");
            String facilityName = intent.getStringExtra("FACILITY_NAME");
            Log.d("SpotConfirmationActivity", "Facility Name: " + facilityName);
            intent.putExtra("SPOT_NUMBER", spotNumber);
            intent.putExtra("FACILITY_NAME", facilityName);
//            intent.putExtra("SELECTED_CATEGORY", selectedCategory);

            // Display the facility name and spot number
            TextView titleTextView = findViewById(R.id.textView_title2);
            titleTextView.setText("YOU'VE PARKED AT: " + facilityName);

            TextView spotNumberTextView = findViewById(R.id.textView_spot_num);
            spotNumberTextView.setText("#" + spotNumber);


//            int resId = getResources().getIdentifier("imageViewSpot" + spotNumber, "id", getPackageName());
//            ImageView spotImageView = (ImageView)findViewById(resId);
//            String imgName = String.valueOf(spotImageView.getTag());
//            if(imgName.equals("spot"))
//                payment_Btn.setEnabled(false);

        }

        if (intent.hasExtra("SELECTED_CATEGORY")) {
            String selectedCategory = intent.getStringExtra("SELECTED_CATEGORY");
            Log.d("SpotConfirmationActivity", "Selected Category: " + selectedCategory);
            // Update the floor number TextView
            TextView floorNumberTextView = findViewById(R.id.textView_floor_num);
            floorNumberTextView.setText("Floor: " + selectedCategory);
        }
    }
}
