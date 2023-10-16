package com.example.bettertogether.QuestionCreatorGUI;

import com.example.bettertogether.Test.Answer;
import com.example.bettertogether.Test.AnswerRow;
import com.example.bettertogether.Test.Question;

import java.util.List;

public class QuestionEditor {

    public static void refreshAnswers(Question question, List<AnswerRow> answerRows) {
        question.getAnswers().clear();
        for(AnswerRow answerRow : answerRows) {
            Answer answer = new Answer(answerRow.getId().getText(), answerRow.getAnswer().getText(),
                    answerRow.getIsCorrect().isSelected());
            question.getAnswers().add(answer);
        }
    }
}
