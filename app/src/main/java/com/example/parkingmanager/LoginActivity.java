package com.example.parkingmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    Button signin;
    EditText et_ID;
    EditText et_PW;
    GlobalData global;
    String loggedin_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        global = (GlobalData)getApplication();

        et_ID = (EditText)findViewById(R.id.et_ID);
        et_PW = (EditText)findViewById(R.id.et_PW);


        et_ID.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        InputMethodManager imm_ID = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm_ID.hideSoftInputFromWindow(et_ID.getWindowToken(), 0);

        et_PW.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


        signin = (Button)findViewById(R.id.btn_signin);
        signin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == signin){
            loggedin_ID = et_ID.getText().toString();
            global.setLoggedin_ID(loggedin_ID);
            Intent intent = new Intent(getApplicationContext(), HamburgerMenuActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
