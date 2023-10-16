package com.example.bettertogether.QuestionCreatorGUI;

import com.example.bettertogether.Test.AnswerRow;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AnswerTableView{

    private final TableView<AnswerRow> tableView;
    private final TableColumn<AnswerRow, String> answerId;
    private final TableColumn<AnswerRow, String> answer;
    private final TableColumn<AnswerRow, CheckBox> isCorrect;
    private char id;

    public AnswerTableView(TableView<AnswerRow> tableView) {
        this.tableView = tableView;

        answerId = new TableColumn<>();
        answer = new TableColumn<>();
        isCorrect = new TableColumn<>();

        answerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        answerId.setText("Id");
        answer.setCellValueFactory(new PropertyValueFactory<>("answer"));
        answer.setText("AnswerRow");
        isCorrect.setCellValueFactory(new PropertyValueFactory<>("isCorrect"));
        isCorrect.setText("Is correct");
        this.tableView.getColumns().add(answerId);
        this.tableView.getColumns().add(answer);
        this.tableView.getColumns().add(isCorrect);

        id = 'A';

        this.tableView.widthProperty().addListener((obs, oldVal, newVal) -> {
            double tableWidth = (double) newVal;
            answerId.setPrefWidth(tableWidth * 0.1);
            answer.setPrefWidth(tableWidth * 0.8);
            isCorrect.setPrefWidth(tableWidth * 0.1);
        });
    }

    public void addNewAnswer() {
        AnswerRow answerRow = new AnswerRow();
        answerRow.setId(Character.toString(id++));
        tableView.getItems().add(answerRow);
    }

    public void addAnswer(AnswerRow answerRow) {
        id++;
        tableView.getItems().add(answerRow);
    }

    public void deleteAnswer() {
        AnswerRow answerRow = tableView.getSelectionModel().getSelectedItem();
        if(answerRow == null) {
            return;
        }

        int removeIndex = tableView.getItems().indexOf(answerRow);
        List<AnswerRow> answerRows = tableView.getItems();
        for(int i = removeIndex; i < answerRows.size(); i++) {
            String temp = answerRows.get(i).getId().getText();
            char id = temp.charAt(0);
            answerRows.get(i).setId(Character.toString(--id));
        }

        tableView.getItems().remove(answerRow);
        id--;
    }
}
