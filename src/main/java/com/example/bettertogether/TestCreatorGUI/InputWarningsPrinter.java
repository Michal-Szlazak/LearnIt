package com.example.bettertogether.TestCreatorGUI;

import com.example.bettertogether.Test.Question;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import java.util.List;

public class InputWarningsPrinter {

    private TextField textField;
    private ListView<Question> questionsList;
    public InputWarningsPrinter(TextField testName, ListView<Question> questions) {
        this.textField = testName;
        this.questionsList = questions;
    }

    public void printWarnings(List<TestInputMistakes> testInputMistakesList) {
        if(testInputMistakesList.isEmpty()) {
            return;
        }

        for(TestInputMistakes mistake : testInputMistakesList) {
            switch (mistake) {
                case EMPTY_TEST_NAME -> emptyNameWarning();
                case EXISTING_TEST_NAME -> existingNameWarning();
                case EMPTY_QUESTION_LIST -> emptyQuestionListWarning();
            }
        }
    }

    private void emptyNameWarning() {
        textField.setTooltip(new Tooltip("You have to add at least one letter"));
        textField.setStyle("-fx-background-color: #660033;");
        textField.onMouseClickedProperty().set(mouseEvent -> {
            textField.setTooltip(null);
            textField.setStyle("-fx-background-color: #197278;");
        });
    }

    private void existingNameWarning() {
        textField.setTooltip(new Tooltip("Name of a new test has to be unique"));
        textField.setStyle("-fx-background-color: #660033;");
        textField.onMouseClickedProperty().set(mouseEvent -> {
            textField.setTooltip(null);
            textField.setStyle("-fx-background-color: #197278;");
        });
    }

    private void emptyQuestionListWarning() {
        questionsList.setTooltip(new Tooltip("You have to create at least one question."));
        questionsList.setStyle("-fx-background-color: #660033;");
        questionsList.onMouseClickedProperty().set(mouseEvent -> {
            questionsList.setTooltip(null);
            questionsList.setStyle("-fx-background-color: #283d3b;");
        });
    }
}
