package com.example.bettertogether.TestMaker;

import com.example.bettertogether.Test.Question;
import com.example.bettertogether.TestMakerGUI.ListViewFromVBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class UserAnswerChecker {

    public static boolean checkResult(ListViewFromVBox listViewFromVBox, Question question) {
        boolean result = true;
        List<Integer> selectedCorrect = new ArrayList<>();
        for(HBox hBox : listViewFromVBox.getSelectedItems()) {
            Label idLabel = (Label) hBox.getChildren().get(0);
            char id = idLabel.getText().charAt(0);
            int idNum = id - 'A';
            if(!question.getAnswers().get(idNum).getIsCorrect()) {
                result = false;
            }
            selectedCorrect.add(idNum);
        }
        int id = 0;
        for(HBox hBox : listViewFromVBox.getItems()) {
            if(question.getAnswers().get(id).getIsCorrect() && !selectedCorrect.contains(id)) {
                result = false;
            }
            id++;
        }
        return result;
    }

}
