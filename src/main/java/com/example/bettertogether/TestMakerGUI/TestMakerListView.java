package com.example.bettertogether.TestMakerGUI;

import com.example.bettertogether.FolderPaths;
import com.example.bettertogether.Test.AnswerRow;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.util.List;

public class TestMakerListView {

    private ListView<HBox> listView;
    private static final int answerIdLabelSize = 50;
    public TestMakerListView(ListView<HBox> listView) {
        this.listView = listView;
        listView.widthProperty().addListener((obs, oldVal, newVal) -> {
            List<HBox> answers = listView.getItems().stream().toList();
            if(!answers.isEmpty()) {
                for(HBox answer : answers) {
                    Label answerLabel = (Label) answer.getChildren().get(1);
                    answerLabel.setMaxWidth((double) newVal * 0.9 - answerIdLabelSize);
                }
            }
        });
    }

    public void loadAnswers(List<AnswerRow> answerRowList) {

        for(AnswerRow answerRow : answerRowList) {
            HBox answerHBox = craeteNewHBox();
            Label answerId = createAnswerIdLabel(answerRow);
            Label answerText = createAnswerTextLabel(answerRow);
            answerHBox.getChildren().add(0, answerId);
            answerHBox.getChildren().add(1, answerText);
            listView.getItems().add(answerHBox);
        }
    }

    private HBox craeteNewHBox() {
        HBox answerHBox = new HBox();
        answerHBox.setAlignment(Pos.CENTER_LEFT);
        answerHBox.getStylesheets().add(
                String.valueOf(getClass().getResource(
                        FolderPaths.pathToCssFiles + "TestMakerStyle/ListViewStyle.css"))
        );
        return answerHBox;
    }

    private Label createAnswerIdLabel(AnswerRow answerRow) {
        Label answerId = new Label(answerRow.getId());
        answerId.setId("answerIdLabel");
        answerId.setPrefWidth(answerIdLabelSize);
        answerId.setPrefHeight(answerIdLabelSize);
        return answerId;
    }

    private Label createAnswerTextLabel(AnswerRow answerRow) {
        Label answerText = new Label(answerRow.getAnswer());
        answerText.setId("answerTextLabel");
        answerText.setMaxWidth((double) listView.getWidth() * 0.9 - answerIdLabelSize);
        answerText.setWrapText(true);
        return answerText;
    }

}
