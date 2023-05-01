package com.example.bettertogether.Test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionStatistics {
    private int answeredCorrectly;
    private int answeredIncorrectly;

    public QuestionStatistics() {
        answeredCorrectly = 0;
        answeredIncorrectly = 0;
    }

    @JsonProperty("answeredCorrectly")
    public int getAnsweredCorrectly() {
        return answeredCorrectly;
    }
    @JsonProperty("answeredInCorrectly")
    public int getAnsweredIncorrectly() {
        return answeredIncorrectly;
    }
    @JsonProperty("answeredCorrectly")
    public void setAnsweredCorrectly(int answeredCorrectly) {
        this.answeredCorrectly = answeredCorrectly;
    }
    @JsonProperty("answeredInCorrectly")
    public void setAnsweredIncorrectly(int answeredIncorrectly) {
        this.answeredIncorrectly = answeredIncorrectly;
    }
}
