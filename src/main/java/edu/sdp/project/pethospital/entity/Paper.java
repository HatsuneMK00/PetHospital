package edu.sdp.project.pethospital.entity;

public class Paper {
    private Integer paperId;
    private String paperName;

    public Paper(Integer paperId, String paperName) {
        this.paperId = paperId;
        this.paperName = paperName;
    }

    public Paper() {
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }
}
