package db.testIt.db.Test;

import java.util.List;

public class Question {

    private String question;
    private List<Answer> answers;
    private QuestionStatistics questionStatistics = new QuestionStatistics();

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answers=" + answers +
                ", questionStatistics=" + questionStatistics +
                '}';
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void setQuestionStatistics(QuestionStatistics questionStatistics) {
        this.questionStatistics = questionStatistics;
    }

    public String getQuestion() {
        return question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public QuestionStatistics getQuestionStatistics() {
        return questionStatistics;
    }
}
