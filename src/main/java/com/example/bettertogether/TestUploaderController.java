package com.example.bettertogether;

import com.example.bettertogether.DB.DB;
import com.example.bettertogether.JsonMappers.JsonToTestMapper;
import com.example.bettertogether.MainMenuGUI.TestLoader;
import com.example.bettertogether.Test.Test;
import com.example.bettertogether.Test.TestStatisticsResetter;
import com.example.bettertogether.UploadTest.DBUploadResult;
import com.example.bettertogether.UploadTest.TestToUpload;
import com.example.bettertogether.UploadTestGUI.HandleUserInput;
import com.example.bettertogether.UploadTestGUI.LoadingLabel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestUploaderController {

    @FXML
    private ListView<String> testListView;
    @FXML
    private FlowPane keywordFlowPane;
    @FXML
    private TextField keywordTextField;
    @FXML
    private Button addKeywordButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button uploadButton;
    @FXML
    private Label warningKeywordLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private Label loadingLabel;

    public void initialize() {
        TestLoader testLoader = new TestLoader(testListView);
        testLoader.load();

        setAddKeywordButton();
        ButtonAnimation.setButtonAnimation(addKeywordButton);
        ButtonAnimation.setButtonAnimation(cancelButton);
        ButtonAnimation.setButtonAnimation(uploadButton);

        cancelButton.setOnAction(this::goToMainMenu);
        uploadButton.setOnAction(event -> {
            if(!checkUserInput()) {
                return;
            }
            waitForDBToResponse();
            DBUploadResult result = DB.sendTestAsJson(getTestToUpload());
            if(result == DBUploadResult.SUCCESSFUL_CONNECTION) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Upload succeeded");
                alert.show();
                goToMainMenu(event);
            } else if(result == DBUploadResult.TEST_ALREADY_EXISTS) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Test already exists");
                alert.setHeaderText("Test with this user name and test name already exists.");
                alert.setContentText("If you want to upload this test change your username or test's name.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Upload failed");
                alert.setHeaderText("Connectivity problems");
                alert.setContentText("We are really sorry. We encountered problems while submitting the test." +
                        " Try resetting application and check your Wi-Fi connection.");
                alert.setHeight(300);
                alert.show();
            }
            stopLoading();
        });
    }

    private void goToMainMenu(ActionEvent event) {
        double width;
        double height;
        Parent root;
        Stage stage;
        Scene scene;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                FolderPaths.pathToFXMLFolder + "MainMenu.fxml"));
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

    private void setAddKeywordButton() {
        addKeywordButton.setDisable(true);
        keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            addKeywordButton.setDisable(newValue.isEmpty());
        });

        addKeywordButton.setOnAction(event -> {
            HBox hBox = new HBox();
            Label keyword = new Label(keywordTextField.getText());
            Button button = new Button();
            ButtonAnimation.setButtonAnimation(button);
            hBox.getChildren().addAll(keyword, button);
            button.setOnAction(event1 -> {
                keywordFlowPane.getChildren().remove(button.getParent());
                System.out.println(button.getParent());
            });

            for(Node node : keywordFlowPane.getChildren()) {
                HBox hbox = (HBox) node;
                Label label = (Label) hbox.getChildren().get(0);
                if(keywordTextField.getText().equals(label.getText())) {
                    Thread thread = new Thread(() -> {
                        warningKeywordLabel.setVisible(true);
                        warningKeywordLabel.setManaged(true);
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        warningKeywordLabel.setVisible(false);
                        warningKeywordLabel.setManaged(false);
                    });
                    thread.start();
                    return;
                }
            }
            keywordFlowPane.getChildren().add(hBox);
            keywordTextField.setText("");
        });
    }

    private boolean checkUserInput() {
        boolean checkName = HandleUserInput.handleNameTextField(nameTextField);
        boolean checkTestList = HandleUserInput.handleTestListView(testListView);
        boolean checkKeywords = HandleUserInput.handleKeywordsFlowPane(keywordFlowPane);
        return checkKeywords && checkName && checkTestList;
    }

    private TestToUpload getTestToUpload() {

        TestToUpload toUpload = new TestToUpload();
        toUpload.setUserName(nameTextField.getText());

        JsonToTestMapper mapper = new JsonToTestMapper();
        Test test = mapper.createTestFromJsonFileName(testListView.getSelectionModel().getSelectedItem() + ".json");
        TestStatisticsResetter.reset(test);
        toUpload.setTest(test);

        List<String> keywords = new ArrayList<>();
        for(Node node : keywordFlowPane.getChildren()) {
            HBox hBox = (HBox) node;
            Label label = (Label) hBox.getChildren().get(0);
            keywords.add(label.getText());
        }
        toUpload.setKeywords(keywords);
        toUpload.setDescription(descriptionTextArea.getText());

        toUpload.setId(toUpload.getUserName() + toUpload.getTest().getTestName());
        return toUpload;
    }

    private void waitForDBToResponse() {
        LoadingLabel.load(loadingLabel);
        cancelButton.setDisable(true);
        uploadButton.setDisable(true);
    }

    private void stopLoading() {
        cancelButton.setDisable(false);
        uploadButton.setDisable(false);
        LoadingLabel.stopLoading(loadingLabel);
    }
}
