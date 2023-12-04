package com.example.parkingmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PurchaseConfirmationActivity extends AppCompatActivity implements View.OnClickListener {


    Button btn_checkout;
    TextView startTime;
    TextView endTime;
    TextView tv_duration;

    TextView tv_rate;
    double rate;
    int duration;

    Intent thisIntent;
    Intent checkoutIntent;
    long selected_pass_id;
    String selected_pass;

    private double calculateRate(int duration, long selected_pass_id){
        double rate=0;
        Log.d("Purchase Confirm: ", String.valueOf(selected_pass_id));
        if(selected_pass_id != 0){ // Semester pass
            if (duration < 60){
                rate = 7.00;
                return rate;
            }
            else if(duration>=60 && duration<90){
                rate = 8.00;
                return rate;
            }
            else if(duration >= 90 && duration<120){
                rate = 9.00;
                return rate;
            }
            else if(duration>=120 && duration < 150){
                rate = 10.00;
                return rate;
            }
            else if(duration >= 150 && duration < 180){
                rate = 11.00;
                return rate;
            }
            else if(duration >= 180){
                rate = 15.00;
                return rate;
            }

        }
        else{
            rate = 550.00;
            return rate;
        }

        return rate;
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_detailconfirmation);
        Date today = new Date();

        btn_checkout = (Button)findViewById((R.id.btn_gotoCheckout));
        btn_checkout.setOnClickListener(this);


        SimpleDateFormat startTimeSDF = new SimpleDateFormat("HH:mm:ss z");
        String currentDateAndTime = startTimeSDF.format(today);
        startTime = (TextView)findViewById(R.id.textView_startTime);
        startTime.setText(currentDateAndTime);

        thisIntent = getIntent();
        int pickedHour = thisIntent.getIntExtra("picked_hour", 0);
        int pickedMinute = thisIntent.getIntExtra("picked_minute", 0);
        selected_pass_id = thisIntent.getLongExtra("selected_pass_id", 0);
        selected_pass = thisIntent.getStringExtra("selected_pass");
        Log.d("Purchase Confirmation activity: ", String.valueOf(selected_pass_id));
        SimpleDateFormat endTimeSDF = new SimpleDateFormat("HH:mm:ss z");
        String endDateAndTime = endTimeSDF.format(new Date(today.getYear(), today.getMonth(), today.getDate(),
                pickedHour, pickedMinute));
        endTime = (TextView)findViewById(R.id.textView_endTime);
        endTime.setText(endDateAndTime);

        tv_duration = (TextView)findViewById((R.id.textView_duration));
        SimpleDateFormat durationSDF = new SimpleDateFormat("mm");
        duration = (Math.abs(today.getHours()-pickedHour)*60) + (Math.abs(today.getMinutes()-pickedMinute));
        try {
            Date dt = durationSDF.parse(String.valueOf(duration));
            durationSDF = new SimpleDateFormat("HH:mm");
            durationSDF.format(dt);
            tv_duration.setText(durationSDF.format(dt));

        } catch (ParseException e) {
            e.printStackTrace();
        }


        rate = calculateRate(duration, selected_pass_id);
        tv_rate = (TextView)findViewById(R.id.textView_rate);
        tv_rate.setText("$" + String.format("%.2f", rate));

        // Send data to CheckoutActivity
        checkoutIntent = new Intent(getApplicationContext(), CheckoutActivity.class);
        checkoutIntent.putExtra("calculated_rate", rate);
        checkoutIntent.putExtra("selected_pass_id", selected_pass_id);
        checkoutIntent.putExtra("selected_pass", selected_pass);
        Log.d("(Purchase Confirm act) selected_pass: ", selected_pass);


    }
    @Override
    public void onResume(){
        super.onResume();

        rate = calculateRate(duration, selected_pass_id);

        tv_rate.setText("$" + String.format("%.2f", rate));

    }
    @Override
    public void onClick(View v) {
        if (v == btn_checkout){
//            Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
            startActivity(checkoutIntent);
            finish();
        }
    }
}
