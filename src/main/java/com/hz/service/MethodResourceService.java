package com.hz.service;

import com.hz.domain.*;
import com.hz.domain.responseBean.ResourceBean;
import com.hz.util.page.PageRequest;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/16
 */
public interface MethodResourceService {


        List<MethodResource> getMethodResourceList(MethodResource methodResource, PageRequest pageRequest);

        int countMethodResource(MethodResource methodResource);

        ResourceBean getResourceBeanById(int id);

        int updateAll(MethodResource methodResource, AdvertisingStandardDetail advertisingStandardDetail, AdvertisingUnstandardDetail advertisingUnstandardDetail,int id);

        void insertMethodResource(MethodResource methodResource,int id) throws Exception;

        void delMethodResourceOnlyStatus(int id,int updaterId);

        void insertHuibao(HuiBao huiBao);

        void updateHuibao(HuiBao huiBao);

        HuiBao selectHuibaoById(int id);

        void deleteHuibaobyId(int id);

        void insertAdvertisingStyle(AdvertisingStyle advertisingStyle);

        void updateAdvertisingStyle(AdvertisingStyle advertisingStyle);

        AdvertisingStyle selectAdvertisingStyleById(int id);

        void deleteAdvertisingStylebyId(int id);

        void deleteTagMethod(TagMethod tagMethod);

        void createTagMethod(List<TagMethod> tagMethods,int methodId);

        List<Tag> selectTagList(int methodId);

        List<HuiBao> getHuiBaoList(int id,PageRequest pageRequest);

        int countHuiBaoList(int id);

        List<AdvertisingStyle> getAdvertisingStyleList(int id,PageRequest pageRequest);

        int countAdvertisingStyleList(int id);


}
