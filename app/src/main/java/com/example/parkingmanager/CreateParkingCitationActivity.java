package com.example.parkingmanager;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CreateParkingCitationActivity extends AppCompatActivity implements View.OnClickListener{
    GlobalData global;

    Button btn_img_upload;
    Button btn_home, btn_notify, btn_create_citation;
    EditText citation_reason;

    String selectedPlate, selectedViolationType;
    long selectedPlateId, selectedViolationTypeId;
    citationTableModel citation;
    boolean image_uploaded = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_parkingcitation);

        global = (GlobalData)getApplication();

        btn_img_upload = (Button)findViewById(R.id.btn_upload_violation_img);
        btn_home = (Button)findViewById(R.id.btn_home);
        btn_notify = (Button)findViewById(R.id.btn_notify);
        btn_create_citation = (Button)findViewById(R.id.btn_create_citation);
        btn_img_upload.setOnClickListener(this);
        btn_home.setOnClickListener(this);
        btn_notify.setOnClickListener(this);
        btn_create_citation.setOnClickListener(this);

        String[] plateArray = getResources().getStringArray(R.array.plate_list);
        ArrayAdapter<String> arrayAdapter_plate = new ArrayAdapter<String>(this,
                R.layout.dropdown_style, plateArray);

        AutoCompleteTextView actv_plate = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView_plate);
        actv_plate.setAdapter(arrayAdapter_plate);

        // Define car selection listener
        actv_plate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
//                Log.d("TAG", actv_pass.getText().toString());
                selectedPlateId = id;
                selectedPlate = actv_plate.getText().toString();


            }
        });

        String[] citationTypeArray = getResources().getStringArray(R.array.citation_type_list);
        ArrayAdapter<String> arrayAdapter_citationType = new ArrayAdapter<String>(this,
                R.layout.dropdown_style, citationTypeArray);

        AutoCompleteTextView actv_citation_type = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView_violation_types);
        actv_citation_type.setAdapter(arrayAdapter_citationType);

        // Define violation type listener
        actv_citation_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
//                Log.d("TAG", actv_pass.getText().toString());
                selectedViolationTypeId = id;
                selectedViolationType = actv_citation_type.getText().toString();

                if(id == 4){
                    citation_reason.setEnabled(true);
                }
                else{
                    citation_reason.setEnabled(false);
                }


            }
        });

        citation_reason = (EditText)findViewById(R.id.et_citation_reason);
        citation_reason.setEnabled(false);
        citation_reason.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == btn_home){
            Intent intent = new Intent(getApplicationContext(), HamburgerMenuActivity.class);
            startActivity(intent);
            finish();
        }
        else if(v == btn_img_upload){
            ImageView violation_img = (ImageView)findViewById(R.id.iv_violation);
            violation_img.setImageResource(R.drawable.violation_img);
            image_uploaded = true;
        }
        else if(v == btn_notify){
            if(selectedPlate != null && selectedViolationType != null && image_uploaded == true){
                citation = new citationTableModel(selectedPlate, selectedPlate, "West Garage, Floor 3, #13");

                global.setCitationTable(citation);

                citationTableModel citation_cp = global.getCitationTable();
                citation_cp.setCitationStatus("Pending");
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateParkingCitationActivity.this);

                // Set the message show for the Alert time
                builder.setMessage("Your notification is sent to the parker");

                // Set Alert Title
                builder.setTitle("Notify the parker");

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
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateParkingCitationActivity.this);

                // Set the message show for the Alert time
                builder.setMessage("Please select plate, violation type, and upload image");

                // Set Alert Title
                builder.setTitle("Required fields error");

                // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                builder.setCancelable(false);

                // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                builder.setPositiveButton("OK", (DialogInterface.OnClickListener) (dialog, which) -> {
                });

                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();
                // Show the Alert Dialog box
                alertDialog.show();
            }

        }
        else if(v == btn_create_citation){
            if(selectedPlate != null && selectedViolationType != null && image_uploaded == true){
                citation = new citationTableModel(selectedPlate, selectedPlate, "West Garage, Floor 3, #13");

                global.setCitationTable(citation);

                citationTableModel citation_cp = global.getCitationTable();
                citation_cp.setCitationStatus("Confirmed");
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateParkingCitationActivity.this);

                // Set the message show for the Alert time
                builder.setMessage("Your ticket is sent to the parker");

                // Set Alert Title
                builder.setTitle("Ticketed the parker");

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
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateParkingCitationActivity.this);

                // Set the message show for the Alert time
                builder.setMessage("Please select a plate and a violation type");

                // Set Alert Title
                builder.setTitle("Required fields error");

                // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                builder.setCancelable(false);

                // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                builder.setPositiveButton("OK", (DialogInterface.OnClickListener) (dialog, which) -> {
                });

                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();
                // Show the Alert Dialog box
                alertDialog.show();
            }

        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
