package com.example.bettertogether.Test;

import java.util.ArrayList;
import java.util.List;

public class Test {

    private List<Question> questions;

    Test() {
        questions = new ArrayList<>();
    }

    public void addNewQuestion(Question question) {
        questions.add(question);
    }

}
