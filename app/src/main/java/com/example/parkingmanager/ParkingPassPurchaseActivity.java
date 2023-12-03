package com.example.parkingmanager;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TimePicker;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class ParkingPassPurchaseActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonPickTime;
    Button buttonGotoPaymentDetail;
    Intent purchaseConfirmIntent;

    int pickedHour, pickedMinute;

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


        // Timepicker button
        buttonPickTime = (Button) findViewById(R.id.btn_selectTimeSlot);
        // purchase button
        buttonGotoPaymentDetail = (Button) findViewById(R.id.btn_purchase_pass);
//        previewSelectedTimeTextView = findViewById<TextView>(R.id.preview_picked_time_textView)

        buttonPickTime.setOnClickListener(this);
        buttonGotoPaymentDetail.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Date today = new Date();
        if (v == buttonPickTime) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            pickedHour = hourOfDay;
                            pickedMinute = minute;
                        }
                    }, today.getHours()+1, 10, false);
            timePickerDialog.show();

        } else if (v == buttonGotoPaymentDetail) {
            purchaseConfirmIntent = new Intent(getApplicationContext(), PurchaseConfirmationActivity.class);
            purchaseConfirmIntent.putExtra("picked_hour", pickedHour);
            purchaseConfirmIntent.putExtra("picked_minute", pickedMinute);
            startActivity(purchaseConfirmIntent);
            finish();
        }
    }

}