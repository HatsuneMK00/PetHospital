package edu.sdp.project.pethospital.entity;

import java.util.Map;

public class Medicine {
    private Integer medId;

    private String medName;

    private String medDescrip;

    public Medicine(Integer medId, String medName, String medDescrip) {
        this.medId = medId;
        this.medName = medName;
        this.medDescrip = medDescrip;
    }

    public Medicine() {
    }

    public Integer getMedId() {
        return medId;
    }

    public void setMedId(Integer medId) {
        this.medId = medId;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getMedDescrip() {
        return medDescrip;
    }

    public void setMedDescrip(String medDescrip) {
        this.medDescrip = medDescrip;
    }
    public void updateMedicine(Map params){
        if(params.containsKey("medId"))
            this.medId=Integer.valueOf(params.get("medId").toString());
        if(params.containsKey("medName"))
            this.medName=params.get("medName").toString();
        if(params.containsKey("medDescrip"))
            this.medDescrip=params.get("medDescrip").toString();
    }
}