package com.example.bettertogether;
;
import com.example.bettertogether.Test.TestModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TestCreatorController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private TestModel testModel;

    public TestCreatorController() {
        testModel = new TestModel();
        System.out.println(testModel.getTest().toString());
    }

    public void goToMainMenu(ActionEvent event) throws IOException {
        double height;
        double width;

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionCreator.fxml"));
        Parent root = loader.load();
        ((QuestionCreatorController)loader.getController()).setTestModel(testModel);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        height = stage.getScene().getHeight();
        width = stage.getScene().getWidth();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

}
