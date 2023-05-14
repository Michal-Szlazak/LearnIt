package com.example.bettertogether;

import com.example.bettertogether.Test.Test;
import com.example.bettertogether.TestSettings.FullTestCompletionChecker;
import com.example.bettertogether.TestSettings.QuestionCounter;
import com.example.bettertogether.TestSettings.TestMode;
import com.example.bettertogether.TestSettings.TestSettingsHolder;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;

public class TestMakerSettingsViewController {

    private Test test;
    @FXML
    private Button cancelButton;
    @FXML
    private Button takeQuickTestButton;
    @FXML
    private Button takeFullTestButton;
    @FXML
    private Text quickTestDescriptionText;
    @FXML
    private Text fullTestDescriptionText;
    @FXML
    private Button quickTestInformationButton;
    @FXML
    private Button fullTestInformationButton;
    @FXML
    private CheckBox shuffleQuestionsCheckBox;
    @FXML
    private CheckBox repeatWronglyAnsweredQuestions;
    @FXML
    private Label takeFullTestInfo;
    @FXML
    private TextFlow takeFullTestInfoTextFlow;
    @FXML
    private Label numberOfQuickTestQuestions;
    @FXML
    private Label numberOfFullTestQuestions;

    public void initialize() {
        TestSettingsHolder.resetTestSettings();
        ButtonAnimation.setButtonAnimation(cancelButton);
        ButtonAnimation.setButtonAnimation(takeQuickTestButton);
        ButtonAnimation.setButtonAnimation(takeFullTestButton);
        ButtonAnimation.setButtonAnimation(quickTestInformationButton);
        ButtonAnimation.setButtonAnimation(fullTestInformationButton);
        quickTestDescriptionText.setVisible(false);
        quickTestDescriptionText.setManaged(false);
        fullTestDescriptionText.setVisible(false);
        fullTestDescriptionText.setManaged(false);
        quickTestInformationButton.setOnAction(event -> {
            quickTestDescriptionText.setVisible(!quickTestDescriptionText.isVisible());
            quickTestDescriptionText.setManaged(!quickTestDescriptionText.isManaged());
        });
        fullTestInformationButton.setOnAction(event -> {
            fullTestDescriptionText.setVisible(!fullTestDescriptionText.isVisible());
            fullTestDescriptionText.setManaged(!fullTestDescriptionText.isManaged());
        });
        takeQuickTestButton.setOnAction(event -> {
            TestSettingsHolder.testMode = TestMode.QUICK;
            TestSettingsHolder.repeatWronglyAnsweredQuestions = repeatWronglyAnsweredQuestions.isSelected();
            TestSettingsHolder.shuffleQuestions = shuffleQuestionsCheckBox.isSelected();
            goToTestMakerTestView(event);
        });
        takeFullTestButton.setOnAction(event -> {
            TestSettingsHolder.testMode = TestMode.FULL;
            TestSettingsHolder.repeatWronglyAnsweredQuestions = repeatWronglyAnsweredQuestions.isSelected();
            TestSettingsHolder.shuffleQuestions = shuffleQuestionsCheckBox.isSelected();
            goToTestMakerTestView(event);
        });
        cancelButton.setOnAction(event -> {
            TestSettingsHolder.resetTestSettings();
            goToMainMenu(event);
        });
        Platform.runLater(() -> {
            if(!FullTestCompletionChecker.checkIfFullTestWasCompleted(test) ||
                    QuestionCounter.getNumberOfQuickTestQuestions(test) == 0) {
                takeQuickTestButton.setDisable(true);
                takeFullTestInfo.setVisible(true);
                takeFullTestInfo.setManaged(true);
                takeFullTestInfoTextFlow.setVisible(true);
                takeFullTestInfoTextFlow.setManaged(true);
                if(QuestionCounter.getNumberOfQuickTestQuestions(test) == 0) {
                    takeFullTestInfo.setText("Your score is too good! We couldn't find any questions for you. " +
                            "If you want, you can reset your statistics in the Main Menu.");
                }
                if(!FullTestCompletionChecker.checkIfFullTestWasCompleted(test)) {
                    takeFullTestInfo.setText("You have to take a full test at least once first!");
                }
            }
            numberOfFullTestQuestions.setText(String.format("Questions: %d.",
                    QuestionCounter.getNumberOfFullTestQuestions(test)));
            numberOfQuickTestQuestions.setText(String.format("Questions: %d.",
                    QuestionCounter.getNumberOfQuickTestQuestions(test)));
        });
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

    public void goToTestMakerTestView(ActionEvent event) {
        double width;
        double height;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                FolderPaths.pathToFXMLFolder + "TestMakerTestView.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ((TestMakerTestViewController)loader.getController()).setTest(test);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        width = stage.getScene().getWidth();
        height = stage.getScene().getHeight();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

}
