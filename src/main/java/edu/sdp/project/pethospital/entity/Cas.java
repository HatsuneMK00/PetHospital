package edu.sdp.project.pethospital.entity;

import java.util.Map;

public class Cas {
    private Integer caseId;

    private String caseName;

    private int caseConsultId;

    private int caseDiagId;

    private int caseTherapyId;

    public Cas(Integer caseId, String caseName, int caseConsultId, int caseDiagId, int caseTherapyId) {
        this.caseId = caseId;
        this.caseName = caseName;
        this.caseConsultId = caseConsultId;
        this.caseDiagId = caseDiagId;
        this.caseTherapyId = caseTherapyId;
    }

    public Cas() {
        super();
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public int getCaseConsultId() {
        return caseConsultId;
    }

    public void setCaseConsultId(int caseConsultId) {
        this.caseConsultId = caseConsultId;
    }

    public int getCaseDiagId() {
        return caseDiagId;
    }

    public void setCaseDiagId(int caseDiagId) {
        this.caseDiagId = caseDiagId;
    }

    public int getCaseTherapyId() {
        return caseTherapyId;
    }

    public void setCaseTherapyId(int caseTherapyId) {
        this.caseTherapyId = caseTherapyId;
    }
    public void updateCase(Map params) {
        if (params.containsKey("caseId"))
            this.caseId = Integer.valueOf(params.get("caseId").toString());
        if (params.containsKey("caseName"))
            this.caseName = params.get("caseName").toString();
        if (params.containsKey("caseConsultId"))
            this.caseConsultId = Integer.parseInt(params.get("caseConsultId").toString());
        if (params.containsKey("caseDiagId"))
            this.caseDiagId = Integer.parseInt(params.get("caseDiagId").toString());
        if (params.containsKey("caseTherapyId"))
            this.caseTherapyId = Integer.parseInt(params.get("caseTherapyId").toString());
    }
}