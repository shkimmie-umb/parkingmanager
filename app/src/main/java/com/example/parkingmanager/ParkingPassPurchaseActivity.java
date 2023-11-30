package com.example.parkingmanager;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

public class ParkingPassPurchaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_parkingpass);

        String[] carArray = getResources().getStringArray(R.array.car_list);
        ArrayAdapter<String> arrayAdapter_car = new ArrayAdapter<String>(this,
                R.layout.dropdown_style, carArray);

        AutoCompleteTextView actv_car = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView_car);
        actv_car.setAdapter(arrayAdapter_car);

        String[] passArray = getResources().getStringArray(R.array.pass_type);
        ArrayAdapter<String> arrayAdapter_pass = new ArrayAdapter<String>(this,
                R.layout.dropdown_style, passArray);

        AutoCompleteTextView actv_pass = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView_pass);
        actv_pass.setAdapter(arrayAdapter_pass);
    }
}