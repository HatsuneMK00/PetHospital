package edu.sdp.project.pethospital.entity;

public class Question {
    private int quesId;
    private String type;
    private String descrip;
    private String answer;
    private int score;
    private String image;
    private String tag;

    public Question() {
    }

    public Question(int quesId, String type, String descrip, String answer, int score, String image, String tag) {
        this.quesId = quesId;
        this.type = type;
        this.descrip = descrip;
        this.answer = answer;
        this.score = score;
        this.image = image;
        this.tag = tag;
    }

    public int getQuesId() {
        return quesId;
    }

    public void setQuesId(int quesId) {
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
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
}
