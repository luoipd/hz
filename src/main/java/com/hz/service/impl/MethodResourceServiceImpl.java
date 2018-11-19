package com.hz.service.impl;

import com.github.pagehelper.PageHelper;
import com.hz.dao.*;
import com.hz.domain.*;
import com.hz.domain.responseBean.ResourceBean;
import com.hz.service.MethodResourceService;
import com.hz.util.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/16
 */
@Service
public class MethodResourceServiceImpl implements MethodResourceService {

    @Autowired
    MethodResourceMapper methodResourceMapper;

    @Autowired
    MediaMapper mediaMapper;

    @Autowired
    IndustryMapper industryMapper;

    @Autowired
    AdvertisingStandardDetailMapper advertisingStandardDetailMapper;

    @Autowired
    AdvertisingUnstandardDetailMapper advertisingUnstandardDetailMapper;

    @Autowired
    HuiBaoMapper huiBaoMapper;

    @Autowired
    AdvertisingStyleMapper advertisingStyleMapper;

    @Autowired
    TagMethodMapper tagMethodMapper;

    @Override
    public List<MethodResource> getMethodResourceList(MethodResource methodResource, PageRequest pageRequest,Integer[] tagIds) {
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<MethodResource> resourceList =  methodResourceMapper.selectResourceList(methodResource);
        for(MethodResource methodResource1:resourceList){
            methodResource1.setMediaName(mediaMapper.selectByPrimaryKey(methodResource1.getMediaId()).getMediaName());
            methodResource1.setIndustryName(industryMapper.selectByPrimaryKey(methodResource1.getIndustryId()).getIndustryName());
            List<Integer> tagIds1 = tagMethodMapper.selectTagIds(methodResource1.getId());
            Integer[] ta = new Integer[tagIds1.size()];
            methodResource1.setTagIds(tagIds1.toArray(ta));
        }
        return resourceList;
    }

    @Override
    public ResourceBean getResourceBeanById(int id) {
        ResourceBean resourceBean = new ResourceBean();
        MethodResource methodResource = methodResourceMapper.selectByPrimaryKey(id);
        List<Integer> tagIds1 = tagMethodMapper.selectTagIds(methodResource.getId());
        Integer[] ta = new Integer[tagIds1.size()];
        methodResource.setTagIds(tagIds1.toArray(ta));
        resourceBean.setMethodResource(methodResource);
        //标准资源
        if(methodResource.getMethodType()==1){
            AdvertisingStandardDetail advertisingStandardDetail = advertisingStandardDetailMapper.selectByPid(id);
            resourceBean.setAdvertisingStandardDetail(advertisingStandardDetail);
            List<AdvertisingStyle> advertisingStyles = advertisingStyleMapper.selectListByPid(id);
            resourceBean.setAdvertisingStyles(advertisingStyles);
        }
        //非标资源
        if(methodResource.getMethodType()==2){
            AdvertisingUnstandardDetail advertisingUnstandardDetail = advertisingUnstandardDetailMapper.selectByPid(id);
            resourceBean.setAdvertisingUnstandardDetail(advertisingUnstandardDetail);
            List<HuiBao> huiBaos = huiBaoMapper.selectListByPid(id);
            resourceBean.setHuiBaos(huiBaos);
        }
        return resourceBean;
    }

    @Override
    @Transactional
    public void updateAll(MethodResource methodResource, AdvertisingStandardDetail advertisingStandardDetail, AdvertisingUnstandardDetail advertisingUnstandardDetail,int id) {
            methodResource.setCreaterId(id);
            methodResourceMapper.updateByPrimaryKeySelective(methodResource);
            if(methodResource.getTagIds()!=null&&methodResource.getTagIds().length>0){
                tagMethodMapper.deleteMethodResource(methodResource.getId());
                for(int tagId:methodResource.getTagIds()){
                    TagMethod tagMethod = new TagMethod();
                    tagMethod.setMethodId(methodResource.getId());
                    tagMethod.setTagId(tagId);
                    tagMethod.setCreaterId(id);
                    tagMethod.setUpdaterId(id);
                    tagMethodMapper.insertSelective(tagMethod);
                }
            }
            if(methodResource.getMethodType()==1){
                advertisingStandardDetail.setUpdaterId(id);
                advertisingStandardDetailMapper.updateByPrimaryKeySelective(advertisingStandardDetail);
            }
            if(methodResource.getMethodType()==2){
                advertisingUnstandardDetail.setUpdaterId(id);
                advertisingUnstandardDetailMapper.updateByPrimaryKeySelective(advertisingUnstandardDetail);
            }



    }

    @Override
    public void insertMethodResource(MethodResource methodResource,int id) throws Exception {
        if(methodResource.getMethodType()!=null&&methodResource.getName()!=null){
            methodResource.setCreaterId(id);
            methodResource.setStatus(1);
            methodResourceMapper.insertSelective(methodResource);
        }else {
            throw new Exception("缺少资源名称和类型");
        }
    }

    @Override
    public void delMethodResourceOnlyStatus(int id,int updaterId) {
        MethodResource methodResource = new MethodResource();
        methodResource.setId(id);
        methodResource.setStatus(0);
        methodResource.setUpdaterId(updaterId);
        methodResourceMapper.updateByPrimaryKeySelective(methodResource);
    }


}
