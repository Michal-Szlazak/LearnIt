package com.example.bettertogether.ResultsView;

import com.example.bettertogether.Test.Question;
import com.example.bettertogether.TestMaker.QuestionHistory;
import com.example.bettertogether.TestMaker.UserTestHistory;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class ScoreGetter {

    public static double getScore(VBox vBox, UserTestHistory userTestHistory) {
        int i = 0;
        int correctAnswers = 0;
        for(QuestionHistory questionHistory : userTestHistory.getQuestionHistoryList()) {
            if(getResult(vBox.getChildren().get(i), questionHistory)) {
                correctAnswers++;
            }
            i++;
        }
        return (double) correctAnswers / vBox.getChildren().size() * 100;
    }

    private static boolean getResult(Node node, QuestionHistory questionHistory) {

        Question question = questionHistory.getQuestion();
        List<Integer> selectedCorrect = new ArrayList<>();
        VBox vBox = (VBox) node;

        for(String idString : questionHistory.getSelectedAnswersId()) {
            char id = idString.charAt(0);
            int idNum = id - 'A';
            if(!question.getAnswers().get(idNum).getIsCorrect()) {
                return false;
            } else {
                selectedCorrect.add(idNum);
            }
        }
        for(int id = 0; id < vBox.getChildren().size(); id++) {
            if(question.getAnswers().get(id).getIsCorrect() && !selectedCorrect.contains(id)) {
                return false;
            }
        }
        return true;
    }

}
