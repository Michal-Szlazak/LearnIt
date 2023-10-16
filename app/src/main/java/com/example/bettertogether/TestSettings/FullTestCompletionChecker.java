package com.example.bettertogether.TestSettings;

import com.example.bettertogether.Test.Question;
import com.example.bettertogether.Test.Test;

public class FullTestCompletionChecker {

    public static boolean checkIfFullTestWasCompleted(Test test) {
        for(Question question : test.getQuestions()) {
            if(question.getQuestionStatistics().getSumOfAnswers() == 0) {
                return false;
            }
        }
        return true;
    }
}
