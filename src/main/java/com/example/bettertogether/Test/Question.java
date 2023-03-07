package com.example.bettertogether.Test;

import java.util.List;

public class Question {

    private String question;
    private List<Answer> answers;
    private QuestionType questionType;
    Question(String question, QuestionType questionType) {
        this.question = question;
        this.questionType = questionType;
    }

    public void addAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
