package edu.sdp.project.pethospital.entity;

public class HospRecord {
    private Integer hosid;

    private String hosanimalname;

    private String disease;

    public HospRecord(Integer hosid, String hosanimalname, String disease) {
        this.hosid = hosid;
        this.hosanimalname = hosanimalname;
        this.disease = disease;
    }

    public HospRecord() {
        super();
    }

    public Integer getHosid() {
        return hosid;
    }

    public void setHosid(Integer hosid) {
        this.hosid = hosid;
    }

    public String getHosanimalname() {
        return hosanimalname;
    }

    public void setHosanimalname(String hosanimalname) {
        this.hosanimalname = hosanimalname == null ? null : hosanimalname.trim();
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease == null ? null : disease.trim();
    }
}