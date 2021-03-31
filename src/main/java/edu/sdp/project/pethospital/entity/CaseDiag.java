package edu.sdp.project.pethospital.entity;

import java.util.Map;

public class CaseDiag {
    private Integer caseDiagId;

    private String diagDescrip;

    private String diagImageUrl;

    private String diagVideoUrl;

    public CaseDiag(Integer caseDiagId, String diagDescrip, String diagImageUrl, String diagVideoUrl) {
        this.caseDiagId = caseDiagId;
        this.diagDescrip = diagDescrip;
        this.diagImageUrl = diagImageUrl;
        this.diagVideoUrl = diagVideoUrl;
    }

    public CaseDiag() {
    }

    public Integer getCaseDiagId() {
        return caseDiagId;
    }

    public void setCaseDiagId(Integer caseDiagId) {
        this.caseDiagId = caseDiagId;
    }

    public String getDiagDescrip() {
        return diagDescrip;
    }

    public void setDiagDescrip(String diagDescrip) {
        this.diagDescrip = diagDescrip;
    }

    public String getDiagImageUrl() {
        return diagImageUrl;
    }

    public void setDiagImageUrl(String diagImageUrl) {
        this.diagImageUrl = diagImageUrl;
    }

    public String getDiagVideoUrl() {
        return diagVideoUrl;
    }

    public void setDiagVideoUrl(String diagVideoUrl) {
        this.diagVideoUrl = diagVideoUrl;
    }

    public void updateCase(Map params) {
        if (params.containsKey("caseDiagId"))
            this.caseDiagId = Integer.valueOf(params.get("caseDiagId").toString());
        if (params.containsKey("diagDescrip"))
            this.diagDescrip = params.get("diagDescrip").toString();
        if (params.containsKey("diagImageUrl"))
            this.diagImageUrl = params.get("diagImageUrl").toString();
        if (params.containsKey("diagVideoUrl"))
            this.diagVideoUrl = params.get("diagVideoUrl").toString();
    }
}