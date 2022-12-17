package com.example.myformulaone;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CircuitView extends AppCompatActivity {

    TextView circuit_name, circuit_location, circuit_lng, circuit_lat;
    Button map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circuit_view);

        circuit_name = findViewById(R.id.circuitName);
        circuit_location = findViewById(R.id.circuitLocation);
        circuit_lng = findViewById(R.id.circuitLng);
        circuit_lat = findViewById(R.id.circuitLat);
        Intent intent = getIntent();

        String c_name = intent.getStringExtra("circuit_name");
        String c_location = intent.getStringExtra("circuit_location");
        String c_lng = intent.getStringExtra("circuit_lng");
        String c_lat = intent.getStringExtra("circuit_lat");

        circuit_name.setText(c_name);
        circuit_location.setText(c_location);
        circuit_lng.setText(c_lng);
        circuit_lat.setText(c_lat);

        map = (Button)findViewById(R.id.button);

        map.setOnClickListener(v ->{
            Intent intent2 = new Intent(CircuitView.this, MapsActivity.class);

            intent2.putExtra("circuit_name",c_name);
            intent2.putExtra("circuit_lng",c_lng);
            intent2.putExtra("circuit_lat",c_lat);

            startActivity(intent2);
        });
    }
}