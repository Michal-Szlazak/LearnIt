package com.example.bettertogether.TestCreatorGUI;

import com.example.bettertogether.QuestionCreatorController;
import com.example.bettertogether.Test.Question;
import com.example.bettertogether.TestCreatorController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class TestListView {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private ListView<Question> listView;

    public TestListView(ListView<Question> listView) {
        this.listView = listView;
    }

    public void loadQuestions(List<Question> questionList) {
        if(questionList.isEmpty()) {
            return;
        }
        listView.getItems().addAll(questionList);
    }

    public void deleteQuestion() {
        Question question = listView.getSelectionModel().getSelectedItem();
        listView.getItems().remove(question);
    }
}
