package com.example.myapplication;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.model.QuizEngine;
import java.util.concurrent.Executors;

public class QuizActivity extends AppCompatActivity {
    private TextView tvQuestion, tvProgress;
    private RadioGroup rgOptions;
    private RadioButton rb1, rb2, rb3;
    private QuizEngine quizEngine;
    private String vehicleType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvProgress = findViewById(R.id.tvProgress);
        rgOptions = findViewById(R.id.rgOptions);
        rb1 = findViewById(R.id.rbOption1);
        rb2 = findViewById(R.id.rbOption2);
        rb3 = findViewById(R.id.rbOption3);

        vehicleType = getIntent().getStringExtra("VEHICLE_TYPE");
        quizEngine = new QuizEngine(vehicleType);
        displayQuestion();

        findViewById(R.id.btnNext).setOnClickListener(v -> {
            int selectedId = rgOptions.getCheckedRadioButtonId();
            if (selectedId == -1) return;

            int index = (selectedId == R.id.rbOption1) ? 0 : (selectedId == R.id.rbOption2) ? 1 : 2;
            quizEngine.checkAnswer(index);

            if (quizEngine.nextQuestion()) displayQuestion();
            else {
                saveToDatabase();
                finish();
            }
        });
    }

    private void displayQuestion() {
        tvQuestion.setText(quizEngine.getCurrentQuestion().getQuestionText());
        String[] opt = quizEngine.getCurrentQuestion().getOptions();
        rb1.setText(opt[0]); rb2.setText(opt[1]); rb3.setText(opt[2]);
        rgOptions.clearCheck();
        tvProgress.setText("Question " + (quizEngine.getCurrentQuestionIndex() + 1) + "/" + quizEngine.getTotalQuestions());
    }

    private void saveToDatabase() {
        Executors.newSingleThreadExecutor().execute(() -> {
            HistoryRecord rec = new HistoryRecord();
            rec.setVehicleType(vehicleType);
            rec.setScore(quizEngine.getScore());
            rec.setTotalQuestions(quizEngine.getTotalQuestions());

            AppDatabase.getInstance(this).historyDao().insertRecord(rec);
        });
    }

}