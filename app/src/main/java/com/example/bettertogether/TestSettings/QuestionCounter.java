package com.example.bettertogether.TestSettings;

import com.example.bettertogether.Test.Question;
import com.example.bettertogether.Test.Test;

public class QuestionCounter {

    public static int getNumberOfFullTestQuestions(Test test) {
        return test.getQuestions().size();
    }
    public static int getNumberOfQuickTestQuestions(Test test) {
        int sumQuestions = 0;

        for(Question question : test.getQuestions()) {
            double statistics = (double) question.getQuestionStatistics().getAnsweredCorrectly() /
                    question.getQuestionStatistics().getSumOfAnswers();
            if(statistics < 1 && question.getQuestionStatistics().getCorrectlyAnsweredStreak() < 3) {
                sumQuestions++;
            }
        }
        return sumQuestions;
    }
}
