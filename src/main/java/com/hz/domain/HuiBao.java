package com.hz.domain;

import java.math.BigDecimal;
import java.util.Date;

public class HuiBao {
    private Integer id;

    private Integer parentId;

    private Integer picId;

    private String huibaoName;

    private String url;

    private String groupName;

    private String huibaoType;

    private String huibaoSite;

    private String huibaoDetail;

    private String per;

    private String number;

    private String price;

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

    public Integer getPicId() {
        return picId;
    }

    public void setPicId(Integer picId) {
        this.picId = picId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getHuibaoType() {
        return huibaoType;
    }

    public void setHuibaoType(String huibaoType) {
        this.huibaoType = huibaoType == null ? null : huibaoType.trim();
    }

    public String getHuibaoSite() {
        return huibaoSite;
    }

    public void setHuibaoSite(String huibaoSite) {
        this.huibaoSite = huibaoSite == null ? null : huibaoSite.trim();
    }

    public String getHuibaoDetail() {
        return huibaoDetail;
    }

    public void setHuibaoDetail(String huibaoDetail) {
        this.huibaoDetail = huibaoDetail == null ? null : huibaoDetail.trim();
    }


    public String getPer() {
        return per;
    }

    public void setPer(String per) {
        this.per = per == null ? null : per.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHuibaoName() {
        return huibaoName;
    }

    public void setHuibaoName(String huibaoName) {
        this.huibaoName = huibaoName;
    }
}