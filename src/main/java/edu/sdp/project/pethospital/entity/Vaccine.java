package edu.sdp.project.pethospital.entity;

import java.util.Map;

public class Vaccine {
    private Integer vacId;

    private String vacName;

    private String vacDescrip;

    public Vaccine(Integer vacId, String vacName, String vacDescrip) {
        this.vacId = vacId;
        this.vacName = vacName;
        this.vacDescrip = vacDescrip;
    }

    public Vaccine() {
    }

    public Integer getVacId() {
        return vacId;
    }

    public void setVacId(Integer vacId) {
        this.vacId = vacId;
    }

    public String getVacName() {
        return vacName;
    }

    public void setVacName(String vacName) {
        this.vacName = vacName;
    }

    public String getVacDescrip() {
        return vacDescrip;
    }

    public void setVacDescrip(String vacDescrip) {
        this.vacDescrip = vacDescrip;
    }
    public void updateVaccine(Map params){
        if(params.containsKey("vacId"))
            this.vacId=Integer.valueOf(params.get("vacId").toString());
        if(params.containsKey("vacName"))
            this.vacName=params.get("vacName").toString();
        if(params.containsKey("vacDescrip"))
            this.vacDescrip=params.get("vacDescrip").toString();
    }
}