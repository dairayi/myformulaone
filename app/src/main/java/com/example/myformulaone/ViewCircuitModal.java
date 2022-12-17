package com.example.myformulaone;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewCircuitModal extends AndroidViewModel {
    //creating a new variable for course repository.
    private CircuitRepository repository;

    //below line is to create a variable for live data where all the courses are present.
    private LiveData<List<CircuitModal>> allCircuits;

    //constructor for our view modal.
    public ViewCircuitModal(@NonNull Application application) {
        super(application);
        repository = new CircuitRepository(application);
        allCircuits = repository.getAllCircuits();
    }


    //below method is use to insert the data to our repository.
    public void insert(CircuitModal model) {
        repository.insert(model);
    }

    //below line is to update data in our repository.
    public void update(CircuitModal model) {
        repository.update(model);
    }

    //below line is to delete the data in our repository.
    public void delete(CircuitModal model) {
        repository.delete(model);
    }

    //below method is to delete all the courses in our list.
    public void deleteAllCircuits() {
        repository.deleteAllCircuits();
    }

    //below method is to get all the courses in our list.
    public LiveData<List<CircuitModal>> getAllCircuits() {
        return allCircuits;
    }
}
