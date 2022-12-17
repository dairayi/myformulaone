package com.example.myformulaone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewDriverActivity extends AppCompatActivity {

    //creating a variables for our button and edittext.
    private EditText driverCodeEdt, driverNumberEdt, driverNameEdt, driverNationalityEdt, driverTeamEdt;
    private Button driverBtn;

    //creating a constant string variable for our course name, description and duration.
    public static final String EXTRA_ID =
            "com.example.project1.EXTRA_ID";
    public static final String EXTRA_DRIVER_CODE =
            "com.example.project1.EXTRA_DRIVER_CODE";
    public static final String EXTRA_DRIVER_NUMBER =
            "com.example.project1.EXTRA_DRIVER_NUMBER";
    public static final String EXTRA_DRIVER_NAME =
            "com.example.project1.EXTRA_DRIVER_NAME";
    public static final String EXTRA_DRIVER_NATIONALITY =
            "com.example.project1.EXTRA_DRIVER_NATIONALITY";
    public static final String EXTRA_DRIVER_TEAM =
            "com.example.project1.EXTRA_DRIVER_TEAM";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_driver);
        //initializing our variables for each view.
        driverCodeEdt = findViewById(R.id.idEdtDriverCode);
        driverNumberEdt = findViewById(R.id.idEdtDriverNumber);
        driverNameEdt = findViewById(R.id.idEdtDriverName);
        driverNationalityEdt = findViewById(R.id.idEdtDriverNationality);
        driverTeamEdt = findViewById(R.id.idEdtDriverTeam);
        driverBtn = findViewById(R.id.idBtnSaveDriver);
        //below line is to get intent as we are getting data via an intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            //if we get id for our data then we are setting values to our edit text fields.
            driverCodeEdt.setText(intent.getStringExtra(EXTRA_DRIVER_CODE));
            driverNumberEdt.setText(intent.getStringExtra(EXTRA_DRIVER_NUMBER));
            driverNameEdt.setText(intent.getStringExtra(EXTRA_DRIVER_NAME));
            driverNationalityEdt.setText(intent.getStringExtra(EXTRA_DRIVER_NATIONALITY));
            driverTeamEdt.setText(intent.getStringExtra(EXTRA_DRIVER_TEAM));
        }
        //adding on click listner for our save button.
        driverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting text value from edittext and validating if the text fields are empty or not.
                String driverCode = driverCodeEdt.getText().toString();
                String driverNumber = driverNumberEdt.getText().toString();
                String driverName = driverNameEdt.getText().toString();
                String driverNationality = driverNationalityEdt.getText().toString();
                String driverTeam = driverTeamEdt.getText().toString();
                if (driverCode.isEmpty() || driverNumber.isEmpty() || driverName.isEmpty() || driverNationality.isEmpty() || driverTeam.isEmpty()) {
                    Toast.makeText(NewDriverActivity.this, "Please enter the valid Driver details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                //calling a method to save our course.
                saveDriver(driverCode, driverNumber, driverName, driverNationality, driverTeam);
            }
        });

    }

    private void saveDriver(String driverCode,String driverNumber,String driverName,String driverNationality,String driverTeam) {
        //inside this method we are passing all the data via an intent.
        Intent data = new Intent();
        //in below line we are passing all our course detail.
        data.putExtra(EXTRA_DRIVER_CODE, driverCode);
        data.putExtra(EXTRA_DRIVER_NUMBER, driverNumber);
        data.putExtra(EXTRA_DRIVER_NAME, driverName);
        data.putExtra(EXTRA_DRIVER_NATIONALITY, driverNationality);
        data.putExtra(EXTRA_DRIVER_TEAM, driverTeam);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            //in below line we are passing our id.
            data.putExtra(EXTRA_ID, id);
        }
        //at last we are setting result as data.
        setResult(RESULT_OK, data);
        //displaying a toast message after adding the data
        Toast.makeText(this, "Driver has been saved to Driver Database. ", Toast.LENGTH_SHORT).show();
    }

}