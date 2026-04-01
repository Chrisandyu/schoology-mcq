package com.example;

import java.util.ArrayList;

public class Question {
    private String question;
    private ArrayList<String> responses;
    private int numResponses;
    private int correctAnswer;

    public Question(String q, ArrayList<String> choices, int numChoices, int answer) {
        question = q;
        responses = choices;
        numResponses = numChoices;
        correctAnswer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getResponses() {
        return responses;
    }

    public int getNumResponses() {
        return numResponses;
    }

    public void setNumResponses(int numResponses) {
        this.numResponses = numResponses;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

}
