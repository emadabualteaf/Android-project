package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;
import java.util.concurrent.Executors;

public class HistoryActivity extends AppCompatActivity {
    private List<String> list = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ListView listView = findViewById(R.id.listViewHistory);
        Button btnClear = findViewById(R.id.btnClear); // تأكد من وجود هذا الـ ID في activity_history.xml

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        loadHistory();

        btnClear.setOnClickListener(v -> {
            Executors.newSingleThreadExecutor().execute(() -> {
                AppDatabase.getInstance(this).historyDao().clearAllHistory();

                runOnUiThread(() -> {
                    list.clear();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(this, "History Cleared!", Toast.LENGTH_SHORT).show();
                });
            });
        });
    }

    private void loadHistory() {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<HistoryRecord> records = AppDatabase.getInstance(this).historyDao().getAllHistory();
            list.clear();
            for (HistoryRecord r : records) {
                list.add(r.getVehicleType() + ": " + r.getScore() + "/" + r.getTotalQuestions());
            }
            runOnUiThread(adapter::notifyDataSetChanged);
        });
    }
}