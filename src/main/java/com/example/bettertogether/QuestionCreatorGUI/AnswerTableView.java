package com.example.bettertogether.QuestionCreatorGUI;

import com.example.bettertogether.Test.Answer;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AnswerTableView{

    private final TableView<Answer> tableView;
    private final TableColumn<Answer, String> answerId;
    private final TableColumn<Answer, String> answer;
    private final TableColumn<Answer, CheckBox> isCorrect;
    private char id;

    public AnswerTableView(TableView<Answer> tableView) {
        this.tableView = tableView;

        answerId = new TableColumn<>();
        answer = new TableColumn<>();
        isCorrect = new TableColumn<>();

        answerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        answerId.setText("Id");
        answer.setCellValueFactory(new PropertyValueFactory<>("answer"));
        answer.setText("Answer");
        isCorrect.setCellValueFactory(new PropertyValueFactory<>("isCorrect"));
        isCorrect.setText("Is correct");
        this.tableView.getColumns().add(answerId);
        this.tableView.getColumns().add(answer);
        this.tableView.getColumns().add(isCorrect);

        id = 'A';

        this.tableView.widthProperty().addListener((obs, oldVal, newVal) -> {
            double tableWidth = (double) newVal;
            System.out.println(tableView.getWidth() + tableView.getPrefWidth());
            answerId.setPrefWidth(tableWidth * 0.1);
            answer.setPrefWidth(tableWidth * 0.8);
            isCorrect.setPrefWidth(tableWidth * 0.1);
        });
    }

    public void addNewAnswer() {
        Answer answer = new Answer();
        answer.setId(Character.toString(id++));
        tableView.getItems().add(answer);
    }

    public void deleteAnswer() {
        Answer answer = tableView.getSelectionModel().getSelectedItem();
        if(answer == null) {
            return;
        }

        int removeIndex = tableView.getItems().indexOf(answer);
        List<Answer> answers = tableView.getItems();
        for(int i = removeIndex; i < answers.size(); i++) {
            Label temp = answers.get(i).getId();
            char id = temp.getText().charAt(0);
            answers.get(i).setId(Character.toString(--id));
        }

        tableView.getItems().remove(answer);
        id--;
    }

}
