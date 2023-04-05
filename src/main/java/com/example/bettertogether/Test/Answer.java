package com.example.bettertogether.Test;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Answer {

    private Label id;
    private TextField answer;
    private CheckBox isCorrect;

    public void setId(String id) {
        this.id = new Label(id);
    }

    public void setAnswer(TextField answer) {
        this.answer = answer;
    }

    public void setIsCorrect(CheckBox isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Label getId() {
        return id;
    }

    public TextField getAnswer() {
        return answer;
    }

    public CheckBox getIsCorrect() {
        return isCorrect;
    }

    public Answer() {
        id = new Label();
        answer = new TextField();
        isCorrect = new CheckBox();
    }
}
