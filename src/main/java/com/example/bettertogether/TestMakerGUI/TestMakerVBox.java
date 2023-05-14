package com.example.bettertogether.TestMakerGUI;

import com.example.bettertogether.Test.Answer;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class TestMakerVBox {

    private final VBox vBox;
    private static final int answerIdLabelSize = 50;
    public TestMakerVBox(VBox vBox) {
        this.vBox = vBox;
        vBox.widthProperty().addListener((obs, oldVal, newVal) -> {
            for(Node node : vBox.getChildren()) {
                HBox hBox = (HBox) node;
                Label idLabel = (Label) hBox.getChildren().get(0);
                Label answerLabel = (Label) hBox.getChildren().get(1);
                answerLabel.setMaxWidth((double) newVal - idLabel.getWidth() - 150);
            }
        });
    }

    public void loadAnswers(List<Answer> answerList) {
        vBox.getChildren().clear();
        for(Answer answer : answerList) {
            HBox answerHBox = craeteNewHBox();
            Label answerId = createAnswerIdLabel(answer);
            Label answerText = createAnswerTextLabel(answer);
            answerHBox.getChildren().add(0, answerId);
            answerHBox.getChildren().add(1, answerText);
            vBox.getChildren().add(answerHBox);
        }
    }

    private HBox craeteNewHBox() {
        HBox answerHBox = new HBox();
        answerHBox.setSpacing(20);
        answerHBox.setAlignment(Pos.CENTER_LEFT);
        answerHBox.getStyleClass().add("answerHBox");
        return answerHBox;
    }

    private Label createAnswerIdLabel(Answer answer) {
        Label answerId = new Label(answer.getId());
        answerId.setId("answerIdLabel");
        answerId.setPrefWidth(answerIdLabelSize);
        answerId.setPrefHeight(answerIdLabelSize);
        return answerId;
    }

    private Label createAnswerTextLabel(Answer answer) {
        Label answerText = new Label(answer.getAnswer());
        answerText.setId("answerTextLabel");
        answerText.setPrefWidth((double) vBox.getWidth() * 0.9 - 200);
        answerText.setWrapText(true);
        answerText.setAlignment(Pos.CENTER_RIGHT);
        return answerText;
    }
}
