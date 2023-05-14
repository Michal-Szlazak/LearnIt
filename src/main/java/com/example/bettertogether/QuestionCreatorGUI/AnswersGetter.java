package com.example.bettertogether.QuestionCreatorGUI;

import com.example.bettertogether.Test.Answer;
import com.example.bettertogether.Test.AnswerRow;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

public class AnswersGetter {

    private final TableView<AnswerRow> tableView;
    public AnswersGetter(TableView<AnswerRow> tableView) {
        this.tableView = tableView;
    }
    public List<Answer> getAnswers() {
        List<Answer> answers = new ArrayList<>();
        for(AnswerRow answerRow : tableView.getItems()) {
            Answer answer = new Answer(answerRow.getId().getText(), answerRow.getAnswer().getText(), answerRow.getIsCorrect().isSelected());
            answers.add(answer);
        }
        return answers;
    }

}
