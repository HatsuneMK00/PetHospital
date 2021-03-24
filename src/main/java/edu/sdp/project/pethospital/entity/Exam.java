package edu.sdp.project.pethospital.entity;

public class Exam {
    private Integer examid;

    private String examname;

    private String examdescrip;

    public Exam(Integer examid, String examname, String examdescrip) {
        this.examid = examid;
        this.examname = examname;
        this.examdescrip = examdescrip;
    }

    public Exam() {
        super();
    }

    public Integer getExamid() {
        return examid;
    }

    public void setExamid(Integer examid) {
        this.examid = examid;
    }

    public String getExamname() {
        return examname;
    }

    public void setExamname(String examname) {
        this.examname = examname == null ? null : examname.trim();
    }

    public String getExamdescrip() {
        return examdescrip;
    }

    public void setExamdescrip(String examdescrip) {
        this.examdescrip = examdescrip == null ? null : examdescrip.trim();
    }
}