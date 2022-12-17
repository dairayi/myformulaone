package com.example.myformulaone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewCircuitActivity extends AppCompatActivity {

    //creating a variables for our button and edittext.
    private EditText circuitNameEdt, circuitLocationEdt, circuitLngEdt, circuitLatEdt;
    private Button circuitBtn;

    //creating a constant string variable for our course name, description and duration.
    public static final String EXTRA_ID =
            "com.example.project1.EXTRA_ID";
    public static final String EXTRA_CIRCUIT_NAME =
            "com.example.project1.EXTRA_CIRCUIT_NAME";
    public static final String EXTRA_CIRCUIT_LOCATION =
            "com.example.project1.EXTRA_CIRCUIT_LOCATION";
    public static final String EXTRA_CIRCUIT_LNG =
            "com.example.project1.EXTRA_CIRCUIT_LNG";
    public static final String EXTRA_CIRCUIT_LAT =
            "com.example.project1.EXTRA_CIRCUIT_LAT";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_circuit);
        //initializing our variables for each view.
        circuitNameEdt = findViewById(R.id.idEdtCircuitName);
        circuitLocationEdt = findViewById(R.id.idEdtCircuitLocation);
        circuitLngEdt = findViewById(R.id.idEdtCircuitLng);
        circuitLatEdt = findViewById(R.id.idEdtCircuitLat);
        circuitBtn = findViewById(R.id.idBtnSaveCircuit);
        //below line is to get intent as we are getting data via an intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            //if we get id for our data then we are setting values to our edit text fields.
            circuitNameEdt.setText(intent.getStringExtra(EXTRA_CIRCUIT_NAME));
            circuitLocationEdt.setText(intent.getStringExtra(EXTRA_CIRCUIT_LOCATION));
            circuitLngEdt.setText(intent.getStringExtra(EXTRA_CIRCUIT_LNG));
            circuitLatEdt.setText(intent.getStringExtra(EXTRA_CIRCUIT_LAT));
        }
        //adding on click listner for our save button.
        circuitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting text value from edittext and validating if the text fields are empty or not.
                String circuitName = circuitNameEdt.getText().toString();
                String circuitLocation = circuitLocationEdt.getText().toString();
                String circuitLng = circuitLngEdt.getText().toString();
                String circuitLat = circuitLatEdt.getText().toString();
                if (circuitName.isEmpty() || circuitLocation.isEmpty() || circuitLng.isEmpty() || circuitLat.isEmpty()) {
                    Toast.makeText(NewCircuitActivity.this, "Please enter the valid Circuit details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                //calling a method to save our course.
                saveCircuit(circuitName, circuitLocation, circuitLng, circuitLat);
            }
        });

    }

    private void saveCircuit(String circuitName,String circuitLocation,String circuitLng,String circuitLat) {
        //inside this method we are passing all the data via an intent.
        Intent data = new Intent();
        //in below line we are passing all our course detail.
        data.putExtra(EXTRA_CIRCUIT_NAME, circuitName);
        data.putExtra(EXTRA_CIRCUIT_LOCATION, circuitLocation);
        data.putExtra(EXTRA_CIRCUIT_LNG, circuitLng);
        data.putExtra(EXTRA_CIRCUIT_LAT, circuitLat);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            //in below line we are passing our id.
            data.putExtra(EXTRA_ID, id);
        }
        //at last we are setting result as data.
        setResult(RESULT_OK, data);
        //displaying a toast message after adding the data
        Toast.makeText(this, "Circuit has been saved to Circuit Database. ", Toast.LENGTH_SHORT).show();
    }

}