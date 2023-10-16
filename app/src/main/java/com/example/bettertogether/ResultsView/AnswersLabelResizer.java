package com.example.bettertogether.ResultsView;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AnswersLabelResizer {

    public static void setResizer(Label answerLabel, VBox vBox) {
        answerLabel.setWrapText(true);
        answerLabel.setPrefWidth(vBox.getWidth() * 0.9 - 250);
        vBox.widthProperty().addListener((n, oldVal, newVal) -> {
            answerLabel.setPrefWidth((double) newVal * 0.9 - 250);
        });
    }
}
