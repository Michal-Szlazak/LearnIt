package com.example.bettertogether;

import com.example.bettertogether.JsonMappers.TestToJsonMapper;
import com.example.bettertogether.Test.Question;
import com.example.bettertogether.Test.Test;
import com.example.bettertogether.TestCreatorGUI.TestListView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class TestEditorViewController {

    private Stage stage;
    private Scene scene;
    @FXML
    private ListView<Question> questionListView;
    @FXML
    private TextField testNameTextField;
    @FXML
    private Button addQuestionButton;
    @FXML
    private Button deleteQuestionButton;
    @FXML
    private Button editQuestionButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveChanges;
    private Test test;

    public void initialize() {
        TestListView testListView = new TestListView(questionListView);
        cancelButton.setOnAction(this::goToMainMenu);
        saveChanges.setOnAction(event -> {
            File oldFile = new File(FolderPaths.pathToTestFolder + test.getTestName() + ".json");
            oldFile.delete();
            TestToJsonMapper jsonConverter = new TestToJsonMapper();
            jsonConverter.convertToJsonConverter(test);
            goToMainMenu(event);
        });
        testNameTextField.setFocusTraversable(false);
        deleteQuestionButton.setOnAction(event -> {
            testListView.deleteQuestion();
            test.getQuestions().remove(questionListView.getSelectionModel().getSelectedItem());
        });
        editQuestionButton.setOnAction(this::goToQuestionEditorView);
        addQuestionButton.setOnAction(this::goToQuestionCreator);

        Platform.runLater(() -> {
            testListView.loadQuestions(test.getQuestions());
            testNameTextField.setText(test.getTestName());
        });
    }
    public void setTestModel(Test test) {
        this.test = test;
    }
    public void goToMainMenu(ActionEvent event) {
        uploadTestName();
        double height;
        double width;

        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FolderPaths.pathToFXMLFolder + "MainMenu.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        height = stage.getScene().getHeight();
        width = stage.getScene().getWidth();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }
    public void goToQuestionCreator(ActionEvent event) {
        double height;
        double width;

        uploadTestName();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(FolderPaths.pathToFXMLFolder + "QuestionCreator.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ((QuestionCreatorController)loader.getController()).setTestModel(test);
        ((QuestionCreatorController)loader.getController()).loadQuestionCreatorWindowFromTestEdit();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        height = stage.getScene().getHeight();
        width = stage.getScene().getWidth();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }
    public void goToQuestionEditorView(ActionEvent event) {
        double height;
        double width;

        uploadTestName();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(FolderPaths.pathToFXMLFolder + "QuestionEditorView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ((QuestionEditorViewController)loader.getController()).setTest(test);
        ((QuestionEditorViewController)loader.getController()).setQuestion(
                questionListView.getSelectionModel().getSelectedItem());
        ((QuestionEditorViewController)loader.getController()).loadAnswerEditWindowFromTestEdit(
                questionListView.getSelectionModel().getSelectedItem());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        height = stage.getScene().getHeight();
        width = stage.getScene().getWidth();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }
    private void uploadTestName() {
        test.setTestName(testNameTextField.getText());
    }

}
