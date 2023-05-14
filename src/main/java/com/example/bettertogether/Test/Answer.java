package com.example.bettertogether.Test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Answer {

    private String id;
    private String answer;
    private boolean isCorrect;
    public Answer() {}
    public Answer(String id, String answer, boolean isCorrect) {
        this.id = id;
        this.answer = answer;
        this.isCorrect = isCorrect;
    }
    public String getId() {
        return id;
    }
    public String getAnswer() {
        return answer;
    }
    public boolean getIsCorrect() {
        return isCorrect;
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
    public void setIsCorrect(boolean correct) {
        isCorrect = correct;
    }
}
