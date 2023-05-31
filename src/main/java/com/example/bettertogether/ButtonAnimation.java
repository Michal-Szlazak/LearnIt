package com.example.bettertogether;

import javafx.animation.*;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ButtonAnimation {

    private static final Duration animationDuration = Duration.millis(100);

    public static void setButtonAnimation(Button button) {
        addHoverTransition(button);
        addFadeTransition(button);
    }

    private static void addHoverTransition(Button button) {

        DropShadow shadow = new DropShadow();
        shadow.setBlurType(BlurType.ONE_PASS_BOX);
        shadow.setColor(Color.BLACK);
        button.setEffect(shadow);

        Timeline timelineDropShadow = new Timeline(
                new KeyFrame(animationDuration,
                        new KeyValue(shadow.offsetXProperty(), -3),
                        new KeyValue(shadow.offsetYProperty(), -3)
                )
        );

        Timeline timelineReverseDropShadow = new Timeline(
                new KeyFrame(animationDuration,
                        new KeyValue(shadow.offsetXProperty(), 0),
                        new KeyValue(shadow.offsetYProperty(), 0)
                )
        );

        TranslateTransition tt = new TranslateTransition(animationDuration, button);
        button.onMouseEnteredProperty().set(mouseEvent -> {
            button.setEffect(shadow);
            tt.setFromX(0);
            tt.setFromY(0);
            tt.setToX(3);
            tt.setToY(3);
            timelineReverseDropShadow.stop();
            timelineDropShadow.play();
            tt.play();
        });
        button.onMouseExitedProperty().set(mouseEvent -> {
            button.setEffect(shadow);
            tt.stop();
            tt.setFromX(button.getTranslateX());
            tt.setFromY(button.getTranslateY());
            tt.setToX(0);
            tt.setToY(0);
            timelineDropShadow.stop();
            timelineReverseDropShadow.play();
            tt.play();
        });
    }

    private static void addFadeTransition(Button button) {

        ScaleTransition transition = new ScaleTransition();
        transition.setDuration(animationDuration);
        transition.setNode(button);
        transition.setFromX(1);
        transition.setFromY(1);
        button.setOnMousePressed(mouseEvent -> {
            transition.setFromX(1);
            transition.setFromY(1);
            transition.setToX(1.05);
            transition.setToY(1.03);
            transition.play();
        });
        button.setOnMouseReleased(mouseEvent -> {
            transition.setFromX(1.05);
            transition.setFromY(1.03);
            transition.setToX(1);
            transition.setToY(1);
            transition.play();
        });

    }

}
