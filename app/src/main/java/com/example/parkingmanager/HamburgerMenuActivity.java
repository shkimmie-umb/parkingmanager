package com.example.parkingmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;

public class HamburgerMenuActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hamburgermenuactivity);

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        hideItem(R.id.nav_Create_Parking_Citation); // Hide parking citation menu for general parkers


        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.my_nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.nav_ParkingPass_Purchase){
                    Intent intent = new Intent(getApplicationContext(), ParkingPassPurchaseActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                return false;
                  }
        }
        );
    }

    private void hideItem(int id)
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.my_nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(id).setVisible(false);
    }
//    public void removeItem(int id) {
//        NavigationView navView = findViewById(R.id.my_nav_view); // Add your NavigationView id
//        Menu menu = navView.getMenu();
//        if (menu.findItem(id) != null) // Make sure that the item exists in the menu
//            menu.removeItem(id);
//    }


    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if(id == R.id.nav_ParkingPass_Purchase){
//            Intent intent = new Intent(getApplicationContext(), ParkingPassPurchaseActivity.class);
//            startActivity(intent);
////            finish();
////            return true;
//        }
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
//        else if(id == R.id.nav_ParkingPass_Purchase){
//            Intent intent = new Intent(getApplicationContext(), ParkingPassPurchaseActivity.class);
//            startActivity(intent);
////            finish();
////            return true;
//        }
//        else {
//            switch(item.getItemId()) {
//                case R.id.nav_ParkingPass_Purchase:
//                    Intent intent = new Intent(getApplicationContext(), ParkingPassPurchaseActivity.class);
//                    startActivity(intent);
//                    return true;
//                case R.id.nav_View_ParkingPass:
////                    Log.d("ActionItemClicked", "Share clicked");
//                    return true;
//            }
//        }
        return super.onOptionsItemSelected(item);
    }
}
