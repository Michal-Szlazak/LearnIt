package com.example.bettertogether.Test;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AnswerRow {

    private Label id;
    private TextField answer;
    private CheckBox isCorrect;
    public Label getId() {
        return id;
    }
    public TextField getAnswer() {
        return answer;
    }

    public CheckBox getIsCorrect() {
        return isCorrect;
    }

    public void setId(String id) {
        this.id.setText(id);
    }

    public void setAnswer(String answer) {
        this.answer.setText(answer);
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect.setSelected(isCorrect);
    }

    public AnswerRow() {
        id = new Label();
        answer = new TextField();
        isCorrect = new CheckBox();
    }
}
