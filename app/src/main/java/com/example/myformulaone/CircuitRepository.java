package com.example.myformulaone;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CircuitRepository {
    //below line is the create a variable for dao and list.
    private Dao3 dao3;
    private LiveData<List<CircuitModal>> allCircuits;

    //creating a constructor for our variables and passing the variables to it.
    public CircuitRepository(Application application) {
        CircuitDatabase database = CircuitDatabase.getInstance(application);
        dao3 = database.Dao3();
        allCircuits = dao3.getAllCircuits();
    }

    //creating a method to insert the data to our database.
    public void insert(CircuitModal model) {
        new InsertCircuitAsyncTask(dao3).execute(model);
    }

    //creating a method to update data in database.
    public void update(CircuitModal model) {
        new UpdateCircuitAsyncTask(dao3).execute(model);
    }

    //creating a method to delete the data in our database.
    public void delete(CircuitModal model) {
        new DeleteCircuitAsyncTask(dao3).execute(model);
    }

    //below is the method to delete all the courses.
    public void deleteAllCircuits() {
        new DeleteAllCircuitsAsyncTask(dao3).execute();
    }

    //below method is to read all.
    public LiveData<List<CircuitModal>> getAllCircuits() {
        return allCircuits;
    }

    //we are creating a async task method to insert new.
    private static class InsertCircuitAsyncTask extends AsyncTask<CircuitModal, Void, Void> {
        private Dao3 dao3;

        private InsertCircuitAsyncTask(Dao3 dao3) {
            this.dao3 = dao3;
        }

        @Override
        protected Void doInBackground(CircuitModal... model) {
            //below line is use to insert our modal in dao.
            dao3.insert(model[0]);
            return null;
        }
    }

    //we are creating a async task method to update our course.
    private static class UpdateCircuitAsyncTask extends AsyncTask<CircuitModal, Void, Void> {
        private Dao3 dao3;

        private UpdateCircuitAsyncTask(Dao3 dao3) {
            this.dao3 = dao3;
        }

        @Override
        protected Void doInBackground(CircuitModal... models) {
            //below line is use to update our modal in dao.
            dao3.update(models[0]);
            return null;
        }
    }

    //we are creating a async task method to delete.
    private static class DeleteCircuitAsyncTask extends AsyncTask<CircuitModal, Void, Void> {
        private Dao3 dao3;

        private DeleteCircuitAsyncTask(Dao3 dao3) {
            this.dao3 = dao3;
        }

        @Override
        protected Void doInBackground(CircuitModal... models) {
            //below line is use to delete our modal in dao.
            dao3.delete(models[0]);
            return null;
        }
    }

    //we are creating a async task method to delete all.
    private static class DeleteAllCircuitsAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao3 dao3;

        private DeleteAllCircuitsAsyncTask(Dao3 dao3) {
            this.dao3 = dao3;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //on below line calling method to delete all.
            dao3.deleteAllCircuits();
            return null;
        }
    }
}