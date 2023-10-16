package com.example.bettertogether.Test;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Question {
    private String question;
    private List<Answer> answers;
    private QuestionStatistics questionStatistics = new QuestionStatistics();

    public void addAnswers(List<Answer> answers) {
        this.answers = answers;
    }
    public String getQuestion() {
        return question;
    }
    public List<Answer> getAnswers() {
        return answers;
    }
    public QuestionStatistics getQuestionStatistics() {
        return questionStatistics;
    }

    @JsonProperty("question")
    public void setQuestion(String question) {
        this.question = question;
    }
    @JsonProperty("answers")
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
    @JsonProperty("questionStatistics")
    public void setQuestionStatistics(QuestionStatistics questionStatistics) {
        this.questionStatistics = questionStatistics;
    }

    @Override
    public String toString() {
        return question;
    }
}