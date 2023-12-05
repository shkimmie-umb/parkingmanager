package com.example.parkingmanager;

import java.util.Date;

public class citationTableModel {
    private int citationNo=0;
    private String vehicleNickname = null;
    private String vehiclePlateNo = null;
    private String parkedPos = null;
    private Date citedDate;
    private String status = null;

    public citationTableModel(){

    }
    public citationTableModel(String vehicleNickname, String vehiclePlateNo, String parkedPos){
        this.citationNo += 1;
        this.vehicleNickname = vehicleNickname;
        this.vehiclePlateNo = vehiclePlateNo;
        this.parkedPos = parkedPos;
        this.citedDate = new Date();
        this.status = "Cited";
    }

    public int getCitationNo(){
        return this.citationNo;
    }
    public String getVehicleNickname(){
        return this.vehicleNickname;
    }
    public String getVehiclePlateNo(){
        return this.vehiclePlateNo;
    }
    public String getParkedPos(){
        return this.parkedPos;
    }
    public Date getCitedDate(){
        return this.citedDate;
    }
    public String getCitationStatus(){
        return this.status;
    }

    public void setVehicleNickname(String nickname){
        this.vehicleNickname = nickname;
    }
    public void setVehiclePlateNo(String plateNo){
        this.vehiclePlateNo = plateNo;
    }
    public void setParkedPos(String pos){
        this.parkedPos = pos;
    }
    public void setCitedDate(){
        this.citedDate = new Date();
    }
    public void setCitationStatus(String status){
        this.status = status;
    }

}
