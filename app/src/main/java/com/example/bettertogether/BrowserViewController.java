package com.example.bettertogether;

import com.example.bettertogether.BrowserTestGUI.PageController;
import com.example.bettertogether.DB.DB;
import com.example.bettertogether.JsonMappers.TestToJsonMapper;
import com.example.bettertogether.Test.Test;
import javafx.application.Platform;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BrowserViewController {

    private int pageNumber = 1;
    private List<String> testList;

    @FXML
    private TextField userNameFilterTextField;
    @FXML
    private TextField testNameFilterTextField;
    @FXML
    private TextField keywordTextField;
    @FXML
    private FlowPane keywordsFlowPane;
    @FXML
    private Button applyFiltersButton;
    @FXML
    private Button addKeywordButton;
    @FXML
    private Button viewMoreTestInfoButton;
    @FXML
    private Button downloadTestButton;
    @FXML
    private Button returnButton;
    @FXML
    private ListView<HBox> testsListView;
    @FXML
    private Label warningKeywordLabel;
    @FXML
    private Button goToPreviousPageButton;
    @FXML
    private Button goToNextPageButton;
    @FXML
    private Label previousPageLabel;
    @FXML
    private Label currentPageLabel;
    @FXML
    private Label nextPageLabel;

    public void initialize() {
        ButtonAnimation.setButtonAnimation(applyFiltersButton);
        ButtonAnimation.setButtonAnimation(addKeywordButton);
        ButtonAnimation.setButtonAnimation(viewMoreTestInfoButton);
        ButtonAnimation.setButtonAnimation(downloadTestButton);
        ButtonAnimation.setButtonAnimation(returnButton);

        PageController pageController = new PageController();

        returnButton.setOnAction(this::goToMainMenu);
        applyFiltersButton.setOnAction(event -> {
            if(loadTestListFromDB()) {
                pageController.loadPages(testList, testsListView);
                pageController.setButtonListeners(goToPreviousPageButton, goToNextPageButton);
                pageController.setLabelListeners(previousPageLabel, currentPageLabel, nextPageLabel);
            }
        });
        viewMoreTestInfoButton.setOnAction(this::goToMoreInfoView);
        downloadTestButton.setOnAction(event -> {
            Test test = downloadTest();
            if(test == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Download error");
                alert.setHeaderText("Failed to download Test.");
                alert.show();
            } else {
                saveTest(test);
            }
        });
        setUpAddKeyword();
        setUpTestButtons();

        Platform.runLater(() -> {
            if(loadTestListFromDB()) {
                pageController.loadPages(testList, testsListView);
                pageController.setButtonListeners(goToPreviousPageButton, goToNextPageButton);
                pageController.setLabelListeners(previousPageLabel, currentPageLabel, nextPageLabel);
            }
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

    private void goToMoreInfoView(ActionEvent event) {
        double width;
        double height;
        Parent root;
        Stage stage;
        Scene scene;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                FolderPaths.pathToFXMLFolder + "BrowserMoreInfoView.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HBox hBox = testsListView.getSelectionModel().getSelectedItem();
        String username = ((Label) hBox.getChildren().get(0)).getText();
        String testName = ((Label) hBox.getChildren().get(1)).getText();

        ((BrowserMoreInfoViewController)loader.getController()).setUsername(username);
        ((BrowserMoreInfoViewController)loader.getController()).setTestName(testName);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        width = stage.getScene().getWidth();
        height = stage.getScene().getHeight();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    private boolean loadTestListFromDB() {
        testList = DB.getTestPage(pageNumber,userNameFilterTextField.getText(),
                testNameFilterTextField.getText(), getKeywords());
        if(testList == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Connection Error");
            alert.setHeaderText("Failed to connect to the database.");
            alert.setContentText("Try restarting the app and check your internet connection.");
            alert.setHeight(200);
            alert.show();
            return false;
        }
        return true;
    }

    private void loadTests() {
        testsListView.getItems().clear();
        for(int i = 0; i < testList.size(); i += 2) {
            HBox hBox = new HBox();
            Label userNameLabel = new Label(testList.get(i));
            userNameLabel.setPrefWidth(100);
            userNameLabel.setMinWidth(100);
            Label testNameLabel = new Label(testList.get(i + 1));
            hBox.getChildren().addAll(userNameLabel, testNameLabel);
            testsListView.getItems().add(hBox);
        }
    }

    private void setUpAddKeyword() {
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
                keywordsFlowPane.getChildren().remove(button.getParent());
                System.out.println(button.getParent());
            });

            for(Node node : keywordsFlowPane.getChildren()) {
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
            keywordsFlowPane.getChildren().add(hBox);
            keywordTextField.setText("");
        });
    }

    private List<String> getKeywords() {
        List<String> keywords = new ArrayList<>();
        for(Node node : keywordsFlowPane.getChildren()) {
            HBox hbox = (HBox) node;
            Label label = (Label) hbox.getChildren().get(0);
            keywords.add(label.getText());
        }
        return keywords;
    }

    private void setUpTestButtons() {
        testsListView.getSelectionModel().selectedItemProperty().addListener((observableValue, hBox, newSel) -> {
            if(newSel == null) {
                viewMoreTestInfoButton.setDisable(true);
                downloadTestButton.setDisable(true);
            } else {
                viewMoreTestInfoButton.setDisable(false);
                downloadTestButton.setDisable(false);
            }
        });
    }

    private Test downloadTest() {
        String username;
        String testName;

        HBox hBox = testsListView.getSelectionModel().getSelectedItem();
        username = ((Label) hBox.getChildren().get(0)).getText();
        testName = ((Label) hBox.getChildren().get(1)).getText();
        return DB.getJsonTestFromDB(username, testName).getTest();
    }

    private void saveTest(Test test) {
        System.out.println(test);
        File files = new File(FolderPaths.pathToTestFolder);
        if(files.listFiles() != null) {
            for(File file : files.listFiles()) {
                if(file.getName().equals(test.getTestName() + ".json")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Save error");
                    alert.setHeaderText("Failed save downloaded Test.");
                    alert.setContentText("You already have a test with that name." +
                            " Consider renaming or deleting local test before trying again.");
                    alert.setHeight(200);
                    alert.show();
                    return;
                }
            }
        }
        TestToJsonMapper mapper = new TestToJsonMapper();
        mapper.convertToJsonConverter(test);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Test downloaded");
        alert.setHeaderText("Successfully downloaded the test.");
        alert.show();
    }

}
