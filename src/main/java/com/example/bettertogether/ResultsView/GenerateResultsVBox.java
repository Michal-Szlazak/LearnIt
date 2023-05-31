package com.example.bettertogether.ResultsView;

import com.example.bettertogether.FolderPaths;
import com.example.bettertogether.Test.Answer;
import com.example.bettertogether.Test.Question;
import com.example.bettertogether.TestMaker.QuestionHistory;
import com.example.bettertogether.TestMaker.UserTestHistory;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class GenerateResultsVBox {
    private final VBox vBox;
    private final UserTestHistory userTestHistory;

    public GenerateResultsVBox(VBox vBox, UserTestHistory userTestHistory) {
        this.vBox = vBox;
        this.userTestHistory = userTestHistory;
    }

    public void generateQuestionsVBox() {
        for(QuestionHistory questionHistory : userTestHistory.getQuestionHistoryList()) {
            vBox.getChildren().add(generateQuestionVBox(questionHistory));
        }
    }
    private VBox generateQuestionVBox(QuestionHistory questionHistory) {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("questionVBox");
        vBox.getChildren().addAll(
                createTitleLabel(questionHistory),
                generateAnswersVBox(questionHistory)
        );
        return vBox;
    }

    private Label createTitleLabel(QuestionHistory questionHistory) {
        String title = questionHistory.getQuestion().getQuestion();
        Label titleLabel = new Label(title);
        titleLabel.setId("questionTitleLabel");
        QuestionLabelResizer.setResizer(titleLabel, vBox);
        return titleLabel;
    }

    private VBox generateAnswersVBox(QuestionHistory questionHistory) {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setId("answersVBox");
        List<Answer> answers = questionHistory.getQuestion().getAnswers();

        for(Answer answer : answers) {
            HBox answerHBox = generateAnswerHBox(answer);
            answerHBox.getStylesheets().add(
                    String.valueOf(getClass().getResource(
                            FolderPaths.pathToCssFiles + "TestMakerResultsView/TestMakerResultsListViewStyle.css"))
            );
            vBox.getChildren().add(answerHBox);
        }
        showResult(vBox, questionHistory);
        return vBox;
    }

    private HBox generateAnswerHBox(Answer answer) {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setId("answerHBox");
        Label idLabel = new Label(answer.getId());
        Label answerLabel = new Label(answer.getAnswer());
        idLabel.setId("answerIdLabel");
        answerLabel.setId("answerTextLabel");
        AnswersLabelResizer.setResizer(answerLabel, vBox);
        hBox.getChildren().addAll(idLabel, answerLabel);
        return hBox;
    }

    private void showResult(VBox vBox, QuestionHistory questionHistory) {

        Question question = questionHistory.getQuestion();
        List<Integer> selectedCorrect = new ArrayList<>();

        for(String idString : questionHistory.getSelectedAnswersId()) {
            char id = idString.charAt(0);
            int idNum = id - 'A';
            HBox hBox = (HBox) vBox.getChildren().get(idNum);
            if(!question.getAnswers().get(idNum).getCorrect()) {
                hBox.setStyle("-fx-background-color: #660033; -fx-background-radius: 10;");
                Label info = new Label("wrong");
                info.setId("answerResultLabel");
                hBox.getChildren().add(info);
            } else {
                hBox.setStyle("-fx-background-color: #009900; -fx-background-radius: 10;");
                Label info = new Label("correct");
                info.setId("answerResultLabel");
                hBox.getChildren().add(info);
                selectedCorrect.add(idNum);
            }
        }
        int id = 0;
        for(Node node : vBox.getChildren()) {
            HBox hBox = (HBox) node;
            if(question.getAnswers().get(id).getCorrect() && !selectedCorrect.contains(id)) {
                hBox.setStyle("-fx-border-color: #009900; -fx-border-radius: 10; -fx-background-radius: 10;" +
                        " -fx-border-width: 2;");
                Label info = new Label("not selected");
                info.setId("answerResultLabel");
                hBox.getChildren().add(info);
            }
            id++;
        }
    }

}
