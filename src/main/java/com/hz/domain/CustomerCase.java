package com.hz.domain;

import java.util.Date;

public class CustomerCase {
    private Integer id;

    private Integer parentId;

    private String title;

    private Integer moduleId;

    private Integer industryId;

    private String customerName;

    private Integer purposeId;

    private String purposeCol1;

    private String purposeCol2;

    private String purposeCol3;

    private String pinpaiStory;

    private String pinpaiActuality;

    private String customerSay;

    private String marketPlan;

    private String effectPrediction;

    private String status;

    private Integer proposalId;

    private Integer createrId;

    private Date createTime;

    private Integer updaterId;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public Integer getPurposeId() {
        return purposeId;
    }

    public void setPurposeId(Integer purposeId) {
        this.purposeId = purposeId;
    }

    public String getPurposeCol1() {
        return purposeCol1;
    }

    public void setPurposeCol1(String purposeCol1) {
        this.purposeCol1 = purposeCol1 == null ? null : purposeCol1.trim();
    }

    public String getPurposeCol2() {
        return purposeCol2;
    }

    public void setPurposeCol2(String purposeCol2) {
        this.purposeCol2 = purposeCol2 == null ? null : purposeCol2.trim();
    }

    public String getPurposeCol3() {
        return purposeCol3;
    }

    public void setPurposeCol3(String purposeCol3) {
        this.purposeCol3 = purposeCol3 == null ? null : purposeCol3.trim();
    }

    public String getPinpaiStory() {
        return pinpaiStory;
    }

    public void setPinpaiStory(String pinpaiStory) {
        this.pinpaiStory = pinpaiStory == null ? null : pinpaiStory.trim();
    }

    public String getPinpaiActuality() {
        return pinpaiActuality;
    }

    public void setPinpaiActuality(String pinpaiActuality) {
        this.pinpaiActuality = pinpaiActuality == null ? null : pinpaiActuality.trim();
    }

    public String getCustomerSay() {
        return customerSay;
    }

    public void setCustomerSay(String customerSay) {
        this.customerSay = customerSay == null ? null : customerSay.trim();
    }

    public String getMarketPlan() {
        return marketPlan;
    }

    public void setMarketPlan(String marketPlan) {
        this.marketPlan = marketPlan == null ? null : marketPlan.trim();
    }

    public String getEffectPrediction() {
        return effectPrediction;
    }

    public void setEffectPrediction(String effectPrediction) {
        this.effectPrediction = effectPrediction == null ? null : effectPrediction.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getProposalId() {
        return proposalId;
    }

    public void setProposalId(Integer proposalId) {
        this.proposalId = proposalId;
    }

    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Integer updaterId) {
        this.updaterId = updaterId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}