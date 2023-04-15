package com.example.bettertogether.QuestionCreatorGUI;

import com.example.bettertogether.Test.Answer;
import com.example.bettertogether.Test.Question;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AnswerLoader {

    private final TextField questionTextField;
    private final AnswerTableView answerTableView;

    public AnswerLoader(TextField questionTextField, AnswerTableView answerTableView) {
        this.questionTextField = questionTextField;
        this.answerTableView = answerTableView;
    }

    public void loadQuestion(Question question) {
        questionTextField.setText(question.toString());
        for(Answer answer : question.getAnswers()) {
            answerTableView.addAnswer(answer);
        }
    }

}
