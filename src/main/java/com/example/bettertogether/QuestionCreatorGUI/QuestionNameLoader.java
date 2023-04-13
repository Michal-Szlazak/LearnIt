package com.example.bettertogether.QuestionCreatorGUI;

import com.example.bettertogether.Test.Answer;
import com.example.bettertogether.Test.Question;
import javafx.scene.control.TextField;

import java.util.List;

public class QuestionNameLoader {

    private final TextField questionTextField;

    public QuestionNameLoader(TextField question) {
        this.questionTextField = question;
    }

    public Question createQuestion(List<Answer> answerList) {
        Question question = new Question();
        question.setQuestion(questionTextField.getText());
        question.addAnswers(answerList);
        return question;
    }
}
