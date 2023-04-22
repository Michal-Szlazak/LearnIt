package com.example.bettertogether;

import com.example.bettertogether.Test.Answer;
import com.example.bettertogether.Test.Question;
import com.example.bettertogether.Test.Test;
import com.example.bettertogether.TestMakerGUI.TestMakerListView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TestMakerTestViewController {

    private static int questionNumber = 0;
    private Test test;
    private TestMakerListView testMakerListView;
    @FXML
    private ListView<HBox> answerListView;
    @FXML
    private Label questionIdLabel;
    @FXML
    private Label questionLabel;
    @FXML
    private Button cancelButton;
    public void initialize() {
        questionNumber = 0;
        testMakerListView = new TestMakerListView(answerListView);
        cancelButton.setOnAction(this::goToTestSettings);
        Platform.runLater(this::loadQuestion);
    }
    public void setTest(Test test) {
        this.test = test;
    }
    public void loadQuestion() {
        Question currentQuestion = test.getQuestions().get(questionNumber++);
        questionIdLabel.setText("Question " + questionNumber);
        questionLabel.setText(currentQuestion.getQuestion());
        testMakerListView.loadAnswers(currentQuestion.getAnswers());
    }
    public void goToTestSettings(ActionEvent event) {
        double width;
        double height;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                FolderPaths.pathToFXMLFolder + "TestMakerSettingsView.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ((TestMakerSettingsViewController)loader.getController()).setTest(test);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        width = stage.getScene().getWidth();
        height = stage.getScene().getHeight();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }
}
