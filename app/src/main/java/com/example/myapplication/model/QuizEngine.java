package com.example.myapplication.model;

import java.util.ArrayList;
import java.util.List;

public class QuizEngine {

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private String vehicleType;

    public QuizEngine(String vehicleType) {
        this.vehicleType = vehicleType;
        this.questions = new ArrayList<>();
        generateQuestions();
    }

    private void generateQuestions() {
        if ("Truck".equalsIgnoreCase(vehicleType)) {
            questions.add(new Question("What is the maximum speed limit for a heavy truck on an urban road?", new String[]{"50 km/h", "80 km/h", "90 km/h"}, 0));
            questions.add(new Question("What should you check before driving a loaded truck down a steep hill?", new String[]{"Air brakes and gear", "The radio volume", "Fuel cap only"}, 0));
        } else if ("Motorcycle".equalsIgnoreCase(vehicleType)) {
            questions.add(new Question("Is wearing a helmet mandatory for motorcycle riders?", new String[]{"Only on highways", "Yes, always", "No, it's optional"}, 1));
            questions.add(new Question("How should you brake effectively on a motorcycle?", new String[]{"Rear brake only", "Front brake only", "Both front and rear brakes evenly"}, 2));
        } else {
            // Default: Private Car
            questions.add(new Question("What does a flashing red traffic light mean?", new String[]{"Slow down", "Stop completely, then proceed when safe", "Accelerate quickly"}, 1));
            questions.add(new Question("What is the legal blood alcohol limit for young drivers?", new String[]{"0.0%", "0.05%", "0.08%"}, 0));
        }
    }

    public Question getCurrentQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex);
        }
        return null;
    }

    public void checkAnswer(int selectedIndex) {
        Question current = getCurrentQuestion();
        if (current != null && selectedIndex == current.getCorrectAnswerIndex()) {
            score++;
        }
    }

    public boolean nextQuestion() {
        currentQuestionIndex++;
        return currentQuestionIndex < questions.size();
    }

    public int getScore() {
        return score;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public int getTotalQuestions() {
        return questions.size();
    }
}