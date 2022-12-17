package com.example.myformulaone;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewDriverModal extends AndroidViewModel {
    //creating a new variable for course repository.
    private DriverRepository repository;
    
    //below line is to create a variable for live data where all the courses are present.
    private LiveData<List<DriverModal>> allDrivers;

    //constructor for our view modal.
    public ViewDriverModal(@NonNull Application application) {
        super(application);
        repository = new DriverRepository(application);
        allDrivers = repository.getAllDrivers();
    }


    //below method is use to insert the data to our repository.
    public void insert(DriverModal model) {
        repository.insert(model);
    }

    //below line is to update data in our repository.
    public void update(DriverModal model) {
        repository.update(model);
    }

    //below line is to delete the data in our repository.
    public void delete(DriverModal model) {
        repository.delete(model);
    }

    //below method is to delete all the courses in our list.
    public void deleteAllDrivers() {
        repository.deleteAllDrivers();
    }

    //below method is to get all the courses in our list.
    public LiveData<List<DriverModal>> getAllDrivers() {
        return allDrivers;
    }
}
