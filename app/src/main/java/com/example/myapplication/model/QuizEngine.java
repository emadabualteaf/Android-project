package com.example.myapplication.model;

public class QuizEngine {

    private int correctAnswers;
    private int totalQuestions;


    private static final double PASS_THRESHOLD = 60.0;

    public QuizEngine(int correctAnswers, int totalQuestions) {
        this.correctAnswers = correctAnswers;
        this.totalQuestions = totalQuestions;
    }

    public double calculatePercentage() {
        if (totalQuestions == 0) return 0;
        return (correctAnswers * 100.0) / totalQuestions;
    }

    public boolean hasPassed() {
        return calculatePercentage() >= PASS_THRESHOLD;
    }

    public int getCorrectAnswers() { return correctAnswers; }
    public int getTotalQuestions() { return totalQuestions; }
}