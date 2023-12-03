package com.example.parkingmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_checkout);

        btn_pay = (Button)findViewById(R.id.btn_proceedToPay);
        btn_pay.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == btn_pay){
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
