package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnCar).setOnClickListener(v -> startQuiz("Private Car"));
        findViewById(R.id.btnTruck).setOnClickListener(v -> startQuiz("Truck"));
        findViewById(R.id.btnMotorcycle).setOnClickListener(v -> startQuiz("Motorcycle"));
        findViewById(R.id.btnViewHistory).setOnClickListener(v -> startActivity(new Intent(this, HistoryActivity.class)));
    }

    private void startQuiz(String type) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("VEHICLE_TYPE", type);
        startActivity(intent);
    }
}