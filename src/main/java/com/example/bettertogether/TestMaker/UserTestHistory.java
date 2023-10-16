package com.example.bettertogether.TestMaker;

import java.util.ArrayList;
import java.util.List;

public class UserTestHistory {

    private String testName;
    private List<QuestionHistory> questionHistoryList;
    public UserTestHistory() {
        questionHistoryList = new ArrayList<>();
    }
    public void addQuestionHistory(QuestionHistory questionHistory) {
        questionHistoryList.add(questionHistory);
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestName() {
        return testName;
    }

    public List<QuestionHistory> getQuestionHistoryList() {
        return questionHistoryList;
    }
}
