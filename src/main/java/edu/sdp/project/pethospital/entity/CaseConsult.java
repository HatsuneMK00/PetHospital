package edu.sdp.project.pethospital.entity;

import java.util.Map;

public class CaseConsult {
    private Integer caseConsultId;

    private String consultDescrip;

    private String consultImageUrl;

    private String consultVideoUrl;

    public CaseConsult(Integer caseConsultId, String consultDescrip, String consultImageUrl, String consultVideoUrl) {
        this.caseConsultId = caseConsultId;
        this.consultDescrip = consultDescrip;
        this.consultImageUrl = consultImageUrl;
        this.consultVideoUrl = consultVideoUrl;
    }

    public CaseConsult() {
        super();
    }

    public Integer getCaseConsultId() {
        return caseConsultId;
    }

    public void setCaseConsultId(Integer caseConsultId) {
        this.caseConsultId = caseConsultId;
    }

    public String getConsultDescrip() {
        return consultDescrip;
    }

    public void setConsultDescrip(String consultDescrip) {
        this.consultDescrip = consultDescrip;
    }

    public String getConsultImageUrl() {
        return consultImageUrl;
    }

    public void setConsultImageUrl(String consultImageUrl) {
        this.consultImageUrl = consultImageUrl;
    }

    public String getConsultVideoUrl() {
        return consultVideoUrl;
    }

    public void setConsultVideoUrl(String consultVideoUrl) {
        this.consultVideoUrl = consultVideoUrl;
    }

    public void updateCaseConsult(Map params) {
        if (params.containsKey("caseConsultId"))
            this.caseConsultId= Integer.valueOf(params.get("caseConsultId").toString());
        if (params.containsKey("consultDescrip"))
            this.consultDescrip = params.get("consultDescrip").toString();
        if (params.containsKey("consultImageUrl"))
            this.consultImageUrl = params.get("consultImageUrl").toString();
        if (params.containsKey("consultVideoUrl"))
            this.consultVideoUrl = params.get("consultImageUrl").toString();
    }
}