package com.example.bettertogether;

import com.example.bettertogether.JsonMappers.TestToJsonMapper;
import com.example.bettertogether.Test.Question;
import com.example.bettertogether.Test.Test;
import com.example.bettertogether.TestMaker.*;
import com.example.bettertogether.TestMakerGUI.ListViewFromVBox;
import com.example.bettertogether.TestMakerGUI.ResultPrinter;
import com.example.bettertogether.TestMakerGUI.SetWrapperTestMakerQuestionLabel;
import com.example.bettertogether.TestMakerGUI.TestMakerVBox;
import com.example.bettertogether.TestSettings.TestSettingsHolder;
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

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class TestMakerTestViewController {

    private Parent root;
    private Stage stage;
    private static int questionNumber = 0;
    private static List<Question> questionList;
    private Test test;
    private TestMakerVBox testMakerVBox;
    private ListViewFromVBox listViewFromVBox;
    private UserTestHistory userTestHistory;
    @FXML
    private VBox questionVBox;
    @FXML
    private VBox answerVBox;
    @FXML
    private Label testLabel;
    @FXML
    private Label questionIdLabel;
    @FXML
    private Label questionLabel;
    @FXML
    private Button submitButton;
    @FXML
    private Button nextQuestionButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label holdCtrlLabel;
    public void initialize() {
        Random rand = new Random();
        questionNumber = 0;
        userTestHistory = new UserTestHistory();

        SetWrapperTestMakerQuestionLabel.setWrapper(questionLabel, questionVBox);
        nextQuestionButton.setDisable(true);
        ButtonAnimation.setButtonAnimation(submitButton);
        ButtonAnimation.setButtonAnimation(cancelButton);
        ButtonAnimation.setButtonAnimation(nextQuestionButton);
        testMakerVBox = new TestMakerVBox(answerVBox);

        cancelButton.setOnAction(this::goToTestSettings);
        Platform.runLater(() -> {
            userTestHistory.setTestName(test.getTestName());
            questionList = QuestionListGetter.getQuestionList(test);
            if(questionList.size() == 1) {
                nextQuestionButton.setText("Finish");
            }
            testLabel.setText(test.getTestName());
            holdCtrlLabel.setVisible(TestSettingsHolder.isItMultipleChoice);
            loadQuestion();
            listViewFromVBox = new ListViewFromVBox(answerVBox, TestSettingsHolder.isItMultipleChoice);
        });
        submitButton.setOnAction(event -> {
            addUsersChoicesToUserHistory();
            submitButton.setDisable(true);
            nextQuestionButton.setDisable(false);
            ResultPrinter.showResult(listViewFromVBox, questionList.get(0));
            if(UserAnswerChecker.checkResult(listViewFromVBox, questionList.get(0))) {
                CorrectAnsweredQuestionHandler.handle(questionList.get(0));
            } else {
                WronglyAnsweredQuestionHandler.handle(questionList.get(0));
                if(TestSettingsHolder.repeatWronglyAnsweredQuestions) {
                    questionList.add(rand.nextInt(questionList.size()) + 1, questionList.get(0));
                }
            }
            questionList.remove(0);
        });
        nextQuestionButton.setOnAction(event ->{
            if(questionList.size() == 1) {
                nextQuestionButton.setText("Finish");
            }
            if(questionList.size() == 0) {
                updateTestStatistics();
                goToResultView(event);
            } else {
                submitButton.setDisable(false);
                nextQuestionButton.setDisable(true);
                questionNumber++;
                loadQuestion();
                listViewFromVBox = new ListViewFromVBox(answerVBox, TestSettingsHolder.isItMultipleChoice);
            }
        });
    }
    public void setTest(Test test) {
        this.test = test;
    }
    public void loadQuestion() {
        Question currentQuestion = questionList.get(0);
        questionIdLabel.setText("Question " + (questionNumber + 1));
        questionLabel.setText(currentQuestion.getQuestion());
        testMakerVBox.loadAnswers(currentQuestion.getAnswers());
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

    public void goToResultView(ActionEvent event) {
        double width;
        double height;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                FolderPaths.pathToFXMLFolder + "TestMakerResultsView.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ((TestMakerResultsViewController)loader.getController()).setUserTestHistory(userTestHistory);
        ((TestMakerResultsViewController)loader.getController()).setTest(test);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        width = stage.getScene().getWidth();
        height = stage.getScene().getHeight();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    private void addUsersChoicesToUserHistory() {
        QuestionHistory questionHistory = new QuestionHistory();
        questionHistory.setQuestion(questionList.get(0));
        questionHistory.setAnswersIdFromHBox(listViewFromVBox.getSelectedItems());
        userTestHistory.addQuestionHistory(questionHistory);
    }

    private void updateTestStatistics() {
        TestToJsonMapper mapper = new TestToJsonMapper();
        File oldFile = new File(FolderPaths.getJarDirPath() +
                FolderPaths.pathToTestFolder + test.getTestName() + ".json");
        oldFile.delete();
        mapper.convertToJsonConverter(test);
    }
}