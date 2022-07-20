package com.example.dogmythsquizapp;

public class Question {
    String qText;
    boolean correctAnswer;
    int picture;

    public Question(String qText, boolean correctAnswer, int picture) {
        this.qText = qText;
        this.correctAnswer = correctAnswer;
        this.picture = picture;
    }


    public String getQText() {
        return qText;
    }

    public void setQText(String QqText) {
        this.qText = qText;
    }

    public boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }


    @Override
    public String toString() {
        return "Question{" +
                "qText='" + qText + '\'' +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}
