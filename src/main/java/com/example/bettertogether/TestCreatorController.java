package com.example.bettertogether;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TestCreatorController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void goToMainMenu(ActionEvent event) throws IOException {
        double height;
        double width;

        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        height = stage.getScene().getHeight();
        width = stage.getScene().getWidth();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    public void goToQuestionCreator(ActionEvent event) throws IOException {
        double height;
        double width;

        root = FXMLLoader.load(getClass().getResource("QuestionCreator.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        height = stage.getScene().getHeight();
        width = stage.getScene().getWidth();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }


}
