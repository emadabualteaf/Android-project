package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;
import java.util.concurrent.Executors;

public class HistoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ListView listView = findViewById(R.id.listViewHistory);
        List<String> list = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        Executors.newSingleThreadExecutor().execute(() -> {
            List<HistoryRecord> records = AppDatabase.getInstance(this).historyDao().getAllHistory();
            for (HistoryRecord r : records) list.add(r.getVehicleType() + ": " + r.getScore() + "/" + r.getTotalQuestions());
            runOnUiThread(adapter::notifyDataSetChanged);
        });
    }
}