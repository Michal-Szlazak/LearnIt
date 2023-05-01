package com.example.bettertogether.Test;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Question {


    private String question;
    private List<AnswerRow> answerRows;
    private QuestionStatistics questionStatistics = new QuestionStatistics();
    @Override
    public String toString() {
        return question;
    }
    public void addAnswers(List<AnswerRow> answerRows) {
        this.answerRows = answerRows;
    }
    public String getQuestion() {
        return question;
    }
    public List<AnswerRow> getAnswers() {
        return answerRows;
    }
    public QuestionStatistics getQuestionStatistics() {
        return questionStatistics;
    }

    @JsonProperty("question")
    public void setQuestion(String question) {
        this.question = question;
    }
    @JsonProperty("answerRows")
    public void setAnswers(List<AnswerRow> answerRows) {
        this.answerRows = answerRows;
    }
    @JsonProperty("questionStatistics")
    public void setQuestionStatistics(QuestionStatistics questionStatistics) {
        this.questionStatistics = questionStatistics;
    }
}