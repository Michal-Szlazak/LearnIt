package com.example.bettertogether.Test;

public class TestStatisticsResetter {
    public static void reset(Test test) {
        for(Question question : test.getQuestions()) {
            question.setQuestionStatistics(new QuestionStatistics());
        }
    }
}
