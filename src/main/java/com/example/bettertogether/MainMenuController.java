package com.example.bettertogether;

import com.example.bettertogether.JsonMappers.JsonToTestMapper;
import com.example.bettertogether.MainMenuGUI.TestLoader;
import com.example.bettertogether.MainMenuGUI.TestRemover;
import com.example.bettertogether.Test.Test;
import javafx.event.ActionEvent;
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
    private Button createNewTestButton;
    @FXML
    private Button downloadNewTestButton;
    @FXML
    private ListView<String> testListView;
    @FXML
    private Button doTestButton;
    @FXML
    private Button deleteTestButton;
    @FXML
    private Button editTestButton;

    public void initialize() {

        TestLoader testLoader = new TestLoader(testListView);
        testLoader.load();

        ButtonAnimation.setButtonAnimation(createNewTestButton);
        ButtonAnimation.setButtonAnimation(downloadNewTestButton);
        ButtonAnimation.setButtonAnimation(doTestButton);
        ButtonAnimation.setButtonAnimation(editTestButton);
        ButtonAnimation.setButtonAnimation(deleteTestButton);

        test = new Test();
        doTestButton.setOnAction(event -> {
            if(testListView.getSelectionModel().getSelectedItem() == null) {
                return;
            }
            JsonToTestMapper mapper = new JsonToTestMapper();
            test = mapper.createTestFromJson(
                    testListView.getSelectionModel().getSelectedItem() + ".json");
            goToTestMakerSettings(event);
        });

        deleteTestButton.setOnAction(event -> {
            if(testListView.getSelectionModel().getSelectedItem() == null) {
                return;
            }
            String testName = testListView.getSelectionModel().getSelectedItem();
            if(TestRemover.removeTest(testName + ".json")) {
                testLoader.load();
            }
        });

        editTestButton.setOnAction(event -> {
            if(testListView.getSelectionModel().getSelectedItem() == null) {
                return;
            }
            JsonToTestMapper mapper = new JsonToTestMapper();
            test = mapper.createTestFromJson(
                    testListView.getSelectionModel().getSelectedItem() + ".json");
            goToTestEditorView(event);
        });
    }

    public void goToTestMakerSettings(ActionEvent event) {
        double width;
        double height;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                FolderPaths.pathToFXMLFolder + "TestMakerSettingsView.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ((TestMakerSettingsViewController)loader.getController()).setTest(test);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        width = stage.getScene().getWidth();
        height = stage.getScene().getHeight();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    public void goToTestCreator(ActionEvent event) {
        double width;
        double height;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(FolderPaths.pathToFXMLFolder + "TestCreator.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ((TestCreatorController)loader.getController()).setTestModel(test);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        width = stage.getScene().getWidth();
        height = stage.getScene().getHeight();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    public void goToTestEditorView(ActionEvent event) {
        double width;
        double height;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(FolderPaths.pathToFXMLFolder + "TestEditorView.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ((TestEditorViewController)loader.getController()).setTestModel(test);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        width = stage.getScene().getWidth();
        height = stage.getScene().getHeight();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }
}