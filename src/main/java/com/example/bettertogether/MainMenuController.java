package com.example.bettertogether;

import com.example.bettertogether.MainMenuGUI.TestLoader;
import com.example.bettertogether.MainMenuGUI.TestRemover;
import com.example.bettertogether.Test.Test;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;


public class MainMenuController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Test test;
    @FXML
    private ListView<String> testListView;
    @FXML
    private Button deleteTestButton;

    public void initialize() {

        TestLoader testLoader = new TestLoader(testListView);
        testLoader.load();

        TestRemover testRemover = new TestRemover();
        deleteTestButton.setOnAction(event -> {
            String testName = testListView.getSelectionModel().getSelectedItem();
            if(testRemover.removeTest(testName + ".json")) {
                testLoader.load();
            }
        });
    }

    public void goToTestCreator(ActionEvent event) throws IOException {
        double width;
        double height;
        test = new Test();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(FolderPaths.pathToFXMLFolder + "TestCreator.fxml"));
        root = loader.load();
        ((TestCreatorController)loader.getController()).setTestModel(test);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        width = stage.getScene().getWidth();
        height = stage.getScene().getHeight();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

}