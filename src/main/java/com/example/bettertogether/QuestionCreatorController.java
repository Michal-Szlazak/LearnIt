package com.example.bettertogether;

import com.example.bettertogether.QuestionCreatorGUI.AnswerTableView;
import com.example.bettertogether.Test.Answer;
import com.example.bettertogether.Test.TestModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class QuestionCreatorController {

    @FXML
    private TableView<Answer> tableView;
    private AnswerTableView answerTableView;
    @FXML
    private Button addQuestionButton;
    @FXML
    private Button deleteQuestionButton;

    public void initialize() {
        answerTableView = new AnswerTableView(tableView);
        addQuestionButton.setOnAction(event -> {
            answerTableView.addNewAnswer();
        });
        deleteQuestionButton.setOnAction(event -> {
            answerTableView.deleteAnswer();
        });
    }
    public void setTestModel(TestModel test) {
    }

    public void goToTestCreator(ActionEvent event) throws IOException {
        double width;
        double height;

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TestCreator.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        width = stage.getScene().getWidth();
        height = stage.getScene().getHeight();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

}
