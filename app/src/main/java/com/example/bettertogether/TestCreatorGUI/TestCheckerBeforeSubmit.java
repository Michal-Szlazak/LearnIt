package com.example.bettertogether.TestCreatorGUI;

import com.example.bettertogether.FolderPaths;
import com.example.bettertogether.Test.Question;
import com.example.bettertogether.Test.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestCheckerBeforeSubmit {

    public static List<TestInputMistakes> checkTest(Test test) {
        List<TestInputMistakes> testInputMistakesList = new ArrayList<>();

        TestInputMistakes mistake = checkTestName(test.getTestName());
        if(mistake != null) {
            testInputMistakesList.add(mistake);
        }

        mistake = checkQuestionList(test.getQuestions());
        if(mistake != null) {
            testInputMistakesList.add(mistake);
        }

        return testInputMistakesList;
    }
    private static TestInputMistakes checkTestName(String name) {

        if(name == null || name.isEmpty()) {
            return TestInputMistakes.EMPTY_TEST_NAME;
        }
        File testFiles = new File(FolderPaths.getJarDirPath() + FolderPaths.pathToTestFolder);
        if(testFiles.listFiles() == null) {
            return null;
        }
        for(File testFile : testFiles.listFiles()) {
            if(testFile.getName().equals(name + ".json")) {
                return TestInputMistakes.EXISTING_TEST_NAME;
            }
        }
        return null;
    }

    private static TestInputMistakes checkQuestionList(List<Question> questionList) {
        if(questionList.isEmpty()) {
            return TestInputMistakes.EMPTY_QUESTION_LIST;
        }
        return null;
    }
}
