package db.testIt.db.Test;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("tests")
public class Test {


    private String testName;
    private List<Question> questions;
    public Test() {
        questions = new ArrayList<>();
    }
    public void setTestName(String testName) {
        this.testName = testName;
    }
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getTestName() {
        return testName;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        return "Test{" +
                "testName='" + testName + '\'' +
                ", questions=" + questions +
                '}';
    }
}
