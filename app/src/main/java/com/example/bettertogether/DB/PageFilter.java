package com.example.bettertogether.DB;

import java.util.List;

public class PageFilter {
    private String userName;
    private String testName;
    private List<String> keywords;
    private int pageNumber;

    public PageFilter(String userName, String testName, List<String> keywords, int pageNumber) {
        this.userName = userName;
        this.testName = testName;
        this.keywords = keywords;
        this.pageNumber = pageNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getTestName() {
        return testName;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
