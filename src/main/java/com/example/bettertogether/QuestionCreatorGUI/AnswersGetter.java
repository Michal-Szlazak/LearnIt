package com.example.bettertogether.QuestionCreatorGUI;

import com.example.bettertogether.Test.Answer;
import javafx.scene.control.TableView;

import java.util.List;

public class AnswersGetter {

    private final TableView<Answer> tableView;
    private List<Answer> answerList;
    public AnswersGetter(TableView<Answer> tableView) {
        this.tableView = tableView;
    }
    public List<Answer> loadAnswers() {
        answerList = tableView.getItems();

        return answerList;
    }

}
