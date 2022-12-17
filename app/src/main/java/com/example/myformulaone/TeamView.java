package com.example.myformulaone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TeamView extends AppCompatActivity {

    TextView team_name, team_location, team_car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_view);

        team_name = findViewById(R.id.teamName);
        team_location = findViewById(R.id.teamLocation);
        team_car = findViewById(R.id.teamCar);

        Intent intent = getIntent();

        String t_name = intent.getStringExtra("team_name");
        String t_location = intent.getStringExtra("team_location");
        String t_car = intent.getStringExtra("team_car");

        team_name.setText(t_name);
        team_location.setText(t_location);
        team_car.setText(t_car);
    }
}