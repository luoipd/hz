package com.hz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Autowired
    TagMapper tagMapper;

    @Override
    public PageInfo<MethodResource> getMethodResourceList(MethodResource methodResource, PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<MethodResource> resourceList =  methodResourceMapper.selectResourceList(methodResource);
        for(MethodResource methodResource1:resourceList){
            methodResource1.setMediaName(mediaMapper.selectByPrimaryKey(methodResource1.getMediaId()).getMediaName());
            //资源不区分行业，行业字段废弃
//            methodResource1.setIndustryName(industryMapper.selectByPrimaryKey(methodResource1.getIndustryId()).getIndustryName());
            List<Tag> tagList = tagMapper.selectTagListByMethodId(methodResource1.getId());
            methodResource1.setTags(tagList);
        }
        PageInfo<MethodResource> methodResourcePageInfo =  new PageInfo<>(resourceList);
        return methodResourcePageInfo;
    }

    @Override
    public PageInfo<MethodResource> getMethodResources(MethodResource methodResource,PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<MethodResource> resourceList =  methodResourceMapper.selectResourceList(methodResource);
        for(MethodResource methodResource1:resourceList){
            methodResource1.setMediaName(mediaMapper.selectByPrimaryKey(methodResource1.getMediaId()).getMediaName());
            //资源不区分行业，行业字段废弃
//            methodResource1.setIndustryName(industryMapper.selectByPrimaryKey(methodResource1.getIndustryId()).getIndustryName());
            List<Tag> tagList = tagMapper.selectTagListByMethodId(methodResource1.getId());
            methodResource1.setTags(tagList);
            if(methodResource1.getMethodType()==1){
                AdvertisingStandardDetail advertisingStandardDetail = advertisingStandardDetailMapper.selectByPid(methodResource1.getId());
                methodResource1.setAdvertisingStandardDetail(advertisingStandardDetail);
                List<AdvertisingStyle> advertisingStyles = advertisingStyleMapper.selectListByPid(methodResource1.getId());
                methodResource1.setAdvertisingStyles(advertisingStyles);

            }
            if(methodResource1.getMethodType()==2){
                AdvertisingUnstandardDetail advertisingUnstandardDetail  = advertisingUnstandardDetailMapper.selectByPid(methodResource1.getId());
                methodResource1.setAdvertisingUnstandardDetail(advertisingUnstandardDetail);
                List<HuiBao> huiBaos = huiBaoMapper.selectListByPid(methodResource1.getId());
                methodResource1.setHuiBaos(huiBaos);
            }

        }
        PageInfo<MethodResource> methodResourcePageInfo = new PageInfo<>(resourceList);
        return methodResourcePageInfo;
    }

    @Override
    public int countMethodResource(MethodResource methodResource){
        return methodResourceMapper.countResources(methodResource);
    }


    @Override
    public ResourceBean getResourceBeanById(int id) {
        ResourceBean resourceBean = new ResourceBean();
        MethodResource methodResource = methodResourceMapper.selectByPrimaryKey(id);
        List<Tag> tagIds1 = tagMapper.selectTagListByMethodId(methodResource.getId());
//        Integer[] ta = new Integer[tagIds1.size()];
//        methodResource.setTagIds(tagIds1.toArray(ta));
        methodResource.setTags(tagIds1);
        resourceBean.setMethodResource(methodResource);
        //标准资源
        if(methodResource.getMethodType()==1){
            AdvertisingStandardDetail advertisingStandardDetail = advertisingStandardDetailMapper.selectByPid(id);
            resourceBean.setAdvertisingStandardDetail(advertisingStandardDetail);
//            List<AdvertisingStyle> advertisingStyles = advertisingStyleMapper.selectListByPid(id);
//            resourceBean.setAdvertisingStyles(advertisingStyles);
        }
        //非标资源
        if(methodResource.getMethodType()==2){
            AdvertisingUnstandardDetail advertisingUnstandardDetail = advertisingUnstandardDetailMapper.selectByPid(id);
            resourceBean.setAdvertisingUnstandardDetail(advertisingUnstandardDetail);
//            List<HuiBao> huiBaos = huiBaoMapper.selectListByPid(id);
//            resourceBean.setHuiBaos(huiBaos);
        }
        return resourceBean;
    }

    @Override
    @Transactional
    public int updateAll(MethodResource methodResource, AdvertisingStandardDetail advertisingStandardDetail, AdvertisingUnstandardDetail advertisingUnstandardDetail,int id) {

            if(methodResource.getId()==null||methodResource.getId()==0){
                methodResource.setCreaterId(id);
                methodResource.setStatus(1);
                methodResource.setModuleId(1);
                methodResourceMapper.insertSelective(methodResource);
            }else{
                methodResource.setUpdaterId(id);
                methodResourceMapper.updateByPrimaryKeySelective(methodResource);
            }
            //绑定标签信息
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
                //id为空是新增
                if(advertisingStandardDetail.getParentId()==null||advertisingStandardDetail.getParentId()==0){
                    advertisingStandardDetail.setCreaterId(id);
                    advertisingStandardDetail.setParentId(methodResource.getId());
                    advertisingStandardDetailMapper.insertSelective(advertisingStandardDetail);
                }else{
                    advertisingStandardDetail.setUpdaterId(id);
                    advertisingStandardDetailMapper.updateByPrimaryKeySelective(advertisingStandardDetail);
                }

            }
            if(methodResource.getMethodType()==2){
                if(advertisingUnstandardDetail.getParentId()==null||advertisingUnstandardDetail.getParentId()==0){
                    advertisingUnstandardDetail.setCreaterId(id);
                    advertisingUnstandardDetail.setParentId(methodResource.getId());
                    advertisingUnstandardDetailMapper.insertSelective(advertisingUnstandardDetail);
                }else{
                    advertisingUnstandardDetail.setUpdaterId(id);
                    advertisingUnstandardDetailMapper.updateByPrimaryKeySelective(advertisingUnstandardDetail);
                }
            }
            return methodResource.getId();
    }

    @Override
    public void insertMethodResource(MethodResource methodResource,int id) throws Exception {
        if(methodResource.getMethodType()!=null&&methodResource.getName()!=null){
            methodResource.setCreaterId(id);
            methodResource.setStatus(1);
            methodResource.setModuleId(1);
            methodResource.setIndustryId(1);
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

    @Override
    public void insertHuibao(HuiBao huiBao) {
        huiBaoMapper.insertSelective(huiBao);
    }

    @Override
    public void updateHuibao(HuiBao huiBao) {
        huiBaoMapper.updateByPrimaryKeySelective(huiBao);
    }

    @Override
    public HuiBao selectHuibaoById(int id) {
        HuiBao huiBao = huiBaoMapper.selectByPrimaryKey(id);
        return huiBao;
    }

    @Override
    public void deleteHuibaobyId(int id) {
        huiBaoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertAdvertisingStyle(AdvertisingStyle advertisingStyle) {
        advertisingStyleMapper.insertSelective(advertisingStyle);
    }

    @Override
    public void updateAdvertisingStyle(AdvertisingStyle advertisingStyle) {
        advertisingStyleMapper.updateByPrimaryKeySelective(advertisingStyle);
    }

    @Override
    public AdvertisingStyle selectAdvertisingStyleById(int id) {
        return advertisingStyleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteAdvertisingStylebyId(int id) {
        advertisingStyleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteTagMethod(TagMethod tagMethod) {
        tagMethodMapper.deleteTagMethod(tagMethod);
    }

    @Override
    public void createTagMethod(List<TagMethod> tagMethods,int methodId) {
        //清空绑定重新绑定
        tagMethodMapper.deleteMethodResource(methodId);
        tagMethodMapper.insertTagMethods(tagMethods);
    }

    @Override
    public List<Tag> selectTagList(int methodId) {
        List<Tag> tagList = tagMapper.selectTagListByMethodId(methodId);
        return tagList;
    }

    @Override
    public PageInfo<HuiBao> getHuiBaoList(int pid,PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<HuiBao> huiBaos = huiBaoMapper.selectListByPid(pid);
        PageInfo<HuiBao> huiBaoPageInfo = new PageInfo<>(huiBaos);
        return huiBaoPageInfo;
    }

    @Override
    public int countHuiBaoList(int id) {
        return huiBaoMapper.countListByPid(id);
    }

    @Override
    public PageInfo<AdvertisingStyle> getAdvertisingStyleList(int id, PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<AdvertisingStyle> advertisingStyles = advertisingStyleMapper.selectListByPid(id);
        PageInfo<AdvertisingStyle> advertisingStylePageInfo = new PageInfo<>(advertisingStyles);
        return advertisingStylePageInfo;
    }
    @Override
    public int countAdvertisingStyleList(int id) {
        return advertisingStyleMapper.countListByPid(id);
    }


}
