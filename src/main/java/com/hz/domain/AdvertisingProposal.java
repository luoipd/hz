package com.hz.domain;

import com.hz.domain.responseBean.ProposalModuleBean;

import java.util.Date;
import java.util.List;

public class AdvertisingProposal {
    private Integer id;

    private Integer industryId;

    private String industryName;

    private String proposalName;

    private Integer customerId;

    private String customerName;

    private String desc;

    private Integer purposeId;

    private String purposeName;

    private String purposeCol1;

    private String purposeCol2;

    private String purposeCol3;

    private String version;

    private Integer createrId;

    private List<Integer> createrIds;

    private Date createTime;

    private Integer updaterId;

    private Date updateTime;

    private Integer themeId;

    private Integer picId;

    private String url;

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
        this.purposeCol1 = purposeCol1;
    }

    public String getPurposeCol2() {
        return purposeCol2;
    }

    public void setPurposeCol2(String purposeCol2) {
        this.purposeCol2 = purposeCol2;
    }

    public String getPurposeCol3() {
        return purposeCol3;
    }

    public void setPurposeCol3(String purposeCol3) {
        this.purposeCol3 = purposeCol3;
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

    public Integer getPicId() {
        return picId;
    }

    public void setPicId(Integer picId) {
        this.picId = picId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getPurposeName() {
        return purposeName;
    }

    public void setPurposeName(String purposeName) {
        this.purposeName = purposeName;
    }

    public List<Integer> getCreaterIds() {
        return createrIds;
    }

    public void setCreaterIds(List<Integer> createrIds) {
        this.createrIds = createrIds;
    }
}