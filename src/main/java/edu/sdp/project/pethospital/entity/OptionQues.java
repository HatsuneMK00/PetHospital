package edu.sdp.project.pethospital.entity;

public class OptionQues {
    private int testOptionId;
    private int quesId;

    public OptionQues(int testOptionId, int quesId) {
        this.testOptionId = testOptionId;
        this.quesId = quesId;
    }

    public OptionQues() {
    }

    public int getTestOptionId() {
        return testOptionId;
    }

    public void setTestOptionId(int testOptionId) {
        this.testOptionId = testOptionId;
    }

    public int getQuesId() {
        return quesId;
    }

    public void setQuesId(int quesId) {
        this.quesId = quesId;
    }
}
