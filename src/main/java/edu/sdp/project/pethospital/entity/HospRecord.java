package edu.sdp.project.pethospital.entity;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class HospRecord {
    private Integer hosId;

    private String hosAnimalName;

    private String disease;

    private Timestamp inDate;
    private Timestamp outDate;

    public HospRecord(Integer hosId, String hosAnimalName, String disease, Timestamp inDate, Timestamp outDate) {
        this.hosId = hosId;
        this.hosAnimalName = hosAnimalName;
        this.disease = disease;
        this.inDate = inDate;
        this.outDate = outDate;
    }

    public HospRecord() {
    }

    public Integer getHosId() {
        return hosId;
    }

    public void setHosId(Integer hosId) {
        this.hosId = hosId;
    }

    public String getHosAnimalName() {
        return hosAnimalName;
    }

    public void setHosAnimalName(String hosAnimalName) {
        this.hosAnimalName = hosAnimalName;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Timestamp getInDate() {
        return inDate;
    }

    public void setInDate(Timestamp inDate) {
        this.inDate = inDate;
    }

    public Timestamp getOutDate() {
        return outDate;
    }

    public void setOutDate(Timestamp outDate) {
        this.outDate = outDate;
    }

    public void updateHospRecord(Map param){
        if(param.containsKey("hosAnimalName"))
            this.hosAnimalName=param.get("hosAnimalName").toString();
        if(param.containsKey("disease"))
            this.disease=param.get("disease").toString();
        if(param.containsKey("inDate")){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = format.parse(param.get("inDate").toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.inDate = Timestamp.valueOf(format.format(date));
        }
        if(param.containsKey("outDate")){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = format.parse(param.get("outDate").toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.outDate = Timestamp.valueOf(format.format(date));
        }
    }
}