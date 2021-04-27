package edu.sdp.project.pethospital.entity;

public class PaperQues {
    private int testOptionId;
    private int quesId;

    public PaperQues(int testOptionId, int quesId) {
        this.testOptionId = testOptionId;
        this.quesId = quesId;
    }

    public PaperQues() {
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
