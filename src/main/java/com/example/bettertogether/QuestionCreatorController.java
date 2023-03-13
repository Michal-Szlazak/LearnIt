package com.example.bettertogether;

import com.example.bettertogether.Test.Answer;
import com.example.bettertogether.Test.Test;
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

public class QuestionCreatorController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private TestModel testModel;
    @FXML
    private TableView tableView;
    private static char id = 'A';

    public void setTestModel(TestModel test) {
        this.testModel = test;
    }

    public void goToTestCreator(ActionEvent event) throws IOException {
        double width;
        double height;

        root = FXMLLoader.load(getClass().getResource("TestCreator.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        width = stage.getScene().getWidth();
        height = stage.getScene().getHeight();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    public void addNewAnswer(ActionEvent event) {

        Answer answer = new Answer();

        Label answerId = new Label(String.valueOf(id));
        TextField answerText = new TextField();
        CheckBox isCorrect = new CheckBox();
        id++;

        TableColumn idColumn = (TableColumn) tableView.getColumns().get(0);
        TableColumn answerColumn = (TableColumn) tableView.getColumns().get(1);
        TableColumn checkBoxColumn = (TableColumn) tableView.getColumns().get(2);

        idColumn.a

    }

}
