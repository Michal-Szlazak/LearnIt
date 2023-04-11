package com.example.bettertogether;

import com.example.bettertogether.MainMenuGUI.TestLoader;
import com.example.bettertogether.Test.Test;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public void initialize() {
        new TestLoader(testListView);
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