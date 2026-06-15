package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.model.Question;
import com.example.myapplication.model.QuizEngine;

public class QuizActivity extends AppCompatActivity {

    // TODO: Add a countdown timer for the quiz later

    private TextView tvProgress, tvQuestion;
    private RadioGroup rgOptions;
    private RadioButton rbOption1, rbOption2, rbOption3;
    private Button btnNext;

    private QuizEngine quizEngine;
    private String vehicleType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Receive intent data from MainActivity
        vehicleType = getIntent().getStringExtra("VEHICLE_TYPE");
        if (vehicleType == null) {
            vehicleType = "Private Car";
        }

        // Initialize views
        tvProgress = findViewById(R.id.tvProgress);
        tvQuestion = findViewById(R.id.tvQuestion);
        rgOptions = findViewById(R.id.rgOptions);
        rbOption1 = findViewById(R.id.rbOption1);
        rbOption2 = findViewById(R.id.rbOption2);
        rbOption3 = findViewById(R.id.rbOption3);
        btnNext = findViewById(R.id.btnNext);

        // Initialize quiz engine with the selected vehicle type
        quizEngine = new QuizEngine(vehicleType);

        // Load the first question
        displayQuestion();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rgOptions.getCheckedRadioButtonId();

                // Validation: Check if user selected an answer
                if (selectedId == -1) {
                    Toast.makeText(QuizActivity.this, "Please select an answer!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check which radio button was selected
                int answerIndex = 0;
                if (selectedId == R.id.rbOption1) answerIndex = 0;
                else if (selectedId == R.id.rbOption2) answerIndex = 1;
                else if (selectedId == R.id.rbOption3) answerIndex = 2;

                // Process answer
                quizEngine.checkAnswer(answerIndex);

                // Move to next question or finish
                if (quizEngine.nextQuestion()) {
                    displayQuestion();
                } else {
                    // Quiz completed!
                    int score = quizEngine.getScore();
                    Toast.makeText(QuizActivity.this, "Quiz Finished! Your Score: " + score, Toast.LENGTH_LONG).show();

                    // TODO: Navigate to SaveResult or History Screen in Stage 3
                    finish();
                }
            }
        });
    }

    private void displayQuestion() {
        // Clear previous selection
        rgOptions.clearCheck();

        // Get current question data
        Question currentQuestion = quizEngine.getCurrentQuestion();

        if (currentQuestion != null) {
            // Update UI texts
            tvProgress.setText("Question: " + (quizEngine.getCurrentQuestionIndex() + 1) + " / " + quizEngine.getTotalQuestions());
            tvQuestion.setText(currentQuestion.getQuestionText());

            String[] options = currentQuestion.getOptions();
            rbOption1.setText(options[0]);
            rbOption2.setText(options[1]);
            rbOption3.setText(options[2]);
        }
    }
}