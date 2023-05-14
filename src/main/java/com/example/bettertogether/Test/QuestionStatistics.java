package com.example.bettertogether.Test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionStatistics {
    private int answeredCorrectly;
    private int sumOfAnswers;
    private int correctlyAnsweredStreak;

    public QuestionStatistics() {
        answeredCorrectly = 0;
        sumOfAnswers = 0;
        correctlyAnsweredStreak = 0;
    }

    @JsonProperty("answeredCorrectly")
    public int getAnsweredCorrectly() {
        return answeredCorrectly;
    }
    @JsonProperty("sumOfAnswers")
    public int getSumOfAnswers() {
        return sumOfAnswers;
    }
    @JsonProperty("correctlyAnsweredStreak")
    public int getCorrectlyAnsweredStreak() {
        return correctlyAnsweredStreak;
    }
    @JsonProperty("answeredCorrectly")
    public void setAnsweredCorrectly(int answeredCorrectly) {
        this.answeredCorrectly = answeredCorrectly;
    }
    @JsonProperty("sumOfAnswers")
    public void setSumOfAnswers(int sumOfAnswers) {
        this.sumOfAnswers = sumOfAnswers;
    }
    @JsonProperty("correctlyAnsweredStreak")
    public void setCorrectlyAnsweredStreak(int correctlyAnsweredStreak) {
        this.correctlyAnsweredStreak = correctlyAnsweredStreak;
    }
}
