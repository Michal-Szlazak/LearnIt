package com.example.bettertogether;

import com.example.bettertogether.ResultsView.GenerateResultsVBox;
import com.example.bettertogether.ResultsView.ScoreGetter;
import com.example.bettertogether.Test.Test;
import com.example.bettertogether.TestMaker.UserTestHistory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TestMakerResultsViewController {

    private Test test;
    private UserTestHistory userTestHistory;
    private GenerateResultsVBox generateResultsVBox;
    @FXML
    private Label testNameLabel;
    @FXML
    private VBox questionsVBox;
    @FXML
    private Label scoreLabel;
    @FXML
    private Button goToMainMenuButton;
    @FXML
    private Button retakeButton;
    public void initialize() {
        ButtonAnimation.setButtonAnimation(goToMainMenuButton);
        ButtonAnimation.setButtonAnimation(retakeButton);
        goToMainMenuButton.setOnAction(this::goToMainMenu);
        retakeButton.setOnAction(this::goToTestMakerSettings);
        Platform.runLater(() -> {
            testNameLabel.setText(userTestHistory.getTestName());
            generateResultsVBox = new GenerateResultsVBox(questionsVBox, userTestHistory);
            generateResultsVBox.generateQuestionsVBox();
            scoreLabel.setText(String.format("Your score: %.1f%%",
                    ScoreGetter.getScore(questionsVBox, userTestHistory)));
        });
    }
    public void setUserTestHistory(UserTestHistory userTestHistory) {
        this.userTestHistory = userTestHistory;
    }
    public void setTest(Test test) {
        this.test = test;
    }
    public void goToMainMenu(ActionEvent event) {
        double width;
        double height;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                FolderPaths.pathToFXMLFolder + "MainMenu.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        width = stage.getScene().getWidth();
        height = stage.getScene().getHeight();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    public void goToTestMakerSettings(ActionEvent event) {
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
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        width = stage.getScene().getWidth();
        height = stage.getScene().getHeight();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }
}
