package com.example.bettertogether;

import com.example.bettertogether.DB.DB;
import com.example.bettertogether.MoreInfoView.VBoxInfoViewGenerator;
import com.example.bettertogether.ResultsView.GenerateResultsVBox;
import com.example.bettertogether.Test.Test;
import com.example.bettertogether.UploadTest.TestToUpload;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class BrowserMoreInfoViewController {

    private TestToUpload upload;
    private Test test;
    private String testName;
    private String username;
    private VBoxInfoViewGenerator generator;

    @FXML
    private Label testNameLabel;
    @FXML
    private Button goToBrowserButton;
    @FXML
    private VBox questionsVBox;
    @FXML
    private TextArea descriptionTextArea;

    public void initialize() {

        ButtonAnimation.setButtonAnimation(goToBrowserButton);

        goToBrowserButton.setOnAction(this::goToBrowserView);

        Platform.runLater(() -> {
            testNameLabel.setText("\"" + testName + "\"" + " by " + username);
            upload = DB.getJsonTestFromDB(username, testName);
            test = upload.getTest();
            generator = new VBoxInfoViewGenerator(questionsVBox, test);
            generator.generateQuestionsVBox();
            descriptionTextArea.setText(upload.getDescription());
        });
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private void goToBrowserView(ActionEvent event) {
        double width;
        double height;
        Parent root;
        Stage stage;
        Scene scene;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                FolderPaths.pathToFXMLFolder + "BrowserView.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        width = stage.getScene().getWidth();
        height = stage.getScene().getHeight();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }
}
