package com.example.myformulaone;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DriverRepository {
    //below line is the create a variable for dao and list for all courses.
    private Dao dao;
    private LiveData<List<DriverModal>> allDrivers;

    //creating a constructor for our variables and passing the variables to it.
    public DriverRepository(Application application) {
        DriverDatabase database = DriverDatabase.getInstance(application);
        dao = database.Dao();
        allDrivers = dao.getAllDrivers();
    }

    //creating a method to insert the data to our database.
    public void insert(DriverModal model) {
        new InsertDriverAsyncTask(dao).execute(model);
    }

    //creating a method to update data in database.
    public void update(DriverModal model) {
        new UpdateDriverAsyncTask(dao).execute(model);
    }

    //creating a method to delete the data in our database.
    public void delete(DriverModal model) {
        new DeleteDriverAsyncTask(dao).execute(model);
    }

    //below is the method to delete all the courses.
    public void deleteAllDrivers() {
        new DeleteAllDriversAsyncTask(dao).execute();
    }

    //below method is to read all the courses.
    public LiveData<List<DriverModal>> getAllDrivers() {
        return allDrivers;
    }

    //we are creating a async task method to insert new course.
    private static class InsertDriverAsyncTask extends AsyncTask<DriverModal, Void, Void> {
        private Dao dao;

        private InsertDriverAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(DriverModal... model) {
            //below line is use to insert our modal in dao.
            dao.insert(model[0]);
            return null;
        }
    }

    //we are creating a async task method to update our course.
    private static class UpdateDriverAsyncTask extends AsyncTask<DriverModal, Void, Void> {
        private Dao dao;

        private UpdateDriverAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(DriverModal... models) {
            //below line is use to update our modal in dao.
            dao.update(models[0]);
            return null;
        }
    }

    //we are creating a async task method to delete course.
    private static class DeleteDriverAsyncTask extends AsyncTask<DriverModal, Void, Void> {
        private Dao dao;

        private DeleteDriverAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(DriverModal... models) {
            //below line is use to delete our course modal in dao.
            dao.delete(models[0]);
            return null;
        }
    }

    //we are creating a async task method to delete all courses.
    private static class DeleteAllDriversAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;

        private DeleteAllDriversAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //on below line calling method to delete all courses.
            dao.deleteAllDrivers();
            return null;
        }
    }
}
