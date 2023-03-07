package com.example.bettertogether;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class MainMenuController {

    @FXML
    private Pane optionsGridPane;
    @FXML
    private ListView<Label> testListView;

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void initialize() {
        Label label = new Label();
        label.getStylesheets().add("com/example/bettertogether/css/MainMenuStyle/MainMenuListStyle.css");
        label.textProperty().set("firstTest");
        testListView.getItems().add(label);
        testListView.getItems().add(new Label("Second test"));
        testListView.getItems().add(new Label("Third test"));
        testListView.getItems().add(new Label("Fourth test"));
    }

    public void goToTestCreator(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TestCreator.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 700, 500);
        stage.setScene(scene);
        stage.show();
    }


}