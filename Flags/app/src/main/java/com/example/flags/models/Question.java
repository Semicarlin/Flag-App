package com.example.flags.models;

import java.util.ArrayList;

public class Question {

    // Attributes
    private ArrayList<Country> answers;
    private Country correctAnswer;

    // Constructor
    public Question() {
        answers = new ArrayList<>();
    }

    // Getters/setters
    public ArrayList<Country> getAnswers() {
        return answers;
    }

    public Country getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Country correctAnswer) {
        this.correctAnswer = correctAnswer;
        assert answers.contains(correctAnswer);
    }

    public void setAnswers(ArrayList<Country> answers) {
        this.answers = answers;
    }

    // Methods
    public void addAnswer(Country answer) {
        answers.add(answer);
    }
}
