package com.example.bettertogether;

import com.example.bettertogether.Test.Question;
import com.example.bettertogether.Test.Test;
import com.example.bettertogether.TestMakerGUI.TestMakerListView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class TestMakerTestViewController {

    private Parent root;
    private Stage stage;
    private static int questionNumber = 0;
    private Test test;
    private TestMakerListView testMakerListView;
    @FXML
    private ListView<HBox> answerListView;
    @FXML
    private Label testLabel;
    @FXML
    private Label questionIdLabel;
    @FXML
    private Label questionLabel;
    @FXML
    private Button submitButton;
    @FXML
    private Button cancelButton;
    public void initialize() {
        ButtonAnimation.setButtonAnimation(submitButton);
        ButtonAnimation.setButtonAnimation(cancelButton);

        questionNumber = 0;
        testMakerListView = new TestMakerListView(answerListView);
        cancelButton.setOnAction(this::goToTestSettings);
        Platform.runLater(() -> {
            testLabel.setText(test.getTestName());
            loadQuestion();
        });
        submitButton.setOnAction(event -> {
            Popup popup = new Popup();

            Label label = new Label("popup");
            label.setMinWidth(300);
            label.setMinHeight(200);
            label.setStyle("-fx-background-color: white;");
            popup.getContent().add(label);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            System.out.println("popup");
            popup.show(stage);
        });
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
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ((TestMakerSettingsViewController)loader.getController()).setTest(test);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        width = stage.getScene().getWidth();
        height = stage.getScene().getHeight();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }
}