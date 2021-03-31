package edu.sdp.project.pethospital.entity;

import java.util.Map;

public class Question {
    private Integer quesId;

    private String type;

    private String descrip;

    private String answer;

    private Integer score;

    private String image;

    private String tag;

    public Question(Integer quesId, String type, String descrip, String answer, Integer score, String image, String tag) {
        this.quesId = quesId;
        this.type = type;
        this.descrip = descrip;
        this.answer = answer;
        this.score = score;
        this.image = image;
        this.tag = tag;
    }

    public Question() {
    }

    public Integer getQuesId() {
        return quesId;
    }

    public void setQuesId(Integer quesId) {
        this.quesId = quesId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void updateQuestion(Map params) {
        if (params.containsKey("quesId"))
            this.quesId= Integer.valueOf(params.get("quesId").toString());
        if (params.containsKey("type"))
            this.type = params.get("type").toString();
        if (params.containsKey("descrip"))
            this.descrip = params.get("descrip").toString();
        if (params.containsKey("answer"))
            this.answer = params.get("answer").toString();
        if(params.containsKey("score"))
            this.score=Integer.valueOf(params.get("score").toString());
        if(params.containsKey("image"))
            this.image=params.get("image").toString();
        if(params.containsKey("tag"))
            this.tag=params.get("tag").toString();
    }
}