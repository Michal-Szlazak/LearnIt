package com.example.bettertogether.TestMaker;

import com.example.bettertogether.Test.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class QuestionShuffler {
    public static List<Question> shuffleQuestions(List<Question> questionList) {
        List<Question> shuffledList = new ArrayList<>(questionList);
        Collections.shuffle(shuffledList);
        return shuffledList;
    }
}
