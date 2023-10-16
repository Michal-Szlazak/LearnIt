package com.example.bettertogether.TestMakerGUI;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SetWrapperTestMakerQuestionLabel {

    public static void setWrapper(Label label, VBox vBox) {
        label.setWrapText(true);
        label.setPrefWidth(vBox.getWidth() * 0.9);
        vBox.widthProperty().addListener((n, oldVal, newVal) -> label.setPrefWidth((double) newVal * 0.9));
    }
}
