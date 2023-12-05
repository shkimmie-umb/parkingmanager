package com.example.parkingmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class HamburgerMenuActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    GlobalData global;
    citationTableModel citation;

    String loggedin_ID;

    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hamburgermenuactivity);

        global = (GlobalData)getApplication();
        citation = global.getCitationTable();

        welcome = (TextView)findViewById(R.id.tv_welcome);
        String welcome_str = "Welcome, " + global.getLoggedin_ID() + "!\n" + "Please select a menu from the left navigation";
        welcome.setText(welcome_str);

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        loggedin_ID = global.getLoggedin_ID();

        accountControl(loggedin_ID);
        Log.d("Hamburger", loggedin_ID);


//        hideItem(R.id.nav_Create_Parking_Citation); // Hide parking citation menu for general parkers



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
                else if(id == R.id.nav_ParkingPass_History){
                    Intent intent = new Intent(getApplicationContext(), PurchaseHistoryActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                else if(id == R.id.nav_View_ParkingPass){
                    Intent intent = new Intent(getApplicationContext(), ViewParkingPassActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                else if(id == R.id.nav_Create_Parking_Citation){
                    Intent intent = new Intent(getApplicationContext(), CreateParkingCitationActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }else if (id == R.id.nav_Parking_Availability) {
                    Intent intent = new Intent(getApplicationContext(), ParkingSelectionActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                else if(id == R.id.nav_Notification_parker){
                    if(citation.getCitationStatus() == "Pending" || citation.getCitationStatus() == "Confirmed") {
                        Intent intent = new Intent(getApplicationContext(), NotificationParkerActivity.class);
                        startActivity(intent);
                        finish();
                        return true;
                    }
                    else if(citation.getCitationStatus() == null || citation.getCitationStatus() == "Paid" || citation.getCitationStatus() == "Voided"){
                        AlertDialog.Builder builder = new AlertDialog.Builder(HamburgerMenuActivity.this);

                        // Set the message show for the Alert time
                        builder.setMessage("You have no notifications");

                        // Set Alert Title
                        builder.setTitle("Notification");

                        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                        builder.setCancelable(false);

                        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                        builder.setPositiveButton("OK", null);



                        // Create the Alert dialog
                        AlertDialog alertDialog = builder.create();
                        // Show the Alert Dialog box
                        alertDialog.show();
                    }
                }
                else if(id == R.id.nav_Notification_manager){
                    if(citation.getCitationStatus() == "Appealed") {
                        Intent intent = new Intent(getApplicationContext(), NotificationManagerActivity.class);
                        startActivity(intent);
                        finish();
                        return true;
                    }
                    else if(citation.getCitationStatus() == null || citation.getCitationStatus() == "Paid" || citation.getCitationStatus() == "Cited" || citation.getCitationStatus() == "Pending" || citation.getCitationStatus() == "Voided"){
                        AlertDialog.Builder builder = new AlertDialog.Builder(HamburgerMenuActivity.this);

                        // Set the message show for the Alert time
                        builder.setMessage("You have no notifications");

                        // Set Alert Title
                        builder.setTitle("Notification");

                        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                        builder.setCancelable(false);

                        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                        builder.setPositiveButton("OK", null);



                        // Create the Alert dialog
                        AlertDialog alertDialog = builder.create();
                        // Show the Alert Dialog box
                        alertDialog.show();
                    }
                }
                else if(id == R.id.nav_logout){
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                return false;
                  }
        }
        );
    }

    private void hideItem(int menu_id)
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.my_nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(menu_id).setVisible(false);
    }

    private void accountControl(String id){
        if(id.equals("super") || id.equals(null)){
            //Do nothing
        }
        else if(id.equals("admin")){ // parking manager
            hideItem(R.id.nav_ParkingPass_Purchase);
            hideItem(R.id.nav_ParkingPass_History);
            hideItem(R.id.nav_View_ParkingPass);
            hideItem(R.id.nav_Notification_parker);

        }
        else if(id.equals("user") || (!id.equals("admin") && !id.equals("super") && id.length() > 1)){ // General parkers
            hideItem(R.id.nav_Create_Parking_Citation);
            hideItem(R.id.nav_Notification_manager);

        }
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
