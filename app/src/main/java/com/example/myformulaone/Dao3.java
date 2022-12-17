package com.example.myformulaone;



import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Adding annotation to our Dao class
@androidx.room.Dao
public interface Dao3 {

    //below method is use to add data to database.

    @Insert
    void insert(CircuitModal model);

    //below method is use to update the data in our database.
    @Update
    void update(CircuitModal model);

    //below line is use to delete a specific course in our database.
    @Delete
    void delete(CircuitModal model);

    //on below line we are making query to delete all courses from our databse.
    @Query("DELETE FROM circuit_table")
    void deleteAllCircuits();

    //beloe line is to read all the courses from our database.
    //in this we are ordering our courses in ascending order with our course name.
    @Query("SELECT * FROM circuit_table ORDER BY circuitName ASC")
    LiveData<List<CircuitModal>> getAllCircuits();

}
