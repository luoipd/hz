package com.hz.domain;

import com.hz.domain.responseBean.ProposalModuleBean;

import java.util.Date;
import java.util.List;

public class AdvertisingProposal {
    private Integer id;

    private Integer industryId;

    private String proposalName;

    private Integer customerId;

    private String customerName;

    private String desc;

    private String lightExposure;

    private String pv;

    private String version;

    private Integer createrId;

    private Date createTime;

    private Integer updaterId;

    private Date updateTime;

    private Integer themeId;

    private List<ProposalModuleBean> proposalModuleBeans;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getProposalName() {
        return proposalName;
    }

    public void setProposalName(String proposalName) {
        this.proposalName = proposalName == null ? null : proposalName.trim();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getLightExposure() {
        return lightExposure;
    }

    public void setLightExposure(String lightExposure) {
        this.lightExposure = lightExposure == null ? null : lightExposure.trim();
    }

    public String getPv() {
        return pv;
    }

    public void setPv(String pv) {
        this.pv = pv == null ? null : pv.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
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

    public List<ProposalModuleBean> getProposalModuleBeans() {
        return proposalModuleBeans;
    }

    public void setProposalModuleBeans(List<ProposalModuleBean> proposalModuleBeans) {
        this.proposalModuleBeans = proposalModuleBeans;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }
}