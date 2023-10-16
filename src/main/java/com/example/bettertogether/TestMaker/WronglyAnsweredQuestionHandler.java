package com.example.bettertogether.TestMaker;

import com.example.bettertogether.Test.Question;

public class WronglyAnsweredQuestionHandler {
    public static void handle(Question question) {
        int sumOfAnswers = question.getQuestionStatistics().getSumOfAnswers();
        question.getQuestionStatistics().setSumOfAnswers(++sumOfAnswers);
        question.getQuestionStatistics().setCorrectlyAnsweredStreak(0);
    }
}
