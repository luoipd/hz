package com.hz.service.impl;

import com.github.pagehelper.PageHelper;
import com.hz.dao.*;
import com.hz.domain.*;
import com.hz.domain.responseBean.ResourceBean;
import com.hz.service.MethodResourceService;
import com.hz.util.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<MethodResource> getMethodResourceList(MethodResource methodResource, PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<MethodResource> resourceList =  methodResourceMapper.selectResourceList(methodResource);
        for(MethodResource methodResource1:resourceList){
            methodResource1.setMediaName(mediaMapper.selectByPrimaryKey(methodResource1.getMediaId()).getMediaName());
            methodResource1.setMediaName(industryMapper.selectByPrimaryKey(methodResource1.getIndustryId()).getIndustryName());
        }
        return resourceList;
    }

    @Override
    public ResourceBean getResourceBeanById(int id) {
        ResourceBean resourceBean = new ResourceBean();
        MethodResource methodResource = methodResourceMapper.selectByPrimaryKey(id);
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


}
