package com.hz.domain;

import java.util.Date;
import java.util.List;

public class Home {
    private Integer id;

    private String title;

    private String content;

    private Integer moduleId;

    private String moduleName;

    private Integer status;

    private Integer proposalId;

    private Integer createrId;

    private Date createTime;

    private Integer updaterId;

    private Date updateTime;

    private ContactUs contactUs;

    private CustomerCase customerCase;

    private List<PictureVideo> pictureVideos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public List<PictureVideo> getPictureVideos() {
        return pictureVideos;
    }

    public void setPictureVideos(List<PictureVideo> pictureVideos) {
        this.pictureVideos = pictureVideos;
    }

    public ContactUs getContactUs() {
        return contactUs;
    }

    public void setContactUs(ContactUs contactUs) {
        this.contactUs = contactUs;
    }

    public CustomerCase getCustomerCase() {
        return customerCase;
    }

    public void setCustomerCase(CustomerCase customerCase) {
        this.customerCase = customerCase;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}