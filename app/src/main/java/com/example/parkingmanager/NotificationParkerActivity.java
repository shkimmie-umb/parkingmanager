package com.example.parkingmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class NotificationParkerActivity extends AppCompatActivity implements View.OnClickListener{
    GlobalData global;
    Button btn_home, btn_reply, btn_pay;
    TextView tv_vehicle, tv_pos;
    String vehicle, pos;
    String citation_status;
    citationTableModel citation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_parker);

        global = (GlobalData)getApplication();
        citation = global.getCitationTable();
        vehicle = "Your vehicle " + "\n" + citation.getVehicleNickname() +"\n" + "parked at \n";
        citation_status = citation.getCitationStatus();
        pos = citation.getParkedPos();

        btn_home = (Button)findViewById(R.id.btn_home);
        btn_home.setOnClickListener(this);
        btn_reply = (Button)findViewById(R.id.btn_correction);
        btn_reply.setOnClickListener(this);
        btn_pay = (Button)findViewById(R.id.btn_pay);
        btn_pay.setOnClickListener(this);

        if(citation_status.equals("Pending")){
            btn_reply.setEnabled(true);
            btn_pay.setEnabled(true);
        }
        else if(citation_status.equals("Confirmed")){
            btn_reply.setEnabled(false);
            btn_pay.setEnabled(true);
        }

        tv_vehicle = (TextView)findViewById(R.id.tv_vehicle);
        tv_vehicle.setText(vehicle);
        tv_pos = (TextView)findViewById(R.id.tv_parkedPos);
        tv_pos.setText(pos);

        if(citation_status.equals("Confirmed")){
            btn_reply.setEnabled(false);
        }


    }

    @Override
    public void onClick(View v) {
        if (v == btn_home){
            Intent intent = new Intent(getApplicationContext(), HamburgerMenuActivity.class);
            startActivity(intent);
            finish();
        }
        else if(v == btn_reply){
            AlertDialog.Builder builder = new AlertDialog.Builder(NotificationParkerActivity.this);

            citation.setCitationStatus("Appealed");

            // Set the message show for the Alert time
            builder.setMessage("Your appeal is sent to the manager");

            // Set Alert Title
            builder.setTitle("Appeal to the manager");

            // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
            builder.setCancelable(false);

            // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
            builder.setPositiveButton("OK", (DialogInterface.OnClickListener) (dialog, which) -> {
                // When the user click yes button then app will close

                Intent intent = new Intent(getApplicationContext(), HamburgerMenuActivity.class);
                startActivity(intent);
                finish();

            });

            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();
            // Show the Alert Dialog box
            alertDialog.show();
        }
        else if(v == btn_pay){
            Intent intent = new Intent(getApplicationContext(), ParkingPassPurchaseActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
