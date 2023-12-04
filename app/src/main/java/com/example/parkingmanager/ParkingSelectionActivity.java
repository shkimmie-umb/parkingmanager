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

import java.util.List;

public class ParkingSelectionActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private Spinner spinner;
    private List<Lot> lots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parkingselectionactivity);

        tableLayout = findViewById(R.id.table_layout);
        spinner = findViewById(R.id.spinner1);


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
                    Lot lot = lots.get(i);
                }
            });
        }

        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"Floor 1", "Floor 2", "Floor 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


}}
