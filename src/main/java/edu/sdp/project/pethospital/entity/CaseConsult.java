package edu.sdp.project.pethospital.entity;

public class CaseConsult {
    private Integer caseconsultid;

    private String consultdescrip;

    private String consultimageurl;

    private String consultvideourl;

    public CaseConsult(Integer caseconsultid, String consultdescrip, String consultimageurl, String consultvideourl) {
        this.caseconsultid = caseconsultid;
        this.consultdescrip = consultdescrip;
        this.consultimageurl = consultimageurl;
        this.consultvideourl = consultvideourl;
    }

    public CaseConsult() {
        super();
    }

    public Integer getCaseconsultid() {
        return caseconsultid;
    }

    public void setCaseconsultid(Integer caseconsultid) {
        this.caseconsultid = caseconsultid;
    }

    public String getConsultdescrip() {
        return consultdescrip;
    }

    public void setConsultdescrip(String consultdescrip) {
        this.consultdescrip = consultdescrip == null ? null : consultdescrip.trim();
    }

    public String getConsultimageurl() {
        return consultimageurl;
    }

    public void setConsultimageurl(String consultimageurl) {
        this.consultimageurl = consultimageurl == null ? null : consultimageurl.trim();
    }

    public String getConsultvideourl() {
        return consultvideourl;
    }

    public void setConsultvideourl(String consultvideourl) {
        this.consultvideourl = consultvideourl == null ? null : consultvideourl.trim();
    }
}