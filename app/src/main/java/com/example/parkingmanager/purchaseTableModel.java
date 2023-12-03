package com.example.parkingmanager;

public class purchaseTableModel {
    private String pass_type;
    private String pass_status;
    private String pass_purchase_date;
    private String pass_amount;

    public purchaseTableModel(String pass_type, String pass_status, String pass_purchase_date, String pass_amount) {
        this.pass_type = pass_type;
        this.pass_status = pass_status;
        this.pass_purchase_date = pass_purchase_date;
        this.pass_amount = pass_amount;
    }

    public String getPass_type() {
        return pass_type;
    }

    public String getPass_status() {
        return pass_status;
    }

    public String getPass_purchase_date() {
        return pass_purchase_date;
    }

    public String getPass_amount() {
        return pass_amount;
    }

}
