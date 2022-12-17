package com.example.myformulaone;



import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TeamRepository {
    //below line is the create a variable for dao and list for all courses.
    private Dao2 dao2;
    private LiveData<List<TeamModal>> allTeams;

    //creating a constructor for our variables and passing the variables to it.
    public TeamRepository(Application application) {
        TeamDatabase database = TeamDatabase.getInstance(application);
        dao2 = database.Dao2();
        allTeams = dao2.getAllTeams();
    }

    //creating a method to insert the data to our database.
    public void insert(TeamModal model) {
        new InsertTeamAsyncTask(dao2).execute(model);
    }

    //creating a method to update data in database.
    public void update(TeamModal model) {
        new UpdateTeamAsyncTask(dao2).execute(model);
    }

    //creating a method to delete the data in our database.
    public void delete(TeamModal model) {
        new DeleteTeamAsyncTask(dao2).execute(model);
    }

    //below is the method to delete all the courses.
    public void deleteAllTeams() {
        new DeleteAllTeamsAsyncTask(dao2).execute();
    }

    //below method is to read all the courses.
    public LiveData<List<TeamModal>> getAllTeams() {
        return allTeams;
    }

    //we are creating a async task method to insert new course.
    private static class InsertTeamAsyncTask extends AsyncTask<TeamModal, Void, Void> {
        private Dao2 dao2;

        private InsertTeamAsyncTask(Dao2 dao2) {
            this.dao2 = dao2;
        }

        @Override
        protected Void doInBackground(TeamModal... model) {
            //below line is use to insert our modal in dao.
            dao2.insert(model[0]);
            return null;
        }
    }

    //we are creating a async task method to update our course.
    private static class UpdateTeamAsyncTask extends AsyncTask<TeamModal, Void, Void> {
        private Dao2 dao2;

        private UpdateTeamAsyncTask(Dao2 dao2) {
            this.dao2 = dao2;
        }

        @Override
        protected Void doInBackground(TeamModal... models) {
            //below line is use to update our modal in dao.
            dao2.update(models[0]);
            return null;
        }
    }

    //we are creating a async task method to delete course.
    private static class DeleteTeamAsyncTask extends AsyncTask<TeamModal, Void, Void> {
        private Dao2 dao2;

        private DeleteTeamAsyncTask(Dao2 dao2) {
            this.dao2 = dao2;
        }

        @Override
        protected Void doInBackground(TeamModal... models) {
            //below line is use to delete our course modal in dao.
            dao2.delete(models[0]);
            return null;
        }
    }

    //we are creating a async task method to delete all courses.
    private static class DeleteAllTeamsAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao2 dao2;

        private DeleteAllTeamsAsyncTask(Dao2 dao2) {
            this.dao2 = dao2;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //on below line calling method to delete all courses.
            dao2.deleteAllTeams();
            return null;
        }
    }
}

