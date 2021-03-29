package edu.sdp.project.pethospital.entity;

import java.util.Map;

public class Exam {
    private Integer examId;

    private String examName;

    private String examDescrip;

    public Exam(Integer examId, String examName, String examDescrip) {
        this.examId = examId;
        this.examName = examName;
        this.examDescrip = examDescrip;
    }

    public Exam() {
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamDescrip() {
        return examDescrip;
    }

    public void setExamDescrip(String examDescrip) {
        this.examDescrip = examDescrip;
    }
    public void updateExam(Map params){
        if(params.containsKey("examId"))
            this.examId=Integer.valueOf(params.get("examId").toString());
        if(params.containsKey("examName"))
            this.examName=params.get("examName").toString();
        if(params.containsKey("examDescrip"))
            this.examDescrip=params.get("examDescrip").toString();
    }
}