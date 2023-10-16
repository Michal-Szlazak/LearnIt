package com.example.bettertogether.BrowserTestGUI;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.util.List;

public class PageController {

    private ListView<HBox> testListView;
    private List<String> testList;
    private int pageNumber = 1;
    private int numberOfPages;
    private Label prevPageL;
    private Label currentPageL;
    private Label nextPageL;

    public void loadPages(List<String> testList, ListView<HBox> testListView) {
        pageNumber = 1;
        numberOfPages = testList.size() / 2 / 10 + 1;

        if(testList.size() % 20 == 0) {
            numberOfPages--;
        }
        this.testList = testList;
        this.testListView = testListView;
        loadTests();
    }
    public void setButtonListeners(Button prevPage, Button nextPage) {

        prevPage.setDisable(true);
        nextPage.setDisable(false);
        if(numberOfPages == 1) {
            nextPage.setDisable(true);
        }

        prevPage.setOnAction(event -> {
            nextPage.setDisable(false);
            nextPageL.setVisible(true);

            pageNumber--;
            loadTests();
            if(pageNumber == 1) {
                currentPageL.setText(Integer.toString(pageNumber));
                prevPage.setDisable(true);
                prevPageL.setVisible(false);
            } else {
              prevPage.setDisable(false);
              prevPageL.setVisible(true);
              nextPageL.setVisible(true);
              currentPageL.setText(Integer.toString(pageNumber));
            }
        });
        nextPage.setOnAction(event -> {
            prevPage.setDisable(false);
            prevPageL.setVisible(true);

            pageNumber++;
            loadTests();
            if(pageNumber == numberOfPages) {
                nextPage.setDisable(true);
                currentPageL.setText(Integer.toString(pageNumber));
                nextPageL.setVisible(false);
            } else {
                prevPageL.setVisible(true);
                nextPageL.setVisible(true);
                nextPage.setDisable(false);
                currentPageL.setText(Integer.toString(pageNumber));
            }
        });
    }

    public void setLabelListeners(Label prevPageLabel, Label currentPageLabel, Label nextPageLabel) {

        prevPageL = prevPageLabel;
        currentPageL = currentPageLabel;
        nextPageL = nextPageLabel;
        prevPageL.setVisible(false);
        nextPageL.setVisible(true);
        if(numberOfPages == 1) {
            prevPageL.setVisible(false);
            nextPageL.setVisible(false);
            currentPageL.setText("1");
        } else {
            nextPageL.setText("..." + numberOfPages);
            currentPageL.setText("1");
            prevPageLabel.setText("1...");
        }
    }

    private void loadTests() {
        testListView.getItems().clear();
        for(int i = (pageNumber - 1) * 20; i < testList.size() && i < (pageNumber - 1) * 20 + 20; i += 2) {
            HBox hBox = new HBox();
            Label userNameLabel = new Label(testList.get(i));
            userNameLabel.setPrefWidth(100);
            userNameLabel.setMinWidth(100);
            Label testNameLabel = new Label(testList.get(i + 1));
            hBox.getChildren().addAll(userNameLabel, testNameLabel);
            testListView.getItems().add(hBox);
        }
    }
}
