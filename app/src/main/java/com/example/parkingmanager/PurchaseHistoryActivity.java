package com.example.parkingmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class PurchaseHistoryActivity extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<purchaseTableModel> passList;

    Intent thisIntent;
    Button btn_home;
    double calculated_rate;
    String paid_date;
    String selected_pass;
    GlobalData global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_history);

        btn_home = (Button)findViewById(R.id.btn_home);
        btn_home.setOnClickListener(this);

        global = (GlobalData)getApplication();
//        thisIntent = getIntent();
        calculated_rate = global.getCalculated_rate();
        paid_date = global.getPaid_date();
        selected_pass = global.getSelected_pass();
        passList = global.getPurchaseTable();

        if(calculated_rate != 0 && paid_date != null && selected_pass != null) {
            Log.d("(Purchase History Act) calculated rate: ", String.valueOf(calculated_rate).toString());
            Log.d("(Purchase History Act) paid date: ", paid_date);
        }


//        passList = new ArrayList<purchaseTableModel>();
        ListView lview = (ListView) findViewById(R.id.listview_purchase_history);
        purchase_listview_Adapter adapter = new purchase_listview_Adapter(this, passList);
        lview.setAdapter(adapter);

        populateList();

        adapter.notifyDataSetChanged();

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String pass_type = ((TextView)view.findViewById(R.id.pass_type)).getText().toString();
                String pass_status = ((TextView)view.findViewById(R.id.pass_status)).getText().toString();
                String pass_purchase_date = ((TextView)view.findViewById(R.id.pass_purchase_date)).getText().toString();
                String pass_amount = ((TextView)view.findViewById(R.id.pass_amount)).getText().toString();

                Toast.makeText(getApplicationContext(),
                        "S no : " + pass_type +"\n"
                                +"Product : " + pass_status +"\n"
                                +"Category : " +pass_purchase_date +"\n"
                                +"Price : " +pass_amount, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateList() {

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



//        if(calculated_rate != 0 && selected_pass != null){
//            DecimalFormat df = new DecimalFormat("#.00");
//            String calculatedRateFormatted = df.format(calculated_rate);
//
//
//            purchaseTableModel item = new purchaseTableModel(selected_pass, "Active", paid_date, "$" + calculatedRateFormatted);
//            passList.add(item);
//        }
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
