package com.example.parkingmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateParkingCitationActivity extends AppCompatActivity implements View.OnClickListener{
    GlobalData global;

    Button btn_home;
    TextView tv_pass_status;
    TextView tv_pass_status_desc;
    ImageView QR;
    String pass_status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_parkingcitation);



    }

    @Override
    public void onClick(View v) {
        if (v == btn_home){
            Intent intent = new Intent(getApplicationContext(), HamburgerMenuActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
