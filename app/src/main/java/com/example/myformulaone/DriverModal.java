package com.example.myformulaone;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//below line is for setting table name.
@Entity(tableName = "driver_table")
public class DriverModal {

    //below line is to auto increment id for each course.
    @PrimaryKey(autoGenerate = true)
    //variable for our id.
    private int id;
    //below line is a variable for course name.
    private String driverCode;
    //below line is use for course description.
    private String driverNumber;
    //below line is use for course duration.
    private String driverName;

    private String driverNationality;

    private String driverTeam;

    //below line we are creating constructor class.
    //inside constructor class we are not passing our id because it is incrementing automatically
    public DriverModal(String driverCode, String driverNumber, String driverName, String driverNationality, String driverTeam) {
        this.driverCode= driverCode;
        this.driverNumber= driverNumber;
        this.driverName= driverName;
        this.driverNationality= driverNationality;
        this.driverTeam= driverTeam;
    }

    //on below line we are creating getter and setter methods.
    public String getDriverCode() {
        return driverCode;
    }

    public void setDriverCode(String driverCode) {
        this.driverCode = driverCode;
    }

    public String getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(String driverNumber) {
        this.driverNumber = driverNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverNationality() {
        return driverNationality;
    }

    public void setDriverNationality(String driverNationality) {this.driverNationality = driverNationality;}

    public String getDriverTeam() {
        return driverTeam;
    }

    public void setDriverTeam(String driverTeam) {
        this.driverTeam = driverTeam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
