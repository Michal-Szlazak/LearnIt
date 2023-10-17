package db.testIt.db.Test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionStatistics {

    private int answeredCorrectly;
    private int sumOfAnswers;
    private int correctlyAnsweredStreak;

    public QuestionStatistics() {
        answeredCorrectly = 0;
        sumOfAnswers = 0;
        correctlyAnsweredStreak = 0;
    }
    public void setAnsweredCorrectly(int answeredCorrectly) {
        this.answeredCorrectly = answeredCorrectly;
    }
    public void setSumOfAnswers(int sumOfAnswers) {
        this.sumOfAnswers = sumOfAnswers;
    }
    public void setCorrectlyAnsweredStreak(int correctlyAnsweredStreak) {
        this.correctlyAnsweredStreak = correctlyAnsweredStreak;
    }

    public int getAnsweredCorrectly() {
        return answeredCorrectly;
    }

    public int getSumOfAnswers() {
        return sumOfAnswers;
    }

    public int getCorrectlyAnsweredStreak() {
        return correctlyAnsweredStreak;
    }

    @Override
    public String toString() {
        return "QuestionStatistics{" +
                "answeredCorrectly=" + answeredCorrectly +
                ", sumOfAnswers=" + sumOfAnswers +
                ", correctlyAnsweredStreak=" + correctlyAnsweredStreak +
                '}';
    }
}
