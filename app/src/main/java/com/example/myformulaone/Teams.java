package com.example.myformulaone;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Teams extends AppCompatActivity {

    //creating a variables for our recycler view.
    private RecyclerView teamsRV;
    private static final int ADD_TEAM_REQUEST = 1;
    private static final int EDIT_TEAM_REQUEST = 2;
    private ViewTeamModal ViewTeamModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        //initializing our variable for our recycler view and fab.
        teamsRV = findViewById(R.id.idRVTeams);
        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        //adding on click listner for floating action button.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting a new activity for adding a new course and passing a constant value in it.
                Intent intent = new Intent(Teams.this, NewTeamActivity.class);
                startActivityForResult(intent, ADD_TEAM_REQUEST);
            }
        });

        //setting layout manager to our adapter class.
        teamsRV.setLayoutManager(new LinearLayoutManager(this));
        teamsRV.setHasFixedSize(true);
        //initializing adapter for recycler view.
        final TeamRVAdapter adapter = new TeamRVAdapter();
        //setting adapter class for recycler view.
        teamsRV.setAdapter(adapter);
        //passing a data from view modal.
        ViewTeamModal = ViewModelProviders.of(this).get(ViewTeamModal.class);
        //below line is use to get all the courses from view modal.
        ViewTeamModal.getAllTeams().observe(this, new Observer<List<TeamModal>>() {
            @Override
            public void onChanged(List<TeamModal> models) {
                //when the data is changed in our models we are adding that list to our adapter class.
                adapter.submitList(models);
            }
        });
        //below method is use to add swipe to delete method for item of recycler view.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //on recycler view item swiped then we are deleting the item of our recycler view.
                ViewTeamModal.delete(adapter.getTeamAt(viewHolder.getAdapterPosition()));
                Toast.makeText(Teams.this, "Team deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                //below line is use to attact this to recycler view.
                        attachToRecyclerView(teamsRV);
        //below line is use to set item click listner for our item of recycler view.
        adapter.setOnItemClickListener(new TeamRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TeamModal model) {

                String t_name = model.getTeamName();
                String t_location = model.getTeamLocation();
                String t_car = model.getTeamCar();
                //after clicking on item of recycler view
                //we are opening a new activity and passing a data to our activity.
                Intent intent = new Intent(Teams.this, TeamView.class);
                intent.putExtra("team_name",t_name);
                intent.putExtra("team_location",t_location);
                intent.putExtra("team_car",t_car);
                //below line is to start a new activity and adding a edit course constant.
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_TEAM_REQUEST && resultCode == RESULT_OK) {
            String teamName = data.getStringExtra(NewTeamActivity.EXTRA_TEAM_NAME);
            String teamLocation = data.getStringExtra(NewTeamActivity.EXTRA_TEAM_LOCATION);
            String teamCar = data.getStringExtra(NewTeamActivity.EXTRA_TEAM_CAR);
            TeamModal model = new TeamModal(teamName, teamLocation, teamCar);
            ViewTeamModal.insert(model);
            Toast.makeText(this, "Team saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_TEAM_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewTeamActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Team can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String teamName = data.getStringExtra(NewTeamActivity.EXTRA_TEAM_NAME);
            String teamLocation = data.getStringExtra(NewTeamActivity.EXTRA_TEAM_LOCATION);
            String teamCar = data.getStringExtra(NewTeamActivity.EXTRA_TEAM_CAR);
            TeamModal model = new TeamModal(teamName, teamLocation, teamCar);
            model.setId(id);
            ViewTeamModal.update(model);
            Toast.makeText(this, "Team updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Team not saved", Toast.LENGTH_SHORT).show();
        }

    }
}