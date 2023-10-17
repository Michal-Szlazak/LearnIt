package db.testIt.db.Test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Answer {
    private String id;
    private String answer;
    private boolean correct;
    public Answer() {}
    public Answer(String id, String answer, boolean correct) {
        this.id = id;
        this.answer = answer;
        this.correct = correct;
    }
    public String getId() {
        return id;
    }
    public String getAnswer() {
        return answer;
    }
    public boolean getCorrect() {
        return correct;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }
    @JsonProperty("answer")
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    @JsonProperty("correct")
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id='" + id + '\'' +
                ", answer='" + answer + '\'' +
                ", isCorrect=" + correct +
                '}';
    }
}
