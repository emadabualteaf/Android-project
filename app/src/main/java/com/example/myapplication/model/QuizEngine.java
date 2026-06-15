package com.example.myapplication.model;

public class QuizEngine {
    private Question[] questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    public QuizEngine(String vehicleType) {
        loadQuestions(vehicleType);
    }

    private void loadQuestions(String vehicleType) {
        if (vehicleType == null) vehicleType = "Private Car";

        if (vehicleType.equals("Truck")) {
            questions = new Question[]{
                    new Question("What is the maximum speed for a truck in urban areas?", new String[]{"50 km/h", "60 km/h", "80 km/h"}, 0),
                    new Question("Are truck drivers required to take rest breaks?", new String[]{"No", "Yes, every 4 hours", "Only on weekends"}, 1)
            };
        } else if (vehicleType.equals("Motorcycle")) {
            questions = new Question[]{
                    new Question("Is a helmet mandatory for motorcycle riders?", new String[]{"Only at night", "No", "Yes, always"}, 2),
                    new Question("Where should a motorcycle ride on a lane?", new String[]{"On the right side", "Where visibility is best", "On the sidewalk"}, 1)
            };
        } else {
            questions = new Question[]{
                    new Question("What does a red traffic light mean?", new String[]{"Go", "Stop", "Slow down"}, 1),
                    new Question("When should you use high beams?", new String[]{"In heavy traffic", "On unlit roads", "Always at night"}, 1)
            };
        }
    }

    public Question getCurrentQuestion() {
        return (questions != null && currentQuestionIndex < questions.length) ? questions[currentQuestionIndex] : null;
    }

    public boolean nextQuestion() {
        if (questions != null && currentQuestionIndex < questions.length - 1) {
            currentQuestionIndex++;
            return true;
        }
        return false;
    }

    public boolean checkAnswer(int selectedOptionIndex) {
        if (questions != null && currentQuestionIndex < questions.length) {
            if (questions[currentQuestionIndex].getCorrectAnswerIndex() == selectedOptionIndex) {
                score++;
                return true;
            }
        }
        return false;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return (questions != null) ? questions.length : 0;
    }
}