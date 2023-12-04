package com.example.parkingmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import com.example.parkingmanager.Lot;

public class ParkingSelectionActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private Spinner spinner;
    private List<Lot> lots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parkingselectionactivity);

        tableLayout = findViewById(R.id.table_layout);
        spinner = findViewById(R.id.spinner);

//        lots = generateRandomLotData();

        populateTable();

        if (spinner.getSelectedItem().toString().equals("West Garage")) {
            spinner.setEnabled(true);
        } else {
            spinner.setEnabled(false);
        }

        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            View row = tableLayout.getChildAt(i);
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Get the lot object for the clicked row
                    Lot lot = lots.get(i);

                    // Generate a new interactive map for the lot
                    generateInteractiveMap(lot);
                }
            });
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected lot
                Lot lot = lots.get(position);

                // Enable or disable the dropdown list based on the selected lot
                if (lot.getName().equals("West Garage")) {
                    spinner.setEnabled(true);
                } else {
                    spinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void generateInteractiveMap(Lot lot) {
        Intent intent = new Intent(this, InteractiveMapActivity.class);

        intent.putExtra("lot", lot);

        startActivity(intent);
    }

    private void populateTable() {
        for (Lot lot : lots) {
            View row = LayoutInflater.from(this).inflate(R.layout.table_row, tableLayout, false);

            TextView lotNameTextView = row.findViewById(R.id.lot_name_text_view);
            TextView occupancyRateTextView = row.findViewById(R.id.occupancy_rate_text_view);

            lotNameTextView.setText(lot.getName());
            occupancyRateTextView.setText(String.valueOf(lot.getOccupancyRate())); // Assuming occupancyRate is a double

            tableLayout.addView(row);
        }
    }

}
