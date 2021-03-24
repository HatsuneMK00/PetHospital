package edu.sdp.project.pethospital.entity;

import java.util.Date;

public class Test extends TestKey {
    private Date begindate;

    private Date enddate;

    private Integer score;

    public Test(Integer testid, Integer userid, Integer testoptionid, Date begindate, Date enddate, Integer score) {
        super(testid, userid, testoptionid);
        this.begindate = begindate;
        this.enddate = enddate;
        this.score = score;
    }

    public Test() {
        super();
    }

    public Date getBegindate() {
        return begindate;
    }

    public void setBegindate(Date begindate) {
        this.begindate = begindate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}