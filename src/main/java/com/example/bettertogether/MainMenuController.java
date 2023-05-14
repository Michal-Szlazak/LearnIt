package com.example.bettertogether;

import com.example.bettertogether.JsonMappers.JsonToTestMapper;
import com.example.bettertogether.JsonMappers.TestToJsonMapper;
import com.example.bettertogether.MainMenuGUI.TestLoader;
import com.example.bettertogether.MainMenuGUI.TestRemover;
import com.example.bettertogether.Test.Test;
import com.example.bettertogether.Test.TestStatisticsResetter;
import com.example.bettertogether.TestEditorGUI.TestNameBeforeEditionHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
    @FXML
    private Button clearStatsButton;
    @FXML
    private Label clearStatsLabel;

    public void initialize() {

        TestLoader testLoader = new TestLoader(testListView);
        testLoader.load();

        ButtonAnimation.setButtonAnimation(createNewTestButton);
        ButtonAnimation.setButtonAnimation(downloadNewTestButton);
        ButtonAnimation.setButtonAnimation(doTestButton);
        ButtonAnimation.setButtonAnimation(editTestButton);
        ButtonAnimation.setButtonAnimation(deleteTestButton);
        ButtonAnimation.setButtonAnimation(clearStatsButton);

        test = new Test();
        doTestButton.setOnAction(event -> {
            if(testListView.getSelectionModel().getSelectedItem() == null) {
                return;
            }
            JsonToTestMapper mapper = new JsonToTestMapper();
            test = mapper.createTestFromJson(
                    testListView.getSelectionModel().getSelectedItem() + ".json");
            System.out.println(test);
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
            TestNameBeforeEditionHolder.setTestNameBeforeEdition(testListView.getSelectionModel().getSelectedItem());
            goToTestEditorView(event);
        });

        clearStatsButton.setOnAction(event -> {
            if(testListView.getSelectionModel().getSelectedItem() == null) {
                return;
            }
            JsonToTestMapper mapperToTest = new JsonToTestMapper();
            test = mapperToTest.createTestFromJson(
                    testListView.getSelectionModel().getSelectedItem() + ".json");
            TestStatisticsResetter.reset(test);
            TestToJsonMapper mapperToJson = new TestToJsonMapper();
            mapperToJson.convertToJsonConverter(test);
            Thread thread = new Thread(() -> {
                clearStatsLabel.setVisible(true);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                clearStatsLabel.setVisible(false);
            });
            thread.start();
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