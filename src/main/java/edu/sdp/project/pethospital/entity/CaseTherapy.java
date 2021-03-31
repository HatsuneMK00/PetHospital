package edu.sdp.project.pethospital.entity;

import java.util.Map;

public class CaseTherapy {
    private Integer caseTherapyId;

    private String therapyDescrip;

    private String therapyImageUrl;

    private String therapyVideoUrl;

    public CaseTherapy(Integer caseTherapyId, String therapyDescrip, String therapyImageUrl, String therapyVideoUrl) {
        this.caseTherapyId = caseTherapyId;
        this.therapyDescrip = therapyDescrip;
        this.therapyImageUrl = therapyImageUrl;
        this.therapyVideoUrl = therapyVideoUrl;
    }

    public CaseTherapy() {
        super();
    }

    public Integer getCaseTherapyId() {
        return caseTherapyId;
    }

    public void setCaseTherapyId(Integer caseTherapyId) {
        this.caseTherapyId = caseTherapyId;
    }

    public String getTherapyDescrip() {
        return therapyDescrip;
    }

    public void setTherapyDescrip(String therapyDescrip) {
        this.therapyDescrip = therapyDescrip;
    }

    public String getTherapyImageUrl() {
        return therapyImageUrl;
    }

    public void setTherapyImageUrl(String therapyImageUrl) {
        this.therapyImageUrl = therapyImageUrl;
    }

    public String getTherapyVideoUrl() {
        return therapyVideoUrl;
    }

    public void setTherapyVideoUrl(String therapyVideoUrl) {
        this.therapyVideoUrl = therapyVideoUrl;
    }
    public void updateCase(Map params) {
        if (params.containsKey("caseTherapyId"))
            this.caseTherapyId = Integer.valueOf(params.get("caseTherapyId").toString());
        if (params.containsKey("therapyDescrip"))
            this.therapyDescrip = params.get("therapyDescrip").toString();
        if (params.containsKey("therapyImageUrl"))
            this.therapyImageUrl = params.get("therapyImageUrl").toString();
        if (params.containsKey("therapyVideoUrl"))
            this.therapyVideoUrl = params.get("therapyVideoUrl").toString();
    }
}