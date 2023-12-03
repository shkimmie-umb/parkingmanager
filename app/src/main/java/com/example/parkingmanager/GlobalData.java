package com.example.parkingmanager;

import android.app.Application;

public class GlobalData extends Application {
    private static double calculated_rate;
    private static String paid_date;
    private static String selected_pass;

    @Override
    public void onCreate() {
        //전역 변수 초기화
        calculated_rate = 0;
        paid_date = null;
        selected_pass = null;
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

    public double getCalculated_rate(){
        return calculated_rate;
    }
    public String getPaid_date(){
        return paid_date;
    }
    public String getSelected_pass(){
        return selected_pass;
    }

}
