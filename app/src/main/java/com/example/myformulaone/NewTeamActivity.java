package com.example.myformulaone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewTeamActivity extends AppCompatActivity {

    //creating a variables for our button and edittext.
    private EditText teamNameEdt, teamLocationEdt, teamCarEdt;
    private Button teamBtn;

    //creating a constant string variable for our course name, description and duration.
    public static final String EXTRA_ID =
            "com.example.project1.EXTRA_ID";
    public static final String EXTRA_TEAM_NAME =
            "com.example.project1.EXTRA_TEAM_NAME";
    public static final String EXTRA_TEAM_LOCATION =
            "com.example.project1.EXTRA_TEAM_LOCATION";
    public static final String EXTRA_TEAM_CAR =
            "com.example.project1.EXTRA_TEAM_CAR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_team);
        //initializing our variables for each view.
        teamNameEdt = findViewById(R.id.idEdtTeamName);
        teamLocationEdt = findViewById(R.id.idEdtTeamLocation);
        teamCarEdt = findViewById(R.id.idEdtTeamCar);
        teamBtn = findViewById(R.id.idBtnSaveTeam);
        //below line is to get intent as we are getting data via an intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            //if we get id for our data then we are setting values to our edit text fields.
            teamNameEdt.setText(intent.getStringExtra(EXTRA_TEAM_NAME));
            teamLocationEdt.setText(intent.getStringExtra(EXTRA_TEAM_LOCATION));
            teamCarEdt.setText(intent.getStringExtra(EXTRA_TEAM_CAR));
        }
        //adding on click listner for our save button.
        teamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting text value from edittext and validating if the text fields are empty or not.
                String teamName = teamNameEdt.getText().toString();
                String teamLocation = teamLocationEdt.getText().toString();
                String teamCar = teamCarEdt.getText().toString();
                if (teamName.isEmpty() || teamLocation.isEmpty() || teamCar.isEmpty()) {
                    Toast.makeText(NewTeamActivity.this, "Please enter the valid Team details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                //calling a method to save our course.
                saveTeam(teamName, teamLocation, teamCar);
            }
        });

    }

    private void saveTeam(String teamName,String teamLocation,String teamCar) {
        //inside this method we are passing all the data via an intent.
        Intent data = new Intent();
        //in below line we are passing all our course detail.
        data.putExtra(EXTRA_TEAM_NAME, teamName);
        data.putExtra(EXTRA_TEAM_LOCATION, teamLocation);
        data.putExtra(EXTRA_TEAM_CAR, teamCar);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            //in below line we are passing our id.
            data.putExtra(EXTRA_ID, id);
        }
        //at last we are setting result as data.
        setResult(RESULT_OK, data);
        //displaying a toast message after adding the data
        Toast.makeText(this, "Team has been saved to Team Database. ", Toast.LENGTH_SHORT).show();
    }

}