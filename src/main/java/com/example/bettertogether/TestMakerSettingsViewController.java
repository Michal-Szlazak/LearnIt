package com.example.bettertogether;

import com.example.bettertogether.Test.Test;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class TestMakerSettingsViewController {

    private Test test;
    @FXML
    private Button cancelButton;
    @FXML
    private Button takeQuickTestButton;
    @FXML
    private Button takeFullTestButton;
    @FXML
    private TitledPane descriptionTitledPane;
    @FXML
    private Text quickTestDescriptionText;
    @FXML
    private Text fullTestDescriptionText;
    @FXML
    private Button quickTestInformationButton;
    @FXML
    private Button fullTestInformationButton;

    public void initialize() {
        quickTestDescriptionText.setVisible(false);
        quickTestDescriptionText.setManaged(false);
        fullTestDescriptionText.setVisible(false);
        fullTestDescriptionText.setManaged(false);
        quickTestInformationButton.setOnAction(event -> {
            quickTestDescriptionText.setVisible(!quickTestDescriptionText.isVisible());
            quickTestDescriptionText.setManaged(!quickTestDescriptionText.isManaged());
        });
        fullTestInformationButton.setOnAction(event -> {
            fullTestDescriptionText.setVisible(!fullTestDescriptionText.isVisible());
            fullTestDescriptionText.setManaged(!fullTestDescriptionText.isManaged());
        });
        takeQuickTestButton.setOnAction(this::goToTestMakerTestView);
        takeFullTestButton.setOnAction(this::goToTestMakerTestView);

         cancelButton.setOnAction(this::goToMainMenu);
    }

    public void setTest(Test test) {
        this.test = test;
    }
    public void goToMainMenu(ActionEvent event) {
        double width;
        double height;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                FolderPaths.pathToFXMLFolder + "MainMenu.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        width = stage.getScene().getWidth();
        height = stage.getScene().getHeight();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    public void goToTestMakerTestView(ActionEvent event) {
        double width;
        double height;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                FolderPaths.pathToFXMLFolder + "TestMakerTestView.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ((TestMakerTestViewController)loader.getController()).setTest(test);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        width = stage.getScene().getWidth();
        height = stage.getScene().getHeight();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

}
