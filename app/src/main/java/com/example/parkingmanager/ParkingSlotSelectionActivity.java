package com.example.parkingmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ParkingSlotSelectionActivity extends AppCompatActivity {

    private TextView parkingFacilitiesTextView;
    private Button westGarageButton;
    private Button lotDButton;
    private Button campusCenter500Button;
    private Button baysideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parkingFacilitiesTextView = findViewById(R.id.parking_facilities_textview);
        westGarageButton = findViewById(R.id.west_garage_button);
        lotDButton = findViewById(R.id.lot_d_button);
        campusCenter500Button = findViewById(R.id.campus_center_button);
        baysideButton = findViewById(R.id.bayside_button);

        westGarageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open the West Garage activity
                Intent intent = new Intent(ParkingSlotSelectionActivity.this, WestGarageActivity.class);
                startActivity(intent);
            }
        });

        lotDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open the Lot D activity
                Intent intent = new Intent(ParkingSlotSelectionActivity.this, LotDActivity.class);
                startActivity(intent);
            }
        });

        campusCenter500Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open the Campus Center 500 activity
                Intent intent = new Intent(ParkingSlotSelectionActivity.this, CampusCenterActivity.class);
                startActivity(intent);
            }
        });

        baysideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open the Bayside activity
                Intent intent = new Intent(ParkingSlotSelectionActivity.this, BaysideActivity.class);
                startActivity(intent);
            }
        });
    }
}
