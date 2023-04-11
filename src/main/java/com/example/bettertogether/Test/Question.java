package com.example.bettertogether.Test;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Question {

    @JsonProperty
    private String question;
    @JsonProperty
    private List<Answer> answers;
    public Question(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return question;
    }

    public void addAnswers(List<Answer> answers) {
        this.answers = answers;
    }
    public String getQuestion() {
        return question;
    }
    public List<Answer> getAnswers() {
        return answers;
    }
}
