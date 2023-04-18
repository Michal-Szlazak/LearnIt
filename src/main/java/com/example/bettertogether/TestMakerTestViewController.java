package com.example.bettertogether;

import com.example.bettertogether.QuestionCreatorGUI.AnswerTableView;
import com.example.bettertogether.Test.Answer;
import com.example.bettertogether.Test.Test;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class TestMakerTestViewController {

    private Test test;
    private AnswerTableView answersTV;
    @FXML
    private TableView<Answer> answerTableView;
    public void initialize() {
        answersTV = new AnswerTableView(answerTableView);
    }
    public void setTest(Test test) {
        this.test = test;
    }
}
