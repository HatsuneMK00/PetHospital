package edu.sdp.project.pethospital.entity;

public class CaseResult {
    private Integer caseresultid;

    private String resultdescrip;

    private String resultimageurl;

    private String resultvideourl;

    public CaseResult(Integer caseresultid, String resultdescrip, String resultimageurl, String resultvideourl) {
        this.caseresultid = caseresultid;
        this.resultdescrip = resultdescrip;
        this.resultimageurl = resultimageurl;
        this.resultvideourl = resultvideourl;
    }

    public CaseResult() {
        super();
    }

    public Integer getCaseresultid() {
        return caseresultid;
    }

    public void setCaseresultid(Integer caseresultid) {
        this.caseresultid = caseresultid;
    }

    public String getResultdescrip() {
        return resultdescrip;
    }

    public void setResultdescrip(String resultdescrip) {
        this.resultdescrip = resultdescrip == null ? null : resultdescrip.trim();
    }

    public String getResultimageurl() {
        return resultimageurl;
    }

    public void setResultimageurl(String resultimageurl) {
        this.resultimageurl = resultimageurl == null ? null : resultimageurl.trim();
    }

    public String getResultvideourl() {
        return resultvideourl;
    }

    public void setResultvideourl(String resultvideourl) {
        this.resultvideourl = resultvideourl == null ? null : resultvideourl.trim();
    }
}