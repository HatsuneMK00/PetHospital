package edu.sdp.project.pethospital.entity;

public class TestOption {
    private int testOptionId;
    private String testOptionName;
    private int goal;
    private int selectNum;
    private int judgeNum;
    private int qaNum;
    private int totalNum;
    private String varTag;
    private String judgeTag;
    private String qaTag;
    private long duration;

    public TestOption() {
    }

    public TestOption(int testOptionId, String testOptionName, int goal, int selectNum, int judgeNum, int qaNum, int totalNum, String varTag, String judgeTag, String qaTag, long duration) {
        this.testOptionId = testOptionId;
        this.testOptionName = testOptionName;
        this.goal = goal;
        this.selectNum = selectNum;
        this.judgeNum = judgeNum;
        this.qaNum = qaNum;
        this.totalNum = totalNum;
        this.varTag = varTag;
        this.judgeTag = judgeTag;
        this.qaTag = qaTag;
        this.duration = duration;
    }

    public int getTestOptionId() {
        return testOptionId;
    }

    public void setTestOptionId(int testOptionId) {
        this.testOptionId = testOptionId;
    }

    public String getTestOptionName() {
        return testOptionName;
    }

    public void setTestOptionName(String testOptionName) {
        this.testOptionName = testOptionName;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getSelectNum() {
        return selectNum;
    }

    public void setSelectNum(int selectNum) {
        this.selectNum = selectNum;
    }

    public int getJudgeNum() {
        return judgeNum;
    }

    public void setJudgeNum(int judgeNum) {
        this.judgeNum = judgeNum;
    }

    public int getQaNum() {
        return qaNum;
    }

    public void setQaNum(int qaNum) {
        this.qaNum = qaNum;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public String getVarTag() {
        return varTag;
    }

    public void setVarTag(String varTag) {
        this.varTag = varTag;
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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
