package com.example.bettertogether;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader root = new FXMLLoader(MainMenuController.class.getResource("main-menu.fxml"));
        Scene scene = new Scene(root.load(), 700, 500);

        stage.setTitle("BetterTogether");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}