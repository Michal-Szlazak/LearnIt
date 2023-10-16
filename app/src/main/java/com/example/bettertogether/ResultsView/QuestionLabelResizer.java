package com.example.bettertogether.ResultsView;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class QuestionLabelResizer {

    public static void setResizer(Label questionLabel, VBox vBox) {
        questionLabel.setWrapText(true);
        questionLabel.setPrefWidth(vBox.getWidth() * 0.9 - 150);
        vBox.widthProperty().addListener((n, oldVal, newVal) ->
                questionLabel.setPrefWidth((double) newVal * 0.9 - 150));
    }
}
