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

public class Drivers extends AppCompatActivity {

    //creating a variables for our recycler view.
    private RecyclerView driversRV;
    private static final int ADD_DRIVER_REQUEST = 1;
    private static final int EDIT_DRIVER_REQUEST = 2;
    private ViewDriverModal ViewDriverModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers);
        //initializing our variable for our recycler view and fab.
        driversRV = findViewById(R.id.idRVDrivers);
        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        //adding on click listner for floating action button.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting a new activity for adding a new course and passing a constant value in it.
                Intent intent = new Intent(Drivers.this, NewDriverActivity.class);
                startActivityForResult(intent, ADD_DRIVER_REQUEST);
            }
        });

        //setting layout manager to our adapter class.
        driversRV.setLayoutManager(new LinearLayoutManager(this));
        driversRV.setHasFixedSize(true);
        //initializing adapter for recycler view.
        final DriverRVAdapter adapter = new DriverRVAdapter();
        //setting adapter class for recycler view.
        driversRV.setAdapter(adapter);
        //passing a data from view modal.
        ViewDriverModal = ViewModelProviders.of(this).get(ViewDriverModal.class);
        //below line is use to get all the courses from view modal.
        ViewDriverModal.getAllDrivers().observe(this, new Observer<List<DriverModal>>() {
            @Override
            public void onChanged(List<DriverModal> models) {
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
                ViewDriverModal.delete(adapter.getDriverAt(viewHolder.getAdapterPosition()));
                Toast.makeText(Drivers.this, "Driver deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                //below line is use to attact this to recycler view.
                        attachToRecyclerView(driversRV);
        //below line is use to set item click listner for our item of recycler view.
        adapter.setOnItemClickListener(new DriverRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DriverModal model) {

                String d_name = model.getDriverName();
                String d_num = model.getDriverNumber();
                String d_code = model.getDriverCode();
                String d_nation = model.getDriverNationality();
                String d_team = model.getDriverTeam();
                //after clicking on item of recycler view
                //we are opening a new activity and passing a data to our activity.
                Intent intent = new Intent(Drivers.this, DriverView.class);
                intent.putExtra("driver_name",d_name);
                intent.putExtra("driver_number",d_num);
                intent.putExtra("driver_code",d_code);
                intent.putExtra("driver_nationality",d_nation);
                intent.putExtra("driver_team",d_team);
//                //below line is to start a new activity and adding a edit course constant.
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_DRIVER_REQUEST && resultCode == RESULT_OK) {
            String driverCode = data.getStringExtra(NewDriverActivity.EXTRA_DRIVER_CODE);
            String driverNumber = data.getStringExtra(NewDriverActivity.EXTRA_DRIVER_NUMBER);
            String driverName = data.getStringExtra(NewDriverActivity.EXTRA_DRIVER_NAME);
            String driverNationality = data.getStringExtra(NewDriverActivity.EXTRA_DRIVER_NATIONALITY);
            String driverTeam = data.getStringExtra(NewDriverActivity.EXTRA_DRIVER_TEAM);
            DriverModal model = new DriverModal(driverCode, driverNumber, driverName, driverNationality, driverTeam);
            ViewDriverModal.insert(model);
            Toast.makeText(this, "Driver saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_DRIVER_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewDriverActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Driver can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String driverCode = data.getStringExtra(NewDriverActivity.EXTRA_DRIVER_CODE);
            String driverNumber = data.getStringExtra(NewDriverActivity.EXTRA_DRIVER_NUMBER);
            String driverName = data.getStringExtra(NewDriverActivity.EXTRA_DRIVER_NAME);
            String driverNationality = data.getStringExtra(NewDriverActivity.EXTRA_DRIVER_NATIONALITY);
            String driverTeam = data.getStringExtra(NewDriverActivity.EXTRA_DRIVER_TEAM);
            DriverModal model = new DriverModal(driverCode, driverNumber, driverName, driverNationality, driverTeam);
            model.setId(id);
            ViewDriverModal.update(model);
            Toast.makeText(this, "Driver updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Driver not saved", Toast.LENGTH_SHORT).show();
        }

    }
}