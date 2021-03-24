package edu.sdp.project.pethospital.entity;

public class Case {
    private Integer caseid;

    private String casename;

    private String caseconsult;

    private String casediag;

    private String casetherapy;

    public Case(Integer caseid, String casename, String caseconsult, String casediag, String casetherapy) {
        this.caseid = caseid;
        this.casename = casename;
        this.caseconsult = caseconsult;
        this.casediag = casediag;
        this.casetherapy = casetherapy;
    }

    public Case() {
        super();
    }

    public Integer getCaseid() {
        return caseid;
    }

    public void setCaseid(Integer caseid) {
        this.caseid = caseid;
    }

    public String getCasename() {
        return casename;
    }

    public void setCasename(String casename) {
        this.casename = casename == null ? null : casename.trim();
    }

    public String getCaseconsult() {
        return caseconsult;
    }

    public void setCaseconsult(String caseconsult) {
        this.caseconsult = caseconsult == null ? null : caseconsult.trim();
    }

    public String getCasediag() {
        return casediag;
    }

    public void setCasediag(String casediag) {
        this.casediag = casediag == null ? null : casediag.trim();
    }

    public String getCasetherapy() {
        return casetherapy;
    }

    public void setCasetherapy(String casetherapy) {
        this.casetherapy = casetherapy == null ? null : casetherapy.trim();
    }
}