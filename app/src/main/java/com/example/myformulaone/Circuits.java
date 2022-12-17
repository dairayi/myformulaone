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

public class Circuits extends AppCompatActivity {

    //creating a variables for our recycler view.
    private RecyclerView circuitsRV;
    private static final int ADD_CIRCUIT_REQUEST = 1;
    private static final int EDIT_CIRCUIT_REQUEST = 2;
    private ViewCircuitModal ViewCircuitModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circuits);
        //initializing our variable for our recycler view and fab.
        circuitsRV = findViewById(R.id.idRVCircuits);
        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        //adding on click listner for floating action button.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting a new activity.
                Intent intent = new Intent(Circuits.this, NewCircuitActivity.class);
                startActivityForResult(intent, ADD_CIRCUIT_REQUEST);
            }
        });

        //setting layout manager to our adapter class.
        circuitsRV.setLayoutManager(new LinearLayoutManager(this));
        circuitsRV.setHasFixedSize(true);
        //initializing adapter for recycler view.
        final CircuitRVAdapter adapter = new CircuitRVAdapter();
        //setting adapter class for recycler view.
        circuitsRV.setAdapter(adapter);
        //passing a data from view modal.
        ViewCircuitModal = ViewModelProviders.of(this).get(ViewCircuitModal.class);
        //below line is use to get all from view modal.
        ViewCircuitModal.getAllCircuits().observe(this, new Observer<List<CircuitModal>>() {
            @Override
            public void onChanged(List<CircuitModal> models) {
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
                ViewCircuitModal.delete(adapter.getCircuitAt(viewHolder.getAdapterPosition()));
                Toast.makeText(Circuits.this, "Circuit deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                //below line is use to attact this to recycler view.
                        attachToRecyclerView(circuitsRV);
        //below line is use to set item click listener for our item of recycler view.
        adapter.setOnItemClickListener(new CircuitRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CircuitModal model) {

                String c_name = model.getCircuitName();
                String c_location = model.getCircuitLocation();
                String c_lng = model.getCircuitLng();
                String c_lat = model.getCircuitLat();
                //after clicking on item of recycler view
                //we are opening a new activity and passing a data to our activity.
                Intent intent = new Intent(Circuits.this, CircuitView.class);
                intent.putExtra("circuit_name",c_name);
                intent.putExtra("circuit_location",c_location);
                intent.putExtra("circuit_lng",c_lng);
                intent.putExtra("circuit_lat",c_lat);
//                //below line is to start a new activity and adding a edit constant.
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CIRCUIT_REQUEST && resultCode == RESULT_OK) {
            String circuitName = data.getStringExtra(NewCircuitActivity.EXTRA_CIRCUIT_NAME);
            String circuitLocation = data.getStringExtra(NewCircuitActivity.EXTRA_CIRCUIT_LOCATION);
            String circuitLng = data.getStringExtra(NewCircuitActivity.EXTRA_CIRCUIT_LNG);
            String circuitLat = data.getStringExtra(NewCircuitActivity.EXTRA_CIRCUIT_LAT);
            CircuitModal model = new CircuitModal(circuitName, circuitLocation, circuitLng, circuitLat);
            ViewCircuitModal.insert(model);
            Toast.makeText(this, "Circuit saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_CIRCUIT_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewCircuitActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Circuit can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String circuitName = data.getStringExtra(NewCircuitActivity.EXTRA_CIRCUIT_NAME);
            String circuitLocation = data.getStringExtra(NewCircuitActivity.EXTRA_CIRCUIT_LOCATION);
            String circuitLng = data.getStringExtra(NewCircuitActivity.EXTRA_CIRCUIT_LNG);
            String circuitLat = data.getStringExtra(NewCircuitActivity.EXTRA_CIRCUIT_LAT);
            CircuitModal model = new CircuitModal(circuitName, circuitLocation, circuitLng, circuitLat);
            model.setId(id);
            ViewCircuitModal.update(model);
            Toast.makeText(this, "Circuit updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Circuit not saved", Toast.LENGTH_SHORT).show();
        }

    }
}