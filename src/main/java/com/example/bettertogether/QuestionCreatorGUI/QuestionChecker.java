package com.example.bettertogether.QuestionCreatorGUI;

import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class QuestionChecker {

    public static boolean checkQuestion(TextField textField) {

        if(textField.getText().isEmpty()) {
            textField.setStyle("-fx-background-color: #660033;");
            textField.setTooltip(new Tooltip("You have to add at least one letter to the question."));
            textField.onMouseClickedProperty().set(mouseEvent -> {
                textField.setStyle("-fx-background-color: #283d3b;");
                textField.setTooltip(null);
            });
            return false;
        } else if(textField.getText().length() > 200){
            textField.setStyle("-fx-background-color: #660033;");
            textField.setTooltip(new Tooltip("Your question cannot have more than 200 letters."));
            textField.onMouseClickedProperty().set(mouseEvent -> {
                textField.setStyle("-fx-background-color: #283d3b;");
                textField.setTooltip(null);
            });
            return false;
        } else {
            return true;
        }
    }
}
