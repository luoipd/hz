package com.hz.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MethodResource {
    private Integer id;

    private String name;

    private Integer moduleId;

    private String moduleName;

    private Integer methodType;

    private Integer mediaId;

    private String mediaName;

    private Integer industryId;

    private String industryName;

    private String price;

    private String col1;

    private String col2;

    private String importLevel;

    private String desc;

    private String characteristic;

    private Integer status;

    private Integer createrId;

    private Date createTime;

    private Integer updaterId;

    private Date updateTime;

    private Integer[] tagIds;

    private List<Tag> tags;

    private AdvertisingStandardDetail advertisingStandardDetail;

    private AdvertisingUnstandardDetail advertisingUnstandardDetail;

    private List<HuiBao> huiBaos;

    private List<AdvertisingStyle> advertisingStyles;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getMethodType() {
        return methodType;
    }

    public void setMethodType(Integer methodType) {
        this.methodType = methodType;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImportLevel() {
        return importLevel;
    }

    public void setImportLevel(String importLevel) {
        this.importLevel = importLevel == null ? null : importLevel.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic == null ? null : characteristic.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public Integer[] getTagIds() {
        return tagIds;
    }

    public void setTagIds(Integer[] tagIds) {
        this.tagIds = tagIds;
    }

    public AdvertisingStandardDetail getAdvertisingStandardDetail() {
        return advertisingStandardDetail;
    }

    public void setAdvertisingStandardDetail(AdvertisingStandardDetail advertisingStandardDetail) {
        this.advertisingStandardDetail = advertisingStandardDetail;
    }

    public AdvertisingUnstandardDetail getAdvertisingUnstandardDetail() {
        return advertisingUnstandardDetail;
    }

    public void setAdvertisingUnstandardDetail(AdvertisingUnstandardDetail advertisingUnstandardDetail) {
        this.advertisingUnstandardDetail = advertisingUnstandardDetail;
    }

    public List<HuiBao> getHuiBaos() {
        return huiBaos;
    }

    public void setHuiBaos(List<HuiBao> huiBaos) {
        this.huiBaos = huiBaos;
    }

    public List<AdvertisingStyle> getAdvertisingStyles() {
        return advertisingStyles;
    }

    public void setAdvertisingStyles(List<AdvertisingStyle> advertisingStyles) {
        this.advertisingStyles = advertisingStyles;
    }
}