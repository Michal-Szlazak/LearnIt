package com.example.bettertogether;

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
import java.io.IOException;

public class TestCreatorController {

    private Stage stage;
    private Scene scene;
    @FXML
    private ListView<Question> questionListView;
    @FXML
    private TextField testNameTextField;
    @FXML
    private Button deleteQuestionButton;
    @FXML
    private Button editQuestionButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button submitButton;
    private Test test;

    public void initialize() {
        TestListView testListView = new TestListView(questionListView);
        cancelButton.setOnAction(this::goToMainMenu);
        submitButton.setOnAction(event -> {
            uploadTestName();
            TestToJsonMapper jsonConverter = new TestToJsonMapper();
            jsonConverter.convertToJsonConverter(test);
            goToMainMenu(event);
        });
        testNameTextField.setFocusTraversable(false);
        deleteQuestionButton.setOnAction(event -> testListView.deleteQuestion());
        editQuestionButton.setOnAction(event -> {
            try {
                goToQuestionCreatorToEditQuestion(event);
            } catch (IOException e) {
                throw new UnsupportedOperationException("Unsupported exception when going to question creator.");
            }
        });

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
    public void goToQuestionCreator(ActionEvent event) throws IOException {
        double height;
        double width;

        uploadTestName();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(FolderPaths.pathToFXMLFolder + "QuestionCreator.fxml"));
        Parent root = loader.load();
        ((QuestionCreatorController)loader.getController()).setTestModel(test);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        height = stage.getScene().getHeight();
        width = stage.getScene().getWidth();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }
    public void goToQuestionCreatorToEditQuestion(ActionEvent event) throws IOException {
        double height;
        double width;

        uploadTestName();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(FolderPaths.pathToFXMLFolder + "QuestionCreator.fxml"));
        Parent root = loader.load();
        ((QuestionCreatorController)loader.getController()).setTestModel(test);
        ((QuestionCreatorController)loader.getController())
                .loadAnswerEditWindow(questionListView.getSelectionModel().getSelectedItem());
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
