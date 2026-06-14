package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // TODO: maybe save last selected vehicle using SharedPreferences

    Spinner spinnerVehicle;
    Button btnStartQuiz, btnViewHistory;
    ImageView imgTrafficSign;
    TextView tvWelcome;

    String selectedVehicle = "Private Car";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find views
        tvWelcome      = findViewById(R.id.tvWelcome);
        imgTrafficSign = findViewById(R.id.imgTrafficSign);
        spinnerVehicle = findViewById(R.id.spinnerVehicle);
        btnStartQuiz   = findViewById(R.id.btnStartQuiz);
        btnViewHistory = findViewById(R.id.btnViewHistory);

        // spinner setup
        String[] vehicles = {"Private Car", "Truck", "Motorcycle"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, vehicles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVehicle.setAdapter(adapter);

        spinnerVehicle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedVehicle = vehicles[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass the vehicle type to QuizActivity
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("VEHICLE_TYPE", selectedVehicle);
                startActivity(intent);
            }
        });

        btnViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
            }
        });

    } // end onCreate

}