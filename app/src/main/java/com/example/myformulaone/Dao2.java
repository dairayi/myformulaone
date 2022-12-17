package com.example.myformulaone;



import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Adding annotation to our Dao class
@androidx.room.Dao
public interface Dao2 {

    //below method is use to add data to database.

    @Insert
    void insert(TeamModal model);

    //below method is use to update the data in our database.
    @Update
    void update(TeamModal model);

    //below line is use to delete a specific course in our database.
    @Delete
    void delete(TeamModal model);

    //on below line we are making query to delete all courses from our databse.
    @Query("DELETE FROM team_table")
    void deleteAllTeams();

    //beloe line is to read all the courses from our database.
    //in this we are ordering our courses in ascending order with our course name.
    @Query("SELECT * FROM team_table ORDER BY teamName ASC")
    LiveData<List<TeamModal>> getAllTeams();

}