package com.example.myformulaone;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//below line is for setting table name.
@Entity(tableName = "team_table")
public class TeamModal {

    //below line is to auto increment id for each course.
    @PrimaryKey(autoGenerate = true)
    //variable for our id.
    private int id;
    //below line is a variable for course name.
    private String teamName;
    //below line is use for course description.
    private String teamLocation;
    //below line is use for course duration.
    private String teamCar;

    //below line we are creating constructor class.
    //inside constructor class we are not passing our id because it is incrementing automatically
    public TeamModal(String teamName, String teamLocation, String teamCar) {
        this.teamName= teamName;
        this.teamLocation= teamLocation;
        this.teamCar= teamCar;
    }

    //on below line we are creating getter and setter methods.
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamLocation() {
        return teamLocation;
    }

    public void setTeamLocation(String teamLocation) {
        this.teamLocation = teamLocation;
    }

    public String getTeamCar() {
        return teamCar;
    }

    public void setTeamCar(String teamCar) {
        this.teamCar = teamCar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

