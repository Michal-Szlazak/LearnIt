package com.example.bettertogether.UploadTestGUI;

import javafx.animation.RotateTransition;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class LoadingLabel {

    private static boolean loading = true;

    public static void load(Label label) {
        label.setManaged(true);
        label.setVisible(true);
        RotateTransition rt = new RotateTransition(Duration.millis(3000), label);
        rt.setByAngle(360);

        Thread thread = new Thread(() -> {
            while(loading) {
                rt.play();
            }
        });
        thread.start();
    }

    public static void stopLoading(Label label) {
        label.setManaged(false);
        label.setVisible(false);
        loading = false;
    }

}
