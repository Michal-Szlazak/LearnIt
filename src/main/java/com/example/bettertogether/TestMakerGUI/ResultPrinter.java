package com.example.bettertogether.TestMakerGUI;

import com.example.bettertogether.Test.Question;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class ResultPrinter {

    public static void showResult(ListViewFromVBox listViewFromVBox, Question question) {
        List<Integer> selectedCorrect = new ArrayList<>();
        for(HBox hBox : listViewFromVBox.getSelectedItems()) {
            Label idLabel = (Label) hBox.getChildren().get(0);
            char id = idLabel.getText().charAt(0);
            int idNum = id - 'A';
            if(!question.getAnswers().get(idNum).getCorrect()) {
                hBox.setStyle("-fx-background-color: #660033; -fx-background-radius: 10;");
                Label info = new Label("wrong");
                info.setId("resultLabel");
                hBox.getChildren().add(info);
            } else {
                hBox.setStyle("-fx-background-color: #009900; -fx-background-radius: 10;");
                Label info = new Label("correct");
                info.setId("resultLabel");
                hBox.getChildren().add(info);
                selectedCorrect.add(idNum);
            }
        }
        int id = 0;
        for(HBox hBox : listViewFromVBox.getItems()) {
            if(question.getAnswers().get(id).getCorrect() && !selectedCorrect.contains(id)) {
                hBox.setStyle("-fx-border-color: #009900; -fx-border-radius: 10; -fx-background-radius: 10;" +
                        " -fx-border-width: 2;");
                Label info = new Label("not selected");
                info.setId("resultLabel");
                hBox.getChildren().add(info);
            }
            id++;
        }
    }
}
