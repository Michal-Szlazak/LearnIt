package com.example.bettertogether.Test;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Answer {

    private String id;
    private String answer;
    private boolean correct;
    public Answer() {}
    public Answer(String id, String answer, boolean correct) {
        this.id = id;
        this.answer = answer;
        this.correct = correct;
    }
    @JsonGetter("id")
    public String getId() {
        return id;
    }
    @JsonGetter("answer")
    public String getAnswer() {
        return answer;
    }
    @JsonGetter("correct")
    public boolean getCorrect() {
        return correct;
    }
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }
    @JsonProperty("answer")
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    @JsonProperty("correct")
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
