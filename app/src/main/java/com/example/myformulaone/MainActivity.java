package com.example.myformulaone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Button clickme;
    Button drivers;
    Button teams;
    Button circuits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drivers= (Button)findViewById(R.id.button_drivers);

        drivers.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, Drivers.class);
            startActivity(intent);
        });

        teams= (Button)findViewById(R.id.button_teams);

        teams.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, Teams.class);
            startActivity(intent);
        });

        circuits= (Button)findViewById(R.id.button_circuits);

        circuits.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, Circuits.class);
            startActivity(intent);
        });
    }
}