package com.example.bettertogether.MoreInfoView;

import com.example.bettertogether.FolderPaths;
import com.example.bettertogether.Test.Answer;
import com.example.bettertogether.Test.Question;
import com.example.bettertogether.Test.Test;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class VBoxInfoViewGenerator {
    private final VBox vBox;
    private final Test test;

    public VBoxInfoViewGenerator(VBox vBox, Test test) {
        this.vBox = vBox;
        this.test = test;
    }

    public void generateQuestionsVBox() {
        for(Question question : test.getQuestions()) {
            vBox.getChildren().add(generateQuestionVBox(question));
        }
    }
    private VBox generateQuestionVBox(Question question) {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("questionVBox");
        vBox.getChildren().addAll(
                createTitleLabel(question),
                generateAnswersVBox(question)
        );
        return vBox;
    }

    private Label createTitleLabel(Question question) {
        String title = question.getQuestion();
        Label titleLabel = new Label(title);
        titleLabel.setId("questionTitleLabel");
        QuestionLabelResizer.setResizer(titleLabel, vBox);
        return titleLabel;
    }

    private VBox generateAnswersVBox(Question question) {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setId("answersVBox");
        List<Answer> answers = question.getAnswers();

        for(Answer answer : answers) {
            HBox answerHBox = generateAnswerHBox(answer);
            answerHBox.getStylesheets().add(
                    String.valueOf(getClass().getResource(
                            FolderPaths.pathToCssFiles + "TestMakerResultsView/TestMakerResultsListViewStyle.css"))
            );
            vBox.getChildren().add(answerHBox);
        }
        showResult(vBox, question);
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

    private void showResult(VBox vBox, Question question) {
        int id = 0;
        for(Node node : vBox.getChildren()) {
            HBox hBox = (HBox) node;
            if(question.getAnswers().get(id).getCorrect()) {
                hBox.setStyle("-fx-border-color: #009900; -fx-border-radius: 10; -fx-background-radius: 10;" +
                        " -fx-border-width: 2; -fx-background-color: green;");
            }
            id++;
        }
    }

}
