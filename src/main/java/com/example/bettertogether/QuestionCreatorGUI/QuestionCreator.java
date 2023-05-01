package com.example.bettertogether.QuestionCreatorGUI;

import com.example.bettertogether.Test.AnswerRow;
import com.example.bettertogether.Test.Question;
import javafx.scene.control.TextField;

import java.util.List;

public class QuestionCreator {

    private final TextField questionTextField;

    public QuestionCreator(TextField question) {
        this.questionTextField = question;
    }

    public Question createQuestion(List<AnswerRow> answerRowList) {
        Question question = new Question();
        question.setQuestion(questionTextField.getText());
        question.addAnswers(answerRowList);
        return question;
    }


}
