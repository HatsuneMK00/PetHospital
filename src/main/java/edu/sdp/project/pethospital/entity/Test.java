package edu.sdp.project.pethospital.entity;

import java.sql.Timestamp;

public class Test {
    private int testId;
    private int userId;
    private int testOptionId;
    private Timestamp beginDate;
    private Timestamp endDate;
    private int score;

    public Test() {
    }

    public Test(int testId, int userId, int testOptionId, Timestamp beginDate, Timestamp endDate, int score) {
        this.testId = testId;
        this.userId = userId;
        this.testOptionId = testOptionId;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.score = score;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getTestOptionId() {
        return testOptionId;
    }

    public void setTestOptionId(int testOptionId) {
        this.testOptionId = testOptionId;
    }

    public Timestamp getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Timestamp beginDate) {
        this.beginDate = beginDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
