package com.example.bettertogether.QuestionCreatorGUI;

import com.example.bettertogether.Test.Answer;
import com.example.bettertogether.Test.AnswerRow;
import com.example.bettertogether.Test.Question;
import javafx.scene.control.TextField;

public class AnswerLoader {

    private final TextField questionTextField;
    private final AnswerTableView answerTableView;

    public AnswerLoader(TextField questionTextField, AnswerTableView answerTableView) {
        this.questionTextField = questionTextField;
        this.answerTableView = answerTableView;
    }

    public void loadQuestion(Question question) {
        questionTextField.setText(question.toString());
        for(Answer answer : question.getAnswers()) {
            AnswerRow answerRow = new AnswerRow();
            answerRow.setId(answer.getId());
            answerRow.setAnswer(answer.getAnswer());
            answerRow.setIsCorrect(answer.getIsCorrect());
            answerTableView.addAnswer(answerRow);
        }
    }

}
