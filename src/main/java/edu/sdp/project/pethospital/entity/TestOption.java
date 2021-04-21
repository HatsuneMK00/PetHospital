package edu.sdp.project.pethospital.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class TestOption {
    private Integer testOptionId;

    private String testOptionName;

    private Integer goal;

    private Integer selectNum;

    private Integer judgeNum;

    private Integer qaNum;

    private Integer totalScore;

    private String selectTag;

    private String judgeTag;

    private String qaTag;

    private Integer duration;

    private Timestamp startDate;

    private Integer paperId;

    public TestOption(Integer testOptionId, String testOptionName, Integer goal, Integer selectNum, Integer judgeNum, Integer qaNum, Integer totalScore, String selectTag, String judgeTag, String qaTag, Integer duration, Timestamp startDate, Integer paperId) {
        this.testOptionId = testOptionId;
        this.testOptionName = testOptionName;
        this.goal = goal;
        this.selectNum = selectNum;
        this.judgeNum = judgeNum;
        this.qaNum = qaNum;
        this.totalScore = totalScore;
        this.selectTag = selectTag;
        this.judgeTag = judgeTag;
        this.qaTag = qaTag;
        this.duration = duration;
        this.startDate = startDate;
        this.paperId = paperId;
    }

    public TestOption() {
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Integer getTestOptionId() {
        return testOptionId;
    }

    public void setTestOptionId(Integer testOptionId) {
        this.testOptionId = testOptionId;
    }

    public String getTestOptionName() {
        return testOptionName;
    }

    public void setTestOptionName(String testOptionName) {
        this.testOptionName = testOptionName;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public Integer getSelectNum() {
        return selectNum;
    }

    public void setSelectNum(Integer selectNum) {
        this.selectNum = selectNum;
    }

    public Integer getJudgeNum() {
        return judgeNum;
    }

    public void setJudgeNum(Integer judgeNum) {
        this.judgeNum = judgeNum;
    }

    public Integer getQaNum() {
        return qaNum;
    }

    public void setQaNum(Integer qaNum) {
        this.qaNum = qaNum;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public String getSelectTag() {
        return selectTag;
    }

    public void setSelectTag(String selectTag) {
        this.selectTag = selectTag;
    }

    public String getJudgeTag() {
        return judgeTag;
    }

    public void setJudgeTag(String judgeTag) {
        this.judgeTag = judgeTag;
    }

    public String getQaTag() {
        return qaTag;
    }

    public void setQaTag(String qaTag) {
        this.qaTag = qaTag;
    }

    public Integer getDuration() {
        return duration;
    }


    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public void updateTestOption(Map params) {
        if (params.containsKey("testOptionId"))
            this.testOptionId= Integer.valueOf(params.get("testOptionId").toString());
        if(params.containsKey("testOptionName"))
            this.testOptionName=params.get("testOptionName").toString();
        if (params.containsKey("goal"))
            this.goal = Integer.valueOf(params.get("goal").toString());
        if (params.containsKey("startDate")){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = format.parse(params.get("startDate").toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.startDate = Timestamp.valueOf(format.format(date));
        }
        if (params.containsKey("selectNum"))
            this.selectNum = Integer.valueOf(params.get("selectNum").toString());
        if(params.containsKey("judgeNum"))
            this.judgeNum=Integer.valueOf(params.get("judgeNum").toString());
        if(params.containsKey("qaNum"))
            this.qaNum=Integer.valueOf(params.get("qaNum").toString());
        if(params.containsKey("totalScore"))
            this.totalScore=Integer.valueOf(params.get("totalScore").toString());
        if(params.containsKey("selectTag"))
            this.selectTag=params.get("selectTag").toString();
        if(params.containsKey("judgeTag"))
            this.judgeTag=params.get("judgeTag").toString();
        if(params.containsKey("qaTag"))
            this.qaTag=params.get("qaTag").toString();
        if(params.containsKey("duration"))
            this.duration=Integer.valueOf(params.get("duration").toString());
        if(params.containsKey("paperId"))
            this.paperId = Integer.valueOf(params.get("paperId").toString());
    }

}