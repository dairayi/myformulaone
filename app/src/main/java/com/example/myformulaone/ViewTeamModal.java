package com.example.myformulaone;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewTeamModal extends AndroidViewModel {
    //creating a new variable for course repository.
    private TeamRepository repository;

    //below line is to create a variable for live data where all the courses are present.
    private LiveData<List<TeamModal>> allTeams;

    //constructor for our view modal.
    public ViewTeamModal(@NonNull Application application) {
        super(application);
        repository = new TeamRepository(application);
        allTeams = repository.getAllTeams();
    }


    //below method is use to insert the data to our repository.
    public void insert(TeamModal model) {
        repository.insert(model);
    }

    //below line is to update data in our repository.
    public void update(TeamModal model) {
        repository.update(model);
    }

    //below line is to delete the data in our repository.
    public void delete(TeamModal model) {
        repository.delete(model);
    }

    //below method is to delete all the courses in our list.
    public void deleteAllTeams() {
        repository.deleteAllTeams();
    }

    //below method is to get all the courses in our list.
    public LiveData<List<TeamModal>> getAllTeams() {
        return allTeams;
    }
}

