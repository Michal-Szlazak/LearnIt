package com.example.bettertogether.QuestionCreatorGUI;

import com.example.bettertogether.Test.AnswerRow;
import javafx.scene.control.TableView;

import java.util.List;

public class AnswersGetter {

    private final TableView<AnswerRow> tableView;
    private List<AnswerRow> answerRowList;
    public AnswersGetter(TableView<AnswerRow> tableView) {
        this.tableView = tableView;
    }
    public List<AnswerRow> getAnswers() {
        answerRowList = tableView.getItems();

        return answerRowList;
    }

}
