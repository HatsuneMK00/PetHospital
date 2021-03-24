package edu.sdp.project.pethospital.entity;

import java.math.BigDecimal;

public class Fee {
    private Integer feeid;

    private String feename;

    private BigDecimal feeprice;

    private String feedescrip;

    public Fee(Integer feeid, String feename, BigDecimal feeprice, String feedescrip) {
        this.feeid = feeid;
        this.feename = feename;
        this.feeprice = feeprice;
        this.feedescrip = feedescrip;
    }

    public Fee() {
        super();
    }

    public Integer getFeeid() {
        return feeid;
    }

    public void setFeeid(Integer feeid) {
        this.feeid = feeid;
    }

    public String getFeename() {
        return feename;
    }

    public void setFeename(String feename) {
        this.feename = feename == null ? null : feename.trim();
    }

    public BigDecimal getFeeprice() {
        return feeprice;
    }

    public void setFeeprice(BigDecimal feeprice) {
        this.feeprice = feeprice;
    }

    public String getFeedescrip() {
        return feedescrip;
    }

    public void setFeedescrip(String feedescrip) {
        this.feedescrip = feedescrip == null ? null : feedescrip.trim();
    }
}