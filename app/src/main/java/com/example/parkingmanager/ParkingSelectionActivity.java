package com.example.parkingmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParkingSelectionActivity extends AppCompatActivity {
    private String selectedCategory = "";

    private String selectedFacilityName = "";

    List<ImageView> spotImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parkingselectionactivity);

        // Find views by their IDs
        TextView titleTextView = findViewById(R.id.textView_title2);
        Spinner spinner = findViewById(R.id.spinner1);

        TableLayout tableLayout = findViewById(R.id.table_layout);

        selectedFacilityName = "West Garage";
        selectedCategory = "Floor 1";

// Get the total number of spots for each parking facility
        TextView westGarageTotalSpotsTextView = tableLayout.findViewById(R.id.west_garage_total_spots);
        String westGarageTotalSpotsString = westGarageTotalSpotsTextView.getText().toString();
        int westGarageTotalSpots = Integer.parseInt(westGarageTotalSpotsString);

        TextView lotDTotalSpotsTextView = tableLayout.findViewById(R.id.lot_d_total_spots);
        String lotDTotalSpotsString = lotDTotalSpotsTextView.getText().toString();
        int lotDTotalSpots = Integer.parseInt(lotDTotalSpotsString);

        TextView campusCenterTotalSpotsTextView = tableLayout.findViewById(R.id.campus_center_total_spots);
        String campusCenterTotalSpotsString = campusCenterTotalSpotsTextView.getText().toString();
        int campusCenterTotalSpots = Integer.parseInt(campusCenterTotalSpotsString);

        TextView baysideTotalSpotsTextView = tableLayout.findViewById(R.id.bayside_total_spots);
        String baysideTotalSpotsString = baysideTotalSpotsTextView.getText().toString();
        int baysideTotalSpots = Integer.parseInt(baysideTotalSpotsString);

// Generate a random number for 'Occupied Spots' in each row
        int westGarageOccupiedSpots = new Random().nextInt(westGarageTotalSpots);
        int lotDOccupiedSpots = new Random().nextInt(lotDTotalSpots);
        int campusCenterOccupiedSpots = new Random().nextInt(campusCenterTotalSpots);
        int baysideOccupiedSpots = new Random().nextInt(baysideTotalSpots);

// Calculate the remaining spots and occupancy rate for each row
        int westGarageRemainingSpots = westGarageTotalSpots - westGarageOccupiedSpots;
        float westGarageOccupancyRate = (float) westGarageOccupiedSpots / westGarageTotalSpots * 100;

        int lotDRemainingSpots = lotDTotalSpots - lotDOccupiedSpots;
        float lotDOccupancyRate = (float) lotDOccupiedSpots / lotDTotalSpots * 100;

        int campusCenterRemainingSpots = campusCenterTotalSpots - campusCenterOccupiedSpots;
        float campusCenterOccupancyRate = (float) campusCenterOccupiedSpots / campusCenterTotalSpots * 100;

        int baysideRemainingSpots = baysideTotalSpots - baysideOccupiedSpots;
        float baysideOccupancyRate = (float) baysideOccupiedSpots / baysideTotalSpots * 100;

// Update the remaining spots and occupancy rate for each row
        TextView westGarageOccupiedSpotsTextView = tableLayout.findViewById(R.id.west_garage_occupied_spots);
        westGarageOccupiedSpotsTextView.setText(String.valueOf(westGarageOccupiedSpots));

        TextView westGarageRemainingSpotsTextView = tableLayout.findViewById(R.id.west_garage_remaining_spots);
        westGarageRemainingSpotsTextView.setText(String.valueOf(westGarageRemainingSpots));

        TextView westGarageOccupancyRateTextView = tableLayout.findViewById(R.id.west_garage_occupancy_rate);
        westGarageOccupancyRateTextView.setText(String.format("%.2f%%", westGarageOccupancyRate));

        TextView lotDOccupiedSpotsTextView = tableLayout.findViewById(R.id.lot_d_occupied_spots);
        lotDOccupiedSpotsTextView.setText(String.valueOf(lotDOccupiedSpots));

        TextView lotDRemainingSpotsTextView = tableLayout.findViewById(R.id.lot_d_remaining_spots);
        lotDRemainingSpotsTextView.setText(String.valueOf(lotDRemainingSpots));

        TextView lotDOccupancyRateTextView = tableLayout.findViewById(R.id.lot_d_occupancy_rate);
        lotDOccupancyRateTextView.setText(String.format("%.2f%%", lotDOccupancyRate));

        TextView campusCenterOccupiedSpotsTextView = tableLayout.findViewById(R.id.campus_center_occupied_spots);
        campusCenterOccupiedSpotsTextView.setText(String.valueOf(campusCenterOccupiedSpots));

        TextView campusCenterRemainingSpotsTextView = tableLayout.findViewById(R.id.campus_center_remaining_spots);
        campusCenterRemainingSpotsTextView.setText(String.valueOf(campusCenterRemainingSpots));

        TextView campusCenterOccupancyRateTextView = tableLayout.findViewById(R.id.campus_center_occupancy_rate);
        campusCenterOccupancyRateTextView.setText(String.format("%.2f%%", campusCenterOccupancyRate));

        TextView baysideOccupiedSpotsTextView = tableLayout.findViewById(R.id.bayside_occupied_spots);

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
                R.layout.spinner_list
//                android.R.layout.simple_spinner_item
        );
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.spinner_list);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedCategory = parentView.getItemAtPosition(position).toString();
                selectedFacilityName = parentView.getItemAtPosition(position).toString();

//                for (int i = 1; i <= 36; i++) {
//
//                }

                ///////////

//                List<ImageView> spotImageViews = new ArrayList<>();
//
//                for (int i = 1; i <= 36; i++) {
//                    int resId = getResources().getIdentifier("imageViewSpot" + i, "id", getPackageName());
//
//                    ImageView spotImageView = (ImageView)findViewById(resId);
//                    spotImageView.setOnClickListener(null);
//                    spotImageView.setClickable(false);
//                    spotImageViews.add(spotImageView);
//
//                }
//
//                // spotImageViews.size();
//                for (int i = 0; i < spotImageViews.size(); i++) {
//                    int spotNumber = i + 1;
//
//                    String imgName = String.valueOf(spotImageViews.get(i).getTag());
//                    spotImageViews.get(i).setOnClickListener(null);
//
//                    if((spotNumber != 8 || spotNumber != 9 || spotNumber != 10 || spotNumber != 11
//                            || spotNumber != 14 || spotNumber != 15 || spotNumber != 16 || spotNumber != 17
//                            || spotNumber != 20 || spotNumber != 21 || spotNumber != 22 || spotNumber != 23
//                            || spotNumber != 26 || spotNumber != 27 || spotNumber != 28 || spotNumber != 29)
//                    ) {
//                        spotImageViews.get(i).setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                // Handle ImageView click
//                                if(!imgName.equals("spot")){
//                                    navigateToSpotConfirmationActivity(String.valueOf(spotNumber), selectedFacilityName, selectedCategory);
//                                }
//                            }
//                        });
//
//
//                    }
//
//                    if(Math.round( Math.random() ) == 1 ) {
//                        spotImageViews.get(i).setImageResource(R.drawable.spot);
//                        spotImageViews.get(i).setOnClickListener(null);
//                    }
//                    else if(Math.round( Math.random() ) == 0){
//                        spotImageViews.get(i).setImageResource(R.drawable.table_border);
////                        spotImageViews.get(i-1).setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View v) {
////                                // Handle ImageView click
////
////                                navigateToSpotConfirmationActivity(String.valueOf(spotNumber), selectedFacilityName, selectedCategory);
////                            }
////                        });
//
//                    }
//                }

                ///////////


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

//        List<ImageView> spotImageViews = new ArrayList<>();
        spotImageViews = new ArrayList<>();

        for (int i = 1; i <= 36; i++) {
            int resId = getResources().getIdentifier("imageViewSpot" + i, "id", getPackageName());
            Log.d("Parking", String.valueOf(resId));
            ImageView spotImageView = (ImageView) findViewById(resId);
            spotImageViews.add(spotImageView);
            int int_random = new Random().nextInt(1);

//            if(Math.round( Math.random() ) == 1 ) {
//                spotImageView.setImageResource(R.drawable.spot);
//
//            }
//            else if(Math.round( Math.random() ) == 0){
//                spotImageView.setImageResource(R.drawable.table_border);
//                spotImageView.setOnClickListener(null);
//            }
        }

        // spotImageViews.size();
        for (int i = 0; i < spotImageViews.size(); i++) {
            int spotNumber = i + 1;

            String imgName = String.valueOf(spotImageViews.get(i).getTag());

            if ((spotNumber != 8 || spotNumber != 9 || spotNumber != 10 || spotNumber != 11
                    || spotNumber != 14 || spotNumber != 15 || spotNumber != 16 || spotNumber != 17
                    || spotNumber != 20 || spotNumber != 21 || spotNumber != 22 || spotNumber != 23
                    || spotNumber != 26 || spotNumber != 27 || spotNumber != 28 || spotNumber != 29)
            ) {
                spotImageViews.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle ImageView click
                        if (!imgName.equals("spot")) {
                            navigateToSpotConfirmationActivity(String.valueOf(spotNumber), selectedFacilityName, selectedCategory);
                        }
                    }
                });
            }
        }

//        for (int i = 1; i <= 36; i++) {
//            int resId = getResources().getIdentifier("imageViewSpot" + i, "id", getPackageName());
//            ImageView spotImageView = findViewById(resId);
////            spotImageViews.add(spotImageView);
//
//
//            if(Math.round( Math.random() ) == 1 ) {
//                spotImageView.setImageResource(R.drawable.spot);
//                spotImageView.setClickable(false);
//                spotImageView.setOnClickListener(null);
//
//            }
//            else if(Math.round( Math.random() ) == 0){
//                spotImageView.setImageResource(R.drawable.table_border);
//
//            }
//        }


//        TableRow westGarageRow = findViewById(R.id.table_row_west_garage);
//        westGarageRow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle West Garage row click
//                selectedFacilityName = "West Garage";
//                Toast.makeText(ParkingSelectionActivity.this, "West Garage clicked", Toast.LENGTH_SHORT).show();
////                rearrangeImageViewsForFacility(R.id.imageViewSpot1, R.id.imageViewSpot2, R.id.imageViewSpot3, R.id.imageViewSpot4, R.id.imageViewSpot5);
//                navigateToSpotConfirmationActivity(String.valueOf(spotNumber), selectedFacilityName, selectedCategory);
//            }
//        });
//
//        TableRow lotDRow = findViewById(R.id.table_row_lot_d);
//        lotDRow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle Lot D row click
//                selectedCategory = "Lot D";
//                Toast.makeText(ParkingSelectionActivity.this, "Lot D clicked", Toast.LENGTH_SHORT).show();
////                rearrangeImageViewsForFacility(R.id.imageViewSpot2, R.id.imageViewSpot1, R.id.imageViewSpot3, R.id.imageViewSpot4, R.id.imageViewSpot5);
//                navigateToSpotConfirmationActivity(String.valueOf(spotNumber), selectedCategory, selectedCategory);
//
//            }
//        });
//
//        TableRow campusCenterRow = findViewById(R.id.table_row_campus_center_500);
//        campusCenterRow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle Lot D row click
//                selectedFacilityName = "Campus Center";
//                Toast.makeText(ParkingSelectionActivity.this, "Campus Center clicked", Toast.LENGTH_SHORT).show();
////                rearrangeImageViewsForFacility(R.id.imageViewSpot2, R.id.imageViewSpot1, R.id.imageViewSpot3, R.id.imageViewSpot4, R.id.imageViewSpot5);
//                navigateToSpotConfirmationActivity(String.valueOf(spotNumber), selectedFacilityName, selectedCategory);
//
//            }
//        });
//
//        TableRow baysideRow = findViewById(R.id.table_row_bayside);
//        baysideRow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle Lot D row click
//                selectedFacilityName = "Bayside";
////                Toast.makeText(ParkingSelectionActivity.this, "Bayside clicked", Toast.LENGTH_SHORT).show();
////                rearrangeImageViewsForFacility(R.id.imageViewSpot2, R.id.imageViewSpot1, R.id.imageViewSpot3, R.id.imageViewSpot4, R.id.imageViewSpot5);
//                navigateToSpotConfirmationActivity(String.valueOf(spotNumber), selectedFacilityName, selectedCategory);
//                ;
//            }
//        });

    }


    private void rearrangeImageViewsForFacility(int... imageViewIds) {
        LinearLayout parentLayout = findViewById(R.id.parent_linear_layout);

        if (parentLayout != null) {
            for (int i = 0; i < imageViewIds.length; i++) {
                ImageView imageView = findViewById(imageViewIds[i]);
                if (imageView != null) {
                    parentLayout.removeView(imageView);
                    parentLayout.addView(imageView);
                }
            }
        } else {
            Toast.makeText(this, "Parent layout is null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        for (int i = 1; i <= 36; i++) {
            int resId = getResources().getIdentifier("imageViewSpot" + i, "id", getPackageName());
            ImageView spotImageView = findViewById(resId);
//            spotImageViews.add(spotImageView);


            if (Math.round(Math.random()) == 1) {
                spotImageView.setImageResource(R.drawable.spot);
                spotImageView.setClickable(false);
                spotImageView.setOnClickListener(null);

            } else if (Math.round(Math.random()) == 0) {
                spotImageView.setImageResource(R.drawable.table_border);

            }
        }
    }

    private int spotNumber;

    private void navigateToSpotConfirmationActivity(String spotNumber, String facilityName, String selectedCategory) {
        Intent intent = new Intent(this, SpotConfirmationActivity.class);
        intent.putExtra("SPOT_NUMBER", spotNumber);
        intent.putExtra("FACILITY_NAME", facilityName);
        intent.putExtra("SELECTED_CATEGORY", selectedCategory); // Pass the selected floor
        Log.d("ParkingSelectionActivity", "" + facilityName);
        Log.d("ParkingSelectionActivity", "Category: " + selectedCategory);


        startActivity(intent);
    }

}
