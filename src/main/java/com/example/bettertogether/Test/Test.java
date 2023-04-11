package com.example.bettertogether.Test;

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

    public void setTestName(String testName) {
        this.testName = testName;
    }
    public String getTestName() {
        return testName;
    }

}
