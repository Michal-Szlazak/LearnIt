package com.example.bettertogether.Test;

import com.example.bettertogether.QuestionCreatorGUI.QuestionTextField;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    public Label getIdLabel() {
        return id;
    }
    @JsonIgnore
    public TextField getAnswerTextField() {
        return answer;
    }
    @JsonIgnore
    public CheckBox getIsCorrectCheckBox() {
        return isCorrect;
    }

    public boolean getIsCorrect() {
        return isCorrect.isSelected();
    }

    public String getId() {
        return id.getText();
    }

    public String getAnswer() {
        return answer.getText();
    }

    public Answer() {
        id = new Label();
        answer = new TextField();
        isCorrect = new CheckBox();

        new QuestionTextField(answer);
    }

    @Override
    public String toString() {
        return answer.getText();
    }

    
}
