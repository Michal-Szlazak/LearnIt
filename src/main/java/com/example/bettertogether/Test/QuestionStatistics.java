package com.example.bettertogether.Test;

public class QuestionStatistics {
    private int answeredCorrectly;
    private int answeredIncorrectly;

    public QuestionStatistics() {
        answeredCorrectly = 0;
        answeredIncorrectly = 0;
    }

    public int getAnsweredCorrectly() {
        return answeredCorrectly;
    }
    public int getAnsweredIncorrectly() {
        return answeredIncorrectly;
    }

    public void setAnsweredCorrectly(int answeredCorrectly) {
        this.answeredCorrectly = answeredCorrectly;
    }

    public void setAnsweredIncorrectly(int answeredIncorrectly) {
        this.answeredIncorrectly = answeredIncorrectly;
    }
}
