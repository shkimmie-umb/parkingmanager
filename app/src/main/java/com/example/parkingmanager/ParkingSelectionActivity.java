package com.example.parkingmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
                // Do nothing here
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
}
