package edu.sdp.project.pethospital.entity;

public class TestOption {
    private Integer testoptionid;

    private String testoptionname;

    private Integer goal;

    private Integer selectnum;

    private Integer judgenum;

    private Integer qanum;

    private Integer totalscore;

    private String selecttag;

    private String judgetag;

    private String qatag;

    private Integer duration;

    public TestOption(Integer testoptionid, String testoptionname, Integer goal, Integer selectnum, Integer judgenum, Integer qanum, Integer totalscore, String selecttag, String judgetag, String qatag, Integer duration) {
        this.testoptionid = testoptionid;
        this.testoptionname = testoptionname;
        this.goal = goal;
        this.selectnum = selectnum;
        this.judgenum = judgenum;
        this.qanum = qanum;
        this.totalscore = totalscore;
        this.selecttag = selecttag;
        this.judgetag = judgetag;
        this.qatag = qatag;
        this.duration = duration;
    }

    public TestOption() {
        super();
    }

    public Integer getTestoptionid() {
        return testoptionid;
    }

    public void setTestoptionid(Integer testoptionid) {
        this.testoptionid = testoptionid;
    }

    public String getTestoptionname() {
        return testoptionname;
    }

    public void setTestoptionname(String testoptionname) {
        this.testoptionname = testoptionname == null ? null : testoptionname.trim();
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public Integer getSelectnum() {
        return selectnum;
    }

    public void setSelectnum(Integer selectnum) {
        this.selectnum = selectnum;
    }

    public Integer getJudgenum() {
        return judgenum;
    }

    public void setJudgenum(Integer judgenum) {
        this.judgenum = judgenum;
    }

    public Integer getQanum() {
        return qanum;
    }

    public void setQanum(Integer qanum) {
        this.qanum = qanum;
    }

    public Integer getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(Integer totalscore) {
        this.totalscore = totalscore;
    }

    public String getSelecttag() {
        return selecttag;
    }

    public void setSelecttag(String selecttag) {
        this.selecttag = selecttag == null ? null : selecttag.trim();
    }

    public String getJudgetag() {
        return judgetag;
    }

    public void setJudgetag(String judgetag) {
        this.judgetag = judgetag == null ? null : judgetag.trim();
    }

    public String getQatag() {
        return qatag;
    }

    public void setQatag(String qatag) {
        this.qatag = qatag == null ? null : qatag.trim();
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}