package com.example.myformulaone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class DriverView extends AppCompatActivity {

    TextView driver_code, driver_name, driver_number, driver_nationality, driver_team;
    ImageView flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_view);

        driver_code = findViewById(R.id.driverCode);
        driver_name = findViewById(R.id.driverName);
        driver_number = findViewById(R.id.driverNumber);
        driver_nationality = findViewById(R.id.driverNationality);
        driver_team = findViewById(R.id.driverTeam);
        flag = findViewById(R.id.imageViewFlag);

        Intent intent = getIntent();

        String d_code = intent.getStringExtra("driver_code");
        String d_name = intent.getStringExtra("driver_name");
        String d_num = intent.getStringExtra("driver_number");
        String d_nation = intent.getStringExtra("driver_nationality");
        String d_team = intent.getStringExtra("driver_team");

        driver_code.setText(d_code);
        driver_team.setText(d_team);
        driver_number.setText(d_num);
        driver_nationality.setText(d_nation);
        driver_name.setText(d_name);

        if (Objects.equals(d_nation, "British")){
            flag.setImageResource(R.drawable.british);
        }
        else if(Objects.equals(d_nation, "French")){
            flag.setImageResource(R.drawable.french);
        }
        else if(Objects.equals(d_nation, "Mexican")){
            flag.setImageResource(R.drawable.mexican);
        }
        else if(Objects.equals(d_nation, "American")){
            flag.setImageResource(R.drawable.american);
        }
        else if(Objects.equals(d_nation, "Australian")){
            flag.setImageResource(R.drawable.australian);
        }
        else if(Objects.equals(d_nation, "Canadian")){
            flag.setImageResource(R.drawable.canadian);
        }
        else if(Objects.equals(d_nation, "Chinese")){
            flag.setImageResource(R.drawable.chinese);
        }
        else if(Objects.equals(d_nation, "Danish")){
            flag.setImageResource(R.drawable.danish);
        }
        else if(Objects.equals(d_nation, "Dutch")){
            flag.setImageResource(R.drawable.dutch);
        }
        else if(Objects.equals(d_nation, "Finnish")){
            flag.setImageResource(R.drawable.finnish);
        }
        else if(Objects.equals(d_nation, "German")){
            flag.setImageResource(R.drawable.german);
        }
        else if(Objects.equals(d_nation, "Monaco")){
            flag.setImageResource(R.drawable.monaco);
        }
        else if(Objects.equals(d_nation, "Spanish")){
            flag.setImageResource(R.drawable.spanish);
        }
        else if(Objects.equals(d_nation, "Thailand")){
            flag.setImageResource(R.drawable.thailand);
        }
        else if(Objects.equals(d_nation, "Japanese")){
            flag.setImageResource(R.drawable.japanese);
        }
    }
}