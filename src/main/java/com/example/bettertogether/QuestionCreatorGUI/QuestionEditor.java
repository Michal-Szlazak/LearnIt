package com.example.bettertogether.QuestionCreatorGUI;

import com.example.bettertogether.Test.Answer;
import com.example.bettertogether.Test.Question;

import java.util.List;

public class QuestionEditor {

    public static void refreshAnswers(Question question, List<Answer> answers) {
        question.getAnswers().clear();
        question.addAnswers(answers);
        System.out.println(answers);
    }

}
