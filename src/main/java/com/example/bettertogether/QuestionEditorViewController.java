package com.example.bettertogether;

import com.example.bettertogether.QuestionCreatorGUI.*;
import com.example.bettertogether.Test.AnswerRow;
import com.example.bettertogether.Test.Question;
import com.example.bettertogether.Test.Test;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class QuestionEditorViewController {

    private Test test;
    private Question question;
    @FXML
    private TextField questionTextField;
    @FXML
    private TableView<AnswerRow> tableView;
    private AnswerTableView answerTableView;
    @FXML
    private Button addAnswerButton;
    @FXML
    private Button deleteAnswerButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveEditedQuestionButton;

    public void initialize() {
        ButtonAnimation.setButtonAnimation(addAnswerButton);
        ButtonAnimation.setButtonAnimation(deleteAnswerButton);
        ButtonAnimation.setButtonAnimation(saveEditedQuestionButton);
        ButtonAnimation.setButtonAnimation(cancelButton);

        questionTextField.setFocusTraversable(false);

        answerTableView = new AnswerTableView(tableView);
        addAnswerButton.setOnAction(event -> answerTableView.addNewAnswer());
        deleteAnswerButton.setOnAction(event -> answerTableView.deleteAnswer());

        saveEditedQuestionButton.setOnAction(event -> {
            AnswersChecker answersChecker = new AnswersChecker();
            boolean questionCorrectness = QuestionChecker.checkQuestion(questionTextField);
            boolean answersCorrectness = answersChecker.checkAnswers(tableView);
            if(questionCorrectness && answersCorrectness) {
                QuestionEditor.refreshAnswers(question, tableView.getItems());
                goToTestCreator(event);
            }
        });
        cancelButton.setOnAction(this::goToTestCreator);

        Platform.runLater(() -> {
            AnswerLoader answerLoader = new AnswerLoader(questionTextField, answerTableView);
            answerLoader.loadQuestion(question);
        });
    }
    public void setTest(Test test) {
        this.test = test;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }
    public void goToTestCreator(ActionEvent event) {
        double width;
        double height;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(FolderPaths.pathToFXMLFolder + "TestCreator.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ((TestCreatorController)loader.getController()).setTestModel(test);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        width = stage.getScene().getWidth();
        height = stage.getScene().getHeight();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }
    public void goToTestEditor(ActionEvent event) {
        double width;
        double height;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FolderPaths.pathToFXMLFolder + "TestEditorView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ((TestEditorViewController)loader.getController()).setTestModel(test);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        width = stage.getScene().getWidth();
        height = stage.getScene().getHeight();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }
    public void loadAnswerEditWindowFromTestEdit(Question question) {
        cancelButton.setOnAction(this::goToTestEditor);
        saveEditedQuestionButton.setOnAction(event -> {
            AnswersChecker answersChecker = new AnswersChecker();
            boolean questionCorrectness = QuestionChecker.checkQuestion(questionTextField);
            boolean answersCorrectness = answersChecker.checkAnswers(tableView);
            if(questionCorrectness && answersCorrectness) {
                QuestionEditor.refreshAnswers(question, tableView.getItems());
                goToTestEditor(event);
            }
        });
    }
}
