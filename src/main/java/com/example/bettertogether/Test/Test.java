package com.example.bettertogether.Test;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private String testName;
    private List<Question> questions;
    public Test() {
        questions = new ArrayList<>();
    }
    @Override
    public String toString() {
        return String.format("{testName:%s,questions:%s}", testName, questions);
    }

    public void addNewQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }
    public String getTestName() {
        return testName;
    }
    @JsonProperty("testName")
    public void setTestName(String testName) {
        this.testName = testName;
    }
    @JsonProperty("questions")
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
