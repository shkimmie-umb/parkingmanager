package com.example.parkingmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

public class PurchaseConfirmationActivity extends AppCompatActivity implements View.OnClickListener {


    Button checkout;
    TextView startTime;
    TextView endTime;
    TextView tv_duration;

    TextView tv_rate;
    double rate;

    Intent thisIntent;

    private double calculateRate(int duration){
        double rate=0;
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
        return rate;
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_detailconfirmation);
        Date today = new Date();


        SimpleDateFormat startTimeSDF = new SimpleDateFormat("HH:mm:ss z");
        String currentDateAndTime = startTimeSDF.format(today);
        startTime = (TextView)findViewById(R.id.textView_startTime);
        startTime.setText(currentDateAndTime);

        thisIntent = getIntent();
        int pickedHour = thisIntent.getIntExtra("picked_hour", 0);
        int pickedMinute = thisIntent.getIntExtra("picked_minute", 0);
        SimpleDateFormat endTimeSDF = new SimpleDateFormat("HH:mm:ss z");
        String endDateAndTime = endTimeSDF.format(new Date(today.getYear(), today.getMonth(), today.getDate(),
                pickedHour, pickedMinute));
        endTime = (TextView)findViewById(R.id.textView_endTime);
        endTime.setText(endDateAndTime);

        tv_duration = (TextView)findViewById((R.id.textView_duration));
        SimpleDateFormat durationSDF = new SimpleDateFormat("mm");
        int duration = (Math.abs(today.getHours()-pickedHour)*60) + (Math.abs(today.getMinutes()-pickedMinute));
        try {
            Date dt = durationSDF.parse(String.valueOf(duration));
            durationSDF = new SimpleDateFormat("HH:mm");
            durationSDF.format(dt);
            tv_duration.setText(durationSDF.format(dt));

        } catch (ParseException e) {
            e.printStackTrace();
        }


        rate = calculateRate(duration);
        tv_rate = (TextView)findViewById(R.id.textView_rate);
        tv_rate.setText("$" + String.format("%.2f", rate));


    }
    @Override
    public void onClick(View v) {
        if (v == checkout){
//            Intent intent = new Intent(getApplicationContext(), ParkingPassPurchaseActivity.class);
//            startActivity(intent);
//            finish();
        }
    }
}
