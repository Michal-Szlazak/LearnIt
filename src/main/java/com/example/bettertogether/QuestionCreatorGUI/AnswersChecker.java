package com.example.bettertogether.QuestionCreatorGUI;

import com.example.bettertogether.FolderPaths;
import com.example.bettertogether.Test.AnswerRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class AnswersChecker {

   public boolean checkAnswers(TableView<AnswerRow> answerTableView) {

       clearStyles(answerTableView);
       boolean result = true;
       int correctAnswers = 0;
       for(AnswerRow answerRow : answerTableView.getItems()) {
           if(answerRow.getIsCorrect().isSelected()) {
               correctAnswers++;
           }
           TextField answer = answerRow.getAnswer();
           if(answer.getText().isEmpty()) {
               answer.setStyle("-fx-background-color: #660033;");
               answer.setTooltip(new Tooltip("You have to add at least one character to the answer."));
               answer.onMouseClickedProperty().set(mouseEvent -> {
                   answer.setStyle("-fx-background-color: #283d3b;");
                   answer.setTooltip(null);
               });
               result = false;
           } else if(answer.getText().length() > 200) {
               answer.setStyle("-fx-background-color: #660033;");
               answer.setTooltip(new Tooltip("Your answer cannot be longer than 200 characters."));
               answer.onMouseClickedProperty().set(mouseEvent -> {
                   answer.setStyle("-fx-background-color: #283d3b;");
                   answer.setTooltip(null);
               });
               result = false;
           }
       }

       if(answerTableView.getItems().size() < 2) {
           answerTableView.setStyle("-fx-background-color: #660033;");
           answerTableView.setTooltip(new Tooltip("You have to add at least two answers to the question."));
           answerTableView.onMouseClickedProperty().set(mouseEvent -> {
               answerTableView.setStyle("-fx-background-color: #283d3b;");
               answerTableView.setTooltip(null);
           });
           result = false;
       } else if (correctAnswers == 0) {
           for(AnswerRow answerRow : answerTableView.getItems()) {
               answerRow.getIsCorrect().getStylesheets().add(String.valueOf(getClass().getResource(
                       FolderPaths.pathToCssFiles + "QuestionCreatorStyle/CheckBoxWarning.css")));
               answerRow.getIsCorrect().setTooltip(new Tooltip("You have to select at least one correct answer"));
               answerRow.getIsCorrect().onMouseClickedProperty().set(mouseEvent -> {
                   for(AnswerRow answerRow1 : answerTableView.getItems()) {
                       answerRow1.getIsCorrect().getStylesheets().remove(String.valueOf(getClass().getResource(
                               FolderPaths.pathToCssFiles + "QuestionCreatorStyle/CheckBoxWarning.css")));
                       answerRow1.getIsCorrect().setTooltip(null);
                   }
               });
           }
           result = false;
       }
       return result;
    }

    private void clearStyles(TableView<AnswerRow> answerTableView) {
        for (AnswerRow answerRow : answerTableView.getItems()) {
            TextField answer = answerRow.getAnswer();
            answer.setStyle("-fx-background-color: #283d3b;");
            answer.setTooltip(null);

            answerRow.getIsCorrect().getStylesheets().remove(String.valueOf(getClass().getResource(
                FolderPaths.pathToCssFiles + "QuestionCreatorStyle/CheckBoxWarning.css")));
            answerRow.getIsCorrect().setTooltip(null);
        }

        answerTableView.setStyle("-fx-background-color: #283d3b;");
        answerTableView.setTooltip(null);
    }
}
