package edu.sdp.project.pethospital.entity;

public class OptionUser {
    private int testOptionId;
    private int userId;

    public OptionUser(int testOptionId, int userId) {
        this.testOptionId = testOptionId;
        this.userId = userId;
    }

    public OptionUser() {
    }

    public int getTestOptionId() {
        return testOptionId;
    }

    public void setTestOptionId(int testOptionId) {
        this.testOptionId = testOptionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
