package edu.sdp.project.pethospital.entity;

public class TestKey {
    private Integer testid;

    private Integer userid;

    private Integer testoptionid;

    public TestKey(Integer testid, Integer userid, Integer testoptionid) {
        this.testid = testid;
        this.userid = userid;
        this.testoptionid = testoptionid;
    }

    public TestKey() {
        super();
    }

    public Integer getTestid() {
        return testid;
    }

    public void setTestid(Integer testid) {
        this.testid = testid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getTestoptionid() {
        return testoptionid;
    }

    public void setTestoptionid(Integer testoptionid) {
        this.testoptionid = testoptionid;
    }
}