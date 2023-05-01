package com.example.bettertogether.QuestionCreatorGUI;

import com.example.bettertogether.Test.AnswerRow;
import com.example.bettertogether.Test.Question;

import java.util.List;

public class QuestionEditor {

    public static void refreshAnswers(Question question, List<AnswerRow> answerRows) {
        question.getAnswers().clear();
        question.addAnswers(answerRows);
        System.out.println(answerRows);
    }

}
