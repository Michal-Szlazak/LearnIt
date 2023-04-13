package com.example.bettertogether;

import com.example.bettertogether.QuestionCreatorGUI.*;
import com.example.bettertogether.Test.Answer;
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
    private TableView<Answer> tableView;
    private AnswerTableView answerTableView;
    @FXML
    private Button addAnswerButton;
    @FXML
    private Button deleteAnswerButton;
    @FXML
    private Button addNewQuestionButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button editQuestionButton;

    public void initialize() {
        new QuestionTextField(questionTextField);
        questionTextField.setFocusTraversable(false);

        answerTableView = new AnswerTableView(tableView);
        addAnswerButton.setOnAction(event -> answerTableView.addNewAnswer());
        deleteAnswerButton.setOnAction(event -> answerTableView.deleteAnswer());

        AnswersGetter answersGetter = new AnswersGetter(tableView);
        QuestionNameLoader questionNameLoader = new QuestionNameLoader(questionTextField);
        addNewQuestionButton.setOnAction(event -> {
            Question question = questionNameLoader.createQuestion(answersGetter.loadAnswers());
            test.addNewQuestion(question);
            try {
                goToTestCreator(event);
            } catch (IOException e) {
                throw new UnsupportedOperationException("Failed to load TestCreator - not implemented yet");
            }
        });
        cancelButton.setOnAction(event -> {
            try {
                goToTestCreator(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
    public void setTestModel(Test test) {
        this.test = test;
    }
    public void goToTestCreator(ActionEvent event) throws IOException {
        double width;
        double height;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(FolderPaths.pathToFXMLFolder + "TestCreator.fxml"));
        Parent root = loader.load();
        ((TestCreatorController)loader.getController()).setTestModel(test);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        width = stage.getScene().getWidth();
        height = stage.getScene().getHeight();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    public void loadAnswerEditWindow(Question question) {
        addNewQuestionButton.setManaged(false);
        editQuestionButton.setManaged(true);
        AnswerLoader answerLoader = new AnswerLoader(questionTextField, tableView);
        answerLoader.loadQuestion(question);

        editQuestionButton.setOnAction(event -> {
            try {
                goToTestCreator(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


}
