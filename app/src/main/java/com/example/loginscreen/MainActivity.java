<<<<<<< HEAD:app/src/main/java/com/example/loginscreen/MainActivity.java
package com.example.loginscreen;
=======
package com.example.parkingmanager;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
>>>>>>> 6b7422a (Dropdown menu):app/src/main/java/com/example/parkingmanager/ParkingPassPurchaseActivity.java

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD:app/src/main/java/com/example/loginscreen/MainActivity.java
        setContentView(R.layout.activity_main);
=======
        setContentView(R.layout.purchase_parkingpass);

        String[] carArray = getResources().getStringArray(R.array.car_list);
        ArrayAdapter<String> arrayAdapter_car = new ArrayAdapter<String>(this,
                R.layout.dropdown_style, carArray);

        AutoCompleteTextView actv_car = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView_car);
        actv_car.setAdapter(arrayAdapter_car);

        String[] passArray = getResources().getStringArray(R.array.pass_type);
        ArrayAdapter<String> arrayAdapter_pass = new ArrayAdapter<String>(this,
                R.layout.dropdown_style, passArray);

        AutoCompleteTextView actv_pass = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView_pass);
        actv_pass.setAdapter(arrayAdapter_pass);
>>>>>>> 6b7422a (Dropdown menu):app/src/main/java/com/example/parkingmanager/ParkingPassPurchaseActivity.java
    }
}