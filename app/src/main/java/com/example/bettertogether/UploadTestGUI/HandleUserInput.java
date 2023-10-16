package com.example.bettertogether.UploadTestGUI;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;

public class HandleUserInput {

    public static boolean handleTestListView(ListView<String> listView) {
        if(listView.getSelectionModel().getSelectedItem() == null) {
            listView.setTooltip(new Tooltip("You have to select a test."));
            listView.setStyle("-fx-background-color: #660033;");
            listView.onMouseClickedProperty().set(mouseEvent -> {
                listView.setTooltip(null);
                listView.setStyle("-fx-background-color: #283d3b;");
            });
            return false;
        } else {
            listView.setStyle("-fx-background-color: #283d3b;");
        }
        return true;
    }
    public static boolean handleNameTextField(TextField textField) {
        System.out.println(textField.getText());
        if(textField.getText().equals("")) {
            textField.setTooltip(new Tooltip("You have to add a name."));
            textField.setStyle("-fx-background-color: #660033;");
            textField.onMouseClickedProperty().set(mouseEvent -> {
                textField.setTooltip(null);
                textField.setStyle("-fx-background-color: #197278;");
            });
            return false;
        }else {
            textField.setStyle("-fx-background-color: #197278;");
        }
        return true;
    }

    public static boolean handleKeywordsFlowPane(FlowPane flowPane) {
        if(flowPane.getChildren().size() == 0 || flowPane.getChildren().size() > 10) {
            flowPane.setStyle("-fx-background-color: #660033;");
            flowPane.onMouseClickedProperty().set(mouseEvent -> {
                flowPane.setStyle("-fx-background-color: #283d3b;");
            });
            return false;
        } else {
            flowPane.setStyle("-fx-background-color: #283d3b;");
        }
        return true;
    }



}
