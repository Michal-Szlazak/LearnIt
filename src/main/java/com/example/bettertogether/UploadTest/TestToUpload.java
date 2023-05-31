package com.example.bettertogether.UploadTest;

import com.example.bettertogether.Test.Test;

import java.util.List;

public class TestToUpload {

    private String userName;
    private List<String> keywords;
    private String description;
    private Test test;
    private String id;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }
    public List<String> getKeywords() {
        return keywords;
    }
    public String getDescription() {
        return description;
    }
    public Test getTest() {
        return test;
    }

    public String getId() {
        return id;
    }
}
