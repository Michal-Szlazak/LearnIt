package com.example.bettertogether.Test;

import java.util.List;

public class Question {

    private String question;
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
