package com.example.bettertogether;

import javafx.animation.*;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ButtonAnimation {

    private static final Duration animationDuration = Duration.millis(100);
    private static final int FadeSteps = 25;

    public static void setButtonAnimation(Button button) {
        addHoverTransition(button);
        //addFadeTransition(button);
    }

    private static void addHoverTransition(Button button) {


        DropShadow shadow = new DropShadow();
        shadow.setBlurType(BlurType.ONE_PASS_BOX);
        shadow.setColor(Color.BLACK);
        button.setEffect(shadow);

        Timeline xOffsetTimeline = new Timeline(
                new KeyFrame(Duration.millis(0),
                        new KeyValue(shadow.offsetXProperty(), shadow.offsetXProperty().getValue(), Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(100), new KeyValue(shadow.offsetXProperty(), -3, Interpolator.LINEAR)
                ));
        xOffsetTimeline.setCycleCount(2);

        Timeline xReverseOffsetTimeline = new Timeline(
                new KeyFrame(Duration.millis(0),
                        new KeyValue(shadow.offsetXProperty(), shadow.offsetXProperty().getValue(), Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(100), new KeyValue(shadow.offsetXProperty(), 0, Interpolator.LINEAR)
                ));

        Timeline yOffsetTimeline = new Timeline(
                new KeyFrame(Duration.millis(0),
                        new KeyValue(shadow.offsetYProperty(), shadow.offsetYProperty().getValue(), Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(100), new KeyValue(shadow.offsetYProperty(), -3, Interpolator.LINEAR)
                ));

        Timeline yReverseOffsetTimeline = new Timeline(
                new KeyFrame(Duration.millis(0),
                        new KeyValue(shadow.offsetYProperty(), shadow.offsetYProperty().getValue(), Interpolator.LINEAR)),
                new KeyFrame(Duration.millis(100), new KeyValue(shadow.offsetYProperty(), 0, Interpolator.LINEAR)
                ));

        TranslateTransition tt = new TranslateTransition(animationDuration, button);
        button.onMouseEnteredProperty().set(mouseEvent -> {
            tt.setFromX(0);
            tt.setFromY(0);
            tt.setToX(3);
            tt.setToY(3);
            xReverseOffsetTimeline.stop();
            yReverseOffsetTimeline.stop();
            xOffsetTimeline.play();
            yOffsetTimeline.play();
            tt.play();
        });
        button.onMouseExitedProperty().set(mouseEvent -> {
            tt.stop();
            tt.setFromX(button.getTranslateX());
            tt.setFromY(button.getTranslateY());
            tt.setToX(0);
            tt.setToY(0);
            tt.play();
            xOffsetTimeline.stop();
            yOffsetTimeline.stop();
            xReverseOffsetTimeline.play();
            yReverseOffsetTimeline.play();
        });
    }

    private static void addFadeTransition(Button button) {

        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0.0);
        button.setEffect(colorAdjust);

        Timeline fadeInTimeline = new Timeline(
                new KeyFrame(Duration.millis(0),
                        new KeyValue(colorAdjust.brightnessProperty(), colorAdjust.brightnessProperty().getValue(), Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(10), new KeyValue(colorAdjust.brightnessProperty(), 0.8, Interpolator.EASE_OUT)
                ));
        fadeInTimeline.setCycleCount(2);
        fadeInTimeline.setAutoReverse(true);

        button.setOnMouseClicked(e -> {
            fadeInTimeline.play();
        });
    }

    private static void addShadowOnHover(Button button) {
    }

}
