package edu.sdp.project.pethospital.entity;

public class Question {
    private Integer quesid;

    private String type;

    private String descrip;

    private String answer;

    private Integer score;

    private String image;

    private String tag;

    public Question(Integer quesid, String type, String descrip, String answer, Integer score, String image, String tag) {
        this.quesid = quesid;
        this.type = type;
        this.descrip = descrip;
        this.answer = answer;
        this.score = score;
        this.image = image;
        this.tag = tag;
    }

    public Question() {
        super();
    }

    public Integer getQuesid() {
        return quesid;
    }

    public void setQuesid(Integer quesid) {
        this.quesid = quesid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip == null ? null : descrip.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
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
        this.image = image == null ? null : image.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }
}