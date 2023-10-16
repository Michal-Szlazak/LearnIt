package com.example.bettertogether.TestMakerGUI;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class ListViewFromVBox {

    private final VBox vBox;

    public ListViewFromVBox(VBox vBox, boolean multipleChoice) {
        this.vBox = vBox;
        setMouseEvents(this.vBox, multipleChoice);
        setHoverMouseEvents(this.vBox);
    }
    private void setHoverMouseEvents(VBox vBox) {

        for(Node node : vBox.getChildren()) {
            HBox hBox = (HBox) node;

            DropShadow shadow = new DropShadow();
            shadow.setBlurType(BlurType.ONE_PASS_BOX);
            shadow.setColor(Color.LIGHTBLUE);

            Timeline timelineDropShadow = new Timeline(
                    new KeyFrame(Duration.millis(100),
                            new KeyValue(shadow.offsetXProperty(), -3),
                            new KeyValue(shadow.offsetYProperty(), -3)
                    )
            );

            Timeline timelineReverseDropShadow = new Timeline(
                    new KeyFrame(Duration.millis(100),
                            new KeyValue(shadow.offsetXProperty(), 0),
                            new KeyValue(shadow.offsetYProperty(), 0)
                    )
            );


            hBox.onMouseEnteredProperty().set(mouseEvent -> {
                hBox.setEffect(shadow);
                timelineReverseDropShadow.stop();
                timelineDropShadow.play();
            });
            hBox.onMouseExitedProperty().set(mouseEvent -> {
                hBox.setEffect(shadow);
                timelineDropShadow.stop();
                timelineReverseDropShadow.play();
            });
        }
    }
    private void setMouseEvents(VBox vBox, boolean multipleChoice) {
        if(multipleChoice) {
            setSelectionMouseEventMultipleChoice(vBox);
        } else {
            setSelectionMouseEventSingleChoice(vBox);
        }
    }

    private void setSelectionMouseEventMultipleChoice(VBox vBox) {
        for(Node node : vBox.getChildren()) {
            HBox hBox = (HBox) node;
            hBox.onMouseClickedProperty().set(mouseEvent -> {
                if(!mouseEvent.isControlDown()) {
                    clearSelection(vBox);
                }
                hBox.getStyleClass().add("selectedAnswerHBox");
            });
        }
    }
    private void setSelectionMouseEventSingleChoice(VBox vBox) {
        for(Node node : vBox.getChildren()) {
            HBox hBox = (HBox) node;
            hBox.onMouseClickedProperty().set(mouseEvent -> {
                clearSelection(vBox);
                hBox.getStyleClass().add("selectedAnswerHBox");
            });
        }
    }
    private void clearSelection(VBox vBox) {
        for(Node node : vBox.getChildren()) {
            HBox hBox = (HBox) node;
            hBox.getStyleClass().remove("selectedAnswerHBox");
        }
    }

    public List<HBox> getSelectedItems() {
        List<HBox> list = new ArrayList<>();
        for(Node node : vBox.getChildren()) {
            HBox hBox = (HBox) node;
            if(hBox.getStyleClass().contains("selectedAnswerHBox")){
                list.add(hBox);
            }
        }
        return list;
    }

    public List<HBox> getItems() {
        List<HBox> list = new ArrayList<>();
        for(Node node : vBox.getChildren()) {
            HBox hBox = (HBox) node;
            list.add(hBox);
        }
        return list;
    }
}
