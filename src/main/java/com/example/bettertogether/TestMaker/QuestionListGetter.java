package com.example.bettertogether.TestMaker;

import com.example.bettertogether.Test.Question;
import com.example.bettertogether.Test.Test;
import com.example.bettertogether.TestSettings.TestMode;
import com.example.bettertogether.TestSettings.TestSettingsHolder;

import java.util.ArrayList;
import java.util.List;

public class QuestionListGetter {
    public static List<Question> getQuestionList(Test test) {
        List<Question> questionList = new ArrayList<>(test.getQuestions());
        if(TestSettingsHolder.testMode == TestMode.QUICK) {
            questionList = getQuickTestQuestions(test);
        }
        if(TestSettingsHolder.shuffleQuestions) {
            return QuestionShuffler.shuffleQuestions(questionList);
        }
        return questionList;
    }

    private static List<Question> getQuickTestQuestions(Test test) {
        List<Question> questionList = new ArrayList<>();
        for(Question question : test.getQuestions()) {
            double statistics = (double) question.getQuestionStatistics().getAnsweredCorrectly() /
                    question.getQuestionStatistics().getSumOfAnswers();
            if(statistics < 1 && question.getQuestionStatistics().getCorrectlyAnsweredStreak() < 3) {
                questionList.add(question);
            }
        }
        return questionList;
    }
}
