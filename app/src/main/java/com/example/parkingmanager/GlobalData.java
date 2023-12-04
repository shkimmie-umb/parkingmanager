package com.example.parkingmanager;

import android.app.Application;
import android.provider.Settings;

import java.util.ArrayList;

public class GlobalData extends Application {
    private ArrayList<purchaseTableModel> passList;
    private static citationTableModel citation;
    private static double calculated_rate;
    private static String paid_date;
    private static String selected_pass;
    private static String parkingpass_status;


    @Override
    public void onCreate() {
        //전역 변수 초기화
        calculated_rate = 0;
        paid_date = null;
        selected_pass = null;
        passList = new ArrayList<purchaseTableModel>();
        parkingpass_status = "Invalid";
        citation = new citationTableModel();
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public void setCalculated_rate(double calculated_rate){
        GlobalData.calculated_rate = calculated_rate;
    }
    public void setPaid_date(String paid_date){
        GlobalData.paid_date = paid_date;
    }

    public void setSelected_pass(String selected_pass){
        GlobalData.selected_pass = selected_pass;
    }
    public void setParkingpass_status(String new_status){
        GlobalData.parkingpass_status = new_status;
    }
    public void setCitationTable(citationTableModel citation){
        this.citation = citation;
    }
    public void addPass(purchaseTableModel pass){
        passList.add(pass);
    }

    public double getCalculated_rate(){
        return calculated_rate;
    }
    public String getPaid_date(){
        return paid_date;
    }
    public String getSelected_pass(){
        return selected_pass;
    }
    public ArrayList<purchaseTableModel> getPurchaseTable(){
        return passList;
    }

    public String getParkingpass_status(){
        return parkingpass_status;
    }
    public citationTableModel getCitationTable(){
        return citation;
    }


}
