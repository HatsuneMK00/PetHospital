package edu.sdp.project.pethospital.entity;

public class CasePlan {
    private Integer caseplanid;

    private String plandescrip;

    private String planimageurl;

    private String planvideourl;

    public CasePlan(Integer caseplanid, String plandescrip, String planimageurl, String planvideourl) {
        this.caseplanid = caseplanid;
        this.plandescrip = plandescrip;
        this.planimageurl = planimageurl;
        this.planvideourl = planvideourl;
    }

    public CasePlan() {
        super();
    }

    public Integer getCaseplanid() {
        return caseplanid;
    }

    public void setCaseplanid(Integer caseplanid) {
        this.caseplanid = caseplanid;
    }

    public String getPlandescrip() {
        return plandescrip;
    }

    public void setPlandescrip(String plandescrip) {
        this.plandescrip = plandescrip == null ? null : plandescrip.trim();
    }

    public String getPlanimageurl() {
        return planimageurl;
    }

    public void setPlanimageurl(String planimageurl) {
        this.planimageurl = planimageurl == null ? null : planimageurl.trim();
    }

    public String getPlanvideourl() {
        return planvideourl;
    }

    public void setPlanvideourl(String planvideourl) {
        this.planvideourl = planvideourl == null ? null : planvideourl.trim();
    }
}