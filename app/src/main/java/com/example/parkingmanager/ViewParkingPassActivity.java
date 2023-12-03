package com.example.parkingmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ViewParkingPassActivity extends AppCompatActivity implements View.OnClickListener{
    GlobalData global;

    Button btn_home;
    TextView tv_pass_status;
    TextView tv_pass_status_desc;
    ImageView QR;
    String pass_status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_parkingpass);

        btn_home = (Button)findViewById(R.id.btn_home);
        btn_home.setOnClickListener(this);

        QR = (ImageView)findViewById(R.id.parkingpass_QR);
        tv_pass_status = (TextView)findViewById(R.id.tv_parkingpass_status);
        tv_pass_status_desc = (TextView)findViewById(R.id.tv_parkingpass_status_desc);



        global = (GlobalData)getApplication();
        pass_status = global.getParkingpass_status();
        tv_pass_status.setText(pass_status);

        if(tv_pass_status.getText().equals("Valid")  || tv_pass_status.getText().equals("Active")){
            QR.setImageResource(R.drawable.qr);
            tv_pass_status_desc.setText("Please scan your parking pass at the entrance");
        }


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
