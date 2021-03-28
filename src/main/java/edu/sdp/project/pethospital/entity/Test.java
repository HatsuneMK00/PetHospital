package edu.sdp.project.pethospital.entity;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Test  {
    private Integer testId;

    private Integer userId;

    private Integer testOptionId;
    private Date beginDate;

    private Date endDate;

    private Integer score;

    public Test(Integer testId, Integer userId, Integer testOptionId, Date beginDate, Date endDate, Integer score) {
        this.testId = testId;
        this.userId = userId;
        this.testOptionId = testOptionId;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.score = score;
    }

    public Test() {
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTestOptionId() {
        return testOptionId;
    }

    public void setTestOptionId(Integer testOptionId) {
        this.testOptionId = testOptionId;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
    public void updateTest(Map params) {
        if (params.containsKey("testId"))
            this.testId= Integer.valueOf(params.get("testId").toString());
        if (params.containsKey("userId"))
            this.userId = Integer.valueOf(params.get("userId").toString());
        if(params.containsKey("testOptionId"))
            this.testOptionId=Integer.valueOf(params.get("testOptionId").toString());
        if (params.containsKey("beginDate")){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = format.parse(params.get("beginDate").toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.beginDate = Timestamp.valueOf(format.format(date));
        }
        if (params.containsKey("endDate")){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = format.parse(params.get("endDate").toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.endDate = Timestamp.valueOf(format.format(date));
        }
        if (params.containsKey("score"))
            this.score = Integer.valueOf(params.get("score").toString());
    }
}