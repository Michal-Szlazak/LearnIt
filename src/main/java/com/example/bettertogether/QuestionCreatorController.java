package com.example.bettertogether;

import com.example.bettertogether.QuestionCreatorGUI.*;
import com.example.bettertogether.Test.AnswerRow;
import com.example.bettertogether.Test.Question;
import com.example.bettertogether.Test.Test;
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

    private Test test;
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
    private Button addNewQuestionToTestButton;
    @FXML
    private Button cancelButton;

    public void initialize() {
        ButtonAnimation.setButtonAnimation(addAnswerButton);
        ButtonAnimation.setButtonAnimation(deleteAnswerButton);
        ButtonAnimation.setButtonAnimation(addNewQuestionToTestButton);
        ButtonAnimation.setButtonAnimation(cancelButton);

        questionTextField.setFocusTraversable(false);

        answerTableView = new AnswerTableView(tableView);
        addAnswerButton.setOnAction(event -> answerTableView.addNewAnswer());
        deleteAnswerButton.setOnAction(event -> answerTableView.deleteAnswer());

        AnswersGetter answersGetter = new AnswersGetter(tableView);
        QuestionCreator questionCreator = new QuestionCreator(questionTextField);
        addNewQuestionToTestButton.setOnAction(event -> {
            AnswersChecker answersChecker = new AnswersChecker();
            boolean questionCorrectness = QuestionChecker.checkQuestion(questionTextField);
            boolean answersCorrectness = answersChecker.checkAnswers(tableView);
            if(questionCorrectness && answersCorrectness) {
                Question question = questionCreator.createQuestion(answersGetter.getAnswers());
                test.addNewQuestion(question);
                goToTestCreator(event);
            }
        });
        cancelButton.setOnAction(this::goToTestCreator);

    }
    public void setTestModel(Test test) {
        this.test = test;
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

    public void goToTestEditorView(ActionEvent event) {
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

    public void loadQuestionCreatorWindowFromTestEdit() {
        cancelButton.setOnAction(this::goToTestEditorView);
        QuestionCreator questionCreator = new QuestionCreator(questionTextField);
        AnswersGetter answersGetter = new AnswersGetter(tableView);
        addNewQuestionToTestButton.setOnAction(event -> {
            AnswersChecker answersChecker = new AnswersChecker();
            boolean questionCorrectness = QuestionChecker.checkQuestion(questionTextField);
            boolean answersCorrectness = answersChecker.checkAnswers(tableView);
            if(questionCorrectness && answersCorrectness) {
                Question question = questionCreator.createQuestion(answersGetter.getAnswers());
                test.addNewQuestion(question);
                goToTestEditorView(event);
            }
        });
    }
}
