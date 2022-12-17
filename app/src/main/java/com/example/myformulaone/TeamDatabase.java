package com.example.myformulaone;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

//adding annotation for our databse entities and db version.
@Database(entities = {TeamModal.class}, version = 1)
public abstract class TeamDatabase extends RoomDatabase {
    //below line is to create instance for our databse class.
    private static TeamDatabase instance;

    //below line is to create abstract variable for dao.
    public abstract Dao2 Dao2();

    //on below line we are getting instance for our database.
    public static synchronized TeamDatabase getInstance(Context context) {
        //below line is to check if the instance is null or not.
        if (instance == null) {
            //if the instance is null we are creating a new instance
            instance =
                    //for creating a instance for our database we are creating a database builder and passing our database class with our database name.
                    Room.databaseBuilder(context.getApplicationContext(),
                                    TeamDatabase.class, "team_database")
                            //below line is use to add fall back to destructive migration to our database.
                            .fallbackToDestructiveMigration()
                            //below line is to add callback to our database.
                            .addCallback(roomCallback)
                            //below line is to build our database.
                            .build();
        }
        //after creating an instance we are returning our instance
        return instance;
    }

    //below line is to create a callback for our room database.
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //this method is called when database is created and below line is to populate our data.
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    //we are creating an async task class to perform task in background.
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        PopulateDbAsyncTask(TeamDatabase instance) {
            Dao2 dao2 = instance.Dao2();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }
}
