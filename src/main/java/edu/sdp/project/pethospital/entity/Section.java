package edu.sdp.project.pethospital.entity;

public class Section {
    private Integer sectionid;

    private String sectionname;

    private String recdescrip;

    private String docdescrip;

    private String asisdescrip;

    private String sectionimageurl;

    public Section(Integer sectionid, String sectionname, String recdescrip, String docdescrip, String asisdescrip, String sectionimageurl) {
        this.sectionid = sectionid;
        this.sectionname = sectionname;
        this.recdescrip = recdescrip;
        this.docdescrip = docdescrip;
        this.asisdescrip = asisdescrip;
        this.sectionimageurl = sectionimageurl;
    }

    public Section() {
        super();
    }

    public Integer getSectionid() {
        return sectionid;
    }

    public void setSectionid(Integer sectionid) {
        this.sectionid = sectionid;
    }

    public String getSectionname() {
        return sectionname;
    }

    public void setSectionname(String sectionname) {
        this.sectionname = sectionname == null ? null : sectionname.trim();
    }

    public String getRecdescrip() {
        return recdescrip;
    }

    public void setRecdescrip(String recdescrip) {
        this.recdescrip = recdescrip == null ? null : recdescrip.trim();
    }

    public String getDocdescrip() {
        return docdescrip;
    }

    public void setDocdescrip(String docdescrip) {
        this.docdescrip = docdescrip == null ? null : docdescrip.trim();
    }

    public String getAsisdescrip() {
        return asisdescrip;
    }

    public void setAsisdescrip(String asisdescrip) {
        this.asisdescrip = asisdescrip == null ? null : asisdescrip.trim();
    }

    public String getSectionimageurl() {
        return sectionimageurl;
    }

    public void setSectionimageurl(String sectionimageurl) {
        this.sectionimageurl = sectionimageurl == null ? null : sectionimageurl.trim();
    }
}