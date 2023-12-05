package com.example.parkingmanager;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TimePicker;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class ParkingPassPurchaseActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_home;
    Button buttonPickTime;
    Button buttonGotoPaymentDetail;
    Intent purchaseConfirmIntent;
    Intent checkoutIntent;
    String selectedPass;
    long selectedPassId;
    String selectedCar;
    long selectedCarId;

    RadioButton oncampus, offcampus;

    int pickedHour=0;
    int pickedMinute=0;

    AutoCompleteTextView actv_car;
    AutoCompleteTextView actv_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_parkingpass);

        // Timepicker button
        buttonPickTime = (Button) findViewById(R.id.btn_selectTimeSlot);
        // Home button
        btn_home = (Button) findViewById(R.id.btn_home);
        // purchase button
        buttonGotoPaymentDetail = (Button) findViewById(R.id.btn_purchase_pass);
//        previewSelectedTimeTextView = findViewById<TextView>(R.id.preview_picked_time_textView)

        oncampus = (RadioButton)findViewById(R.id.radio_oncampus);
        offcampus = (RadioButton)findViewById(R.id.radio_offcampus);

        buttonPickTime.setOnClickListener(this);
        btn_home.setOnClickListener(this);
        buttonGotoPaymentDetail.setOnClickListener(this);

        // In default, set picktime button disabled
        buttonPickTime.setEnabled(false);

        String[] carArray = getResources().getStringArray(R.array.car_list);
        ArrayAdapter<String> arrayAdapter_car = new ArrayAdapter<String>(this,
                R.layout.dropdown_style, carArray);

        actv_car = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView_car);
        actv_car.setAdapter(arrayAdapter_car);

        String[] passArray = getResources().getStringArray(R.array.pass_type);
        ArrayAdapter<String> arrayAdapter_pass = new ArrayAdapter<String>(this,
                R.layout.dropdown_style, passArray);

        actv_pass = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView_pass);
        actv_pass.setAdapter(arrayAdapter_pass);


        // Define car selection listener
        actv_car.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
//                Log.d("TAG", actv_pass.getText().toString());
                selectedCarId = id;
                selectedCar = actv_car.getText().toString();

            }
        });


        // Define pass selection listener
        actv_pass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                Log.d("Purchase id", String.valueOf(id));
                Log.d("Purchase pos", String.valueOf(position));
                selectedPassId = id;
                selectedPass = actv_pass.getText().toString();
                if(id==1) { // Only Day pass is to pick time

                    buttonPickTime.setEnabled(true);
                }
                else if(id==0) {
                    buttonPickTime.setEnabled(false);
                }
            }
        });




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

            if(selectedCar != null && selectedPass != null && (oncampus.isChecked() || offcampus.isChecked())){
                // Sending data to calculate the amount to pay in PurchaseConfirmationActivity activity
                purchaseConfirmIntent = new Intent(getApplicationContext(), PurchaseConfirmationActivity.class);
                purchaseConfirmIntent.putExtra("picked_hour", pickedHour);
                purchaseConfirmIntent.putExtra("picked_minute", pickedMinute);
                purchaseConfirmIntent.putExtra("selected_car_id", selectedCarId);
                purchaseConfirmIntent.putExtra("selected_car", selectedCar);
                purchaseConfirmIntent.putExtra("selected_pass_id", selectedPassId);
                purchaseConfirmIntent.putExtra("selected_pass", selectedPass);
                startActivity(purchaseConfirmIntent);
                finish();
            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(ParkingPassPurchaseActivity.this);

                // Set the message show for the Alert time
                builder.setMessage("Please select car, parking pass type, and on/off campus");

                // Set Alert Title
                builder.setTitle("Required fields error");

                // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                builder.setCancelable(false);

                // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                builder.setPositiveButton("OK", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // When the user click yes button then app will close


                });

                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();
                // Show the Alert Dialog box
                alertDialog.show();
            }

        }
        else if(v == btn_home){
            if (v == btn_home){
                Intent intent = new Intent(getApplicationContext(), HamburgerMenuActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

}