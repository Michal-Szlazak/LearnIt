package com.example.bettertogether.TestMaker;

import com.example.bettertogether.Test.Answer;
import com.example.bettertogether.Test.Question;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class QuestionHistory {

    private Question question;
    private List<String> selectedAnswersId;
    public QuestionHistory() {
        selectedAnswersId = new ArrayList<>();
    }
    public void setQuestion(Question question) {
        this.question = question;
    }
    public void setAnswersIdFromHBox(List<HBox> hBoxList) {
        for(HBox hBox : hBoxList) {
            Label labelId = (Label) hBox.getChildren().get(0);
            selectedAnswersId.add(labelId.getText());
        }
    }

    public Question getQuestion() {
        return question;
    }

    public List<String> getSelectedAnswersId() {
        return selectedAnswersId;
    }
}
