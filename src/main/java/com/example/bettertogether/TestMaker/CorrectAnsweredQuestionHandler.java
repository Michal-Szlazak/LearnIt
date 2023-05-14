package com.example.bettertogether.TestMaker;

import com.example.bettertogether.Test.Question;

public class CorrectAnsweredQuestionHandler {
    public static void handle(Question question) {
        int sumOfAnswers = question.getQuestionStatistics().getSumOfAnswers();
        int correctlyAnswered = question.getQuestionStatistics().getAnsweredCorrectly();
        int streak = question.getQuestionStatistics().getCorrectlyAnsweredStreak();
        question.getQuestionStatistics().setSumOfAnswers(++sumOfAnswers);
        question.getQuestionStatistics().setAnsweredCorrectly(++correctlyAnswered);
        question.getQuestionStatistics().setCorrectlyAnsweredStreak(++streak);
    }
}
