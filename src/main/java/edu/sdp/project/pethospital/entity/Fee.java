package edu.sdp.project.pethospital.entity;

import java.math.BigDecimal;
import java.util.Map;

public class Fee {
    private Integer feeId;

    private String feeName;

    private Double feePrice;

    private String feeDescrip;

    public Fee(Integer feeId, String feeName, Double feePrice, String feeDescrip) {
        this.feeId = feeId;
        this.feeName = feeName;
        this.feePrice = feePrice;
        this.feeDescrip = feeDescrip;
    }

    public Fee() {
    }

    public Integer getFeeId() {
        return feeId;
    }

    public void setFeeId(Integer feeId) {
        this.feeId = feeId;
    }

    public String getFeeName() {
        return feeName;
    }

    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    public double getFeePrice() {
        return feePrice;
    }

    public void setFeePrice(Double feePrice) {
        this.feePrice = feePrice;
    }

    public String getFeeDescrip() {
        return feeDescrip;
    }

    public void setFeeDescrip(String feeDescrip) {
        this.feeDescrip = feeDescrip;
    }

    public void updateFee(Map params){
        if(params.containsKey(params.containsKey("feeId")))
            this.feeId=Integer.valueOf(params.get("feeId").toString());
        if(params.containsKey(params.containsKey("feeName")))
            this.feeName=params.get("feeName").toString();
        if(params.containsKey("feePrice"))
            this.feePrice=Double.valueOf(params.get("feePrice").toString());
        if(params.containsKey("feeDescrip"))
            this.feeDescrip=params.get("feeDescrip").toString();
    }
}