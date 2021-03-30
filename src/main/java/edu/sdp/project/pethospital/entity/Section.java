package edu.sdp.project.pethospital.entity;

import java.util.Map;

public class Section {
    private Integer sectionId;

    private String sectionName;

    private String recDescrip;

    private String docDescrip;

    private String assisDescrip;

    private String sectionImageUrl;

    public Section(Integer sectionId, String sectionName, String recDescrip, String docDescrip, String assisDescrip, String sectionImageUrl) {
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.recDescrip = recDescrip;
        this.docDescrip = docDescrip;
        this.assisDescrip = assisDescrip;
        this.sectionImageUrl = sectionImageUrl;
    }

    public Section() {
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getRecDescrip() {
        return recDescrip;
    }

    public void setRecDescrip(String recDescrip) {
        this.recDescrip = recDescrip;
    }

    public String getDocDescrip() {
        return docDescrip;
    }

    public void setDocDescrip(String docDescrip) {
        this.docDescrip = docDescrip;
    }

    public String getAssisDescrip() {
        return assisDescrip;
    }

    public void setAssisDescrip(String assisDescrip) {
        this.assisDescrip = assisDescrip;
    }

    public String getSectionImageUrl() {
        return sectionImageUrl;
    }

    public void setSectionImageUrl(String sectionImageUrl) {
        this.sectionImageUrl = sectionImageUrl;
    }
    public void updateSection(Map param){
        if(param.containsKey("sectionId"))
            this.sectionId=Integer.valueOf(param.get("sectionId").toString());
        if(param.containsKey("sectionName"))
            this.sectionName=param.get("sectionName").toString();
        if(param.containsKey("recDescrip"))
            this.recDescrip=param.get("recDescrip").toString();
        if(param.containsKey("docDescrip"))
            this.docDescrip=param.get("docDescrip").toString();
        if(param.containsKey("assisDescrip"))
            this.assisDescrip=param.get("assisDescrip").toString();
        if(param.containsKey("sectionImageUrl"))
            this.sectionImageUrl=param.get("sectionImageUrl").toString();
    }
}