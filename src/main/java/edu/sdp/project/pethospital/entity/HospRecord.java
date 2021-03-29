package edu.sdp.project.pethospital.entity;

import java.util.Map;

public class HospRecord {
    private Integer hosId;

    private String hosAnimalName;

    private String disease;

    public HospRecord(Integer hosId, String hosAnimalName, String disease) {
        this.hosId = hosId;
        this.hosAnimalName = hosAnimalName;
        this.disease = disease;
    }

    public HospRecord() {
    }

    public Integer getHosId() {
        return hosId;
    }

    public void setHosId(Integer hosId) {
        this.hosId = hosId;
    }

    public String getHosAnimalName() {
        return hosAnimalName;
    }

    public void setHosAnimalName(String hosAnimalName) {
        this.hosAnimalName = hosAnimalName;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
    public void updateHospRecord(Map param){
        if(param.containsKey("hosAnimalName"))
            this.hosAnimalName=param.get("hosAnimalName").toString();
        if(param.containsKey("disease"))
            this.disease=param.get("disease").toString();
    }
}