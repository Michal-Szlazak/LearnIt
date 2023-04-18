package com.example.bettertogether;

import com.example.bettertogether.Test.Answer;
import com.example.bettertogether.Test.Question;
import com.example.bettertogether.Test.Test;
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
    @FXML
    private ListView<HBox> answerListView;
    @FXML
    private Label questionLabel;
    @FXML
    private Button cancelButton;
    public void initialize() {
        cancelButton.setOnAction(this::goToTestSettings);

        Platform.runLater(() -> loadQuestion(answerListView));
    }
    public void setTest(Test test) {
        this.test = test;
    }
    public void loadQuestion(ListView<HBox> listView) {

        Question currentQuestion = test.getQuestions().get(questionNumber++);
        questionLabel.setText(currentQuestion.getQuestion());

        for(Answer answer : currentQuestion.getAnswers()) {
            HBox answerHBox = new HBox();
            answerHBox.setAlignment(Pos.CENTER_LEFT);
            answerHBox.getStylesheets().add(
                    String.valueOf(getClass().getResource(FolderPaths.pathToCssFiles + "TestCreatorStyle/TestCreatorQuestionListStyle.css"))
            );
            Label answerId = new Label(answer.getId());
            answerId.setId("answerIdLabel");
            Label answerText = new Label(answer.getAnswer());
            answerText.setId("answerTextLabel");
            answerText.setWrapText(true);
            answerHBox.getChildren().add(0, answerId);
            answerHBox.getChildren().add(1, answerText);
            listView.getItems().add(answerHBox);
        }

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
