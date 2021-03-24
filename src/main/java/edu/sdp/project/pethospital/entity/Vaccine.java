package edu.sdp.project.pethospital.entity;

public class Vaccine {
    private Integer vacid;

    private String vacname;

    private String vacdescrip;

    public Vaccine(Integer vacid, String vacname, String vacdescrip) {
        this.vacid = vacid;
        this.vacname = vacname;
        this.vacdescrip = vacdescrip;
    }

    public Vaccine() {
        super();
    }

    public Integer getVacid() {
        return vacid;
    }

    public void setVacid(Integer vacid) {
        this.vacid = vacid;
    }

    public String getVacname() {
        return vacname;
    }

    public void setVacname(String vacname) {
        this.vacname = vacname == null ? null : vacname.trim();
    }

    public String getVacdescrip() {
        return vacdescrip;
    }

    public void setVacdescrip(String vacdescrip) {
        this.vacdescrip = vacdescrip == null ? null : vacdescrip.trim();
    }
}