package com.example.bettertogether.TestSettings;

public class TestSettingsHolder {
    public static TestMode testMode = TestMode.FULL;
    public static boolean repeatWronglyAnsweredQuestions = false;
    public static boolean shuffleQuestions = false;
    public static void resetTestSettings() {
        testMode = TestMode.FULL;
        repeatWronglyAnsweredQuestions = false;
        shuffleQuestions = false;
    }
}
