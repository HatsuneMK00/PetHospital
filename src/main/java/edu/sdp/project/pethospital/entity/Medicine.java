package edu.sdp.project.pethospital.entity;

public class Medicine {
    private Integer medid;

    private String medname;

    private String meddescrip;

    public Medicine(Integer medid, String medname, String meddescrip) {
        this.medid = medid;
        this.medname = medname;
        this.meddescrip = meddescrip;
    }

    public Medicine() {
        super();
    }

    public Integer getMedid() {
        return medid;
    }

    public void setMedid(Integer medid) {
        this.medid = medid;
    }

    public String getMedname() {
        return medname;
    }

    public void setMedname(String medname) {
        this.medname = medname == null ? null : medname.trim();
    }

    public String getMeddescrip() {
        return meddescrip;
    }

    public void setMeddescrip(String meddescrip) {
        this.meddescrip = meddescrip == null ? null : meddescrip.trim();
    }
}