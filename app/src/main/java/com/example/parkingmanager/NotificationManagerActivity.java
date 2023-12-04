package com.example.parkingmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class NotificationManagerActivity extends AppCompatActivity implements View.OnClickListener{
    GlobalData global;
    Button btn_home, btn_approve, btn_confirm_ticket;
    TextView tv_vehicle, tv_pos;
    String vehicle, pos;
    String citation_status;
    citationTableModel citation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_manager);

        global = (GlobalData)getApplication();
        citation = global.getCitationTable();
        vehicle = "The ticketed vehicle " + "\n" + citation.getVehicleNickname() +"\n" + "parked at \n";
        citation_status = citation.getCitationStatus();
        pos = citation.getParkedPos();

        btn_home = (Button)findViewById(R.id.btn_home);
        btn_home.setOnClickListener(this);
        btn_approve = (Button)findViewById(R.id.btn_approve);
        btn_approve.setOnClickListener(this);
        btn_confirm_ticket = (Button)findViewById(R.id.btn_confirm_ticket);
        btn_confirm_ticket.setOnClickListener(this);



        tv_vehicle = (TextView)findViewById(R.id.tv_vehicle);
        tv_vehicle.setText(vehicle);
        tv_pos = (TextView)findViewById(R.id.tv_parkedPos);
        tv_pos.setText(pos);


    }

    @Override
    public void onClick(View v) {
        if (v == btn_home){
            Intent intent = new Intent(getApplicationContext(), HamburgerMenuActivity.class);
            startActivity(intent);
            finish();
        }
        else if(v == btn_approve){
            citation.setCitationStatus("Voided");
            AlertDialog.Builder builder = new AlertDialog.Builder(NotificationManagerActivity.this);

            // Set the message show for the Alert time
            builder.setMessage("You voided the ticket courteously");

            // Set Alert Title
            builder.setTitle("Approved an excuse");

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
        else if(v == btn_confirm_ticket){
            citation.setCitationStatus("Confirmed");
            AlertDialog.Builder builder = new AlertDialog.Builder(NotificationManagerActivity.this);

            // Set the message show for the Alert time
            builder.setMessage("Violation fine is forwarded to the parker");

            // Set Alert Title
            builder.setTitle("Confirmed the ticket");

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
    }
}
