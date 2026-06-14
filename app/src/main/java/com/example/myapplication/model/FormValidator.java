package com.example.myapplication.model;

public class FormValidator {

    private static final int MAX_LENGTH = 200;

    //

    public boolean isValidNotes(String notes) {

        if (notes == null || notes.trim().isEmpty()) {
            return false;
        }
        if (notes.trim().length() > MAX_LENGTH) {
            return false;
        }
        return true;
    }

    public String getError(String notes) {
        if (notes == null || notes.trim().isEmpty()) {
            return "Please write a note before saving.";
        }
        if (notes.trim().length() > MAX_LENGTH) {
            return "Note is too long. Maximum " + MAX_LENGTH + " characters allowed.";
        }
        return null;
    }
}