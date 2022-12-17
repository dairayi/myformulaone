package com.example.myformulaone;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//below line is for setting table name.
@Entity(tableName = "circuit_table")
public class CircuitModal {

    //below line is to auto increment id for each course.
    @PrimaryKey(autoGenerate = true)
    //variable for our id.
    private int id;
    private String circuitName;
    private String circuitLocation;
    private String circuitLng;
    private String circuitLat;

    //below line we are creating constructor class.
    //inside constructor class we are not passing our id because it is incrementing automatically
    public CircuitModal(String circuitName, String circuitLocation, String circuitLng, String circuitLat) {
        this.circuitName= circuitName;
        this.circuitLocation= circuitLocation;
        this.circuitLng= circuitLng;
        this.circuitLat= circuitLat;
    }

    //on below line we are creating getter and setter methods.
    public String getCircuitName() {
        return circuitName;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    public String getCircuitLocation() {
        return circuitLocation;
    }

    public void setCircuitLocation(String circuitLocation) { this.circuitLocation = circuitLocation; }

    public String getCircuitLng() {
        return circuitLng;
    }

    public void setCircuitLng(String circuitLng) {
        this.circuitLng = circuitLng;
    }

    public String getCircuitLat() {
        return circuitLat;
    }

    public void setCircuitLat(String circuitLat) {this.circuitLat = circuitLat;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
