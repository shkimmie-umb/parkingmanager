package com.example.parkingmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_pay;
    Intent thisIntent;
    Intent PurchaseHistoryIntent;
    double calculated_rate;
    String selected_pass;
    GlobalData global;
    citationTableModel citation;
    ArrayList<purchaseTableModel> passList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_checkout);

        global = (GlobalData)getApplication();


        btn_pay = (Button)findViewById(R.id.btn_proceedToPay);
        btn_pay.setOnClickListener(this);

        // Get calculated rate to send
        thisIntent = getIntent();
        calculated_rate = thisIntent.getDoubleExtra("calculated_rate", 0);
        selected_pass = thisIntent.getStringExtra("selected_pass");

        passList = global.getPurchaseTable();
    }
    @Override
    public void onClick(View v) {
        if (v == btn_pay){
            // Send data to Application (global data)
//            PurchaseHistoryIntent = new Intent(getApplicationContext(), PurchaseHistoryActivity.class);
//            PurchaseHistoryIntent.putExtra("calculated_rate", calculated_rate);

            Date today = new Date();
//            String paid_date = String.valueOf(today.getMonth()).toString() + "/" + String.valueOf(today.getDay()).toString() + "/"
//                    + String.valueOf(today.getYear()).toString();
            SimpleDateFormat todaySDF = new SimpleDateFormat("MM/dd/YYYY");
            String paid_date = todaySDF.format(new Date(today.getYear(), today.getMonth(), today.getDate()));
            global.setCalculated_rate(calculated_rate);
            global.setPaid_date(paid_date);
            global.setSelected_pass(selected_pass);
            global.setParkingpass_status("Valid");
            Log.d("(Checkout act) calculated_rate:", String.valueOf(calculated_rate));
            Log.d("(Checkout act) paid_date:", paid_date);
            Log.d("(Checkout act) selected_pass:", selected_pass);

            citation = global.getCitationTable();
            if(citation.getCitationStatus().equals("Cited") || citation.getCitationStatus().equals("Pending") || citation.getCitationStatus().equals("Appealed") || citation.getCitationStatus() == "Confirmed") {
                citation.setCitationStatus("Paid");
            }

            purchaseTableModel item1, item2, item3, item4, item5;
            if(passList.size() == 0){
                item1 = new purchaseTableModel("Day pass", "Expired", "11/23/2022", "$15.00");
                passList.add(item1);

                item2 = new purchaseTableModel("Day pass", "Expired", "08/24/2022", "$8.00");
                passList.add(item2);

                item3 = new purchaseTableModel("2022 Spring pass", "Expired", "01/02/2022", "$500.00");
                passList.add(item3);

                item4 = new purchaseTableModel("2020 Fall pass", "Expired", "09/02/2020", "$550.00");
                passList.add(item4);

                item5 = new purchaseTableModel("2018 Spring pass", "Expired", "01/12/2018", "$550.00");
                passList.add(item5);
            }

            if(calculated_rate != 0 && selected_pass != null){
                DecimalFormat df = new DecimalFormat("#.00");
                String calculatedRateFormatted = df.format(calculated_rate);


                purchaseTableModel item = new purchaseTableModel(selected_pass, "Active", paid_date, "$" + calculatedRateFormatted);
                passList.add(item);
            }

            // Create dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(CheckoutActivity.this);

            // Set the message show for the Alert time
            builder.setMessage("Your payment is processed");

            // Set Alert Title
            builder.setTitle("Payment Successful");

            // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
            builder.setCancelable(false);

            // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
            builder.setPositiveButton("View my pass", (DialogInterface.OnClickListener) (dialog, which) -> {
                // When the user click yes button then app will close
//                Intent intent = new Intent(getApplicationContext(), HamburgerMenuActivity.class);
//                startActivity(intent);
//                finish();

            });

            // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
            builder.setNegativeButton("Go back to home", (DialogInterface.OnClickListener) (dialog, which) -> {
                // If user click no then dialog box is canceled.
                Intent intent = new Intent(getApplicationContext(), HamburgerMenuActivity.class);
                startActivity(intent);
                finish();
            });

            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();
            // Show the Alert Dialog box
            alertDialog.show();
//            Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
//            startActivity(intent);
//            finish();
        }
    }
}
