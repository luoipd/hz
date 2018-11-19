package com.hz.service;

import com.hz.domain.AdvertisingStandardDetail;
import com.hz.domain.AdvertisingUnstandardDetail;
import com.hz.domain.MethodResource;
import com.hz.domain.responseBean.ResourceBean;
import com.hz.util.page.PageRequest;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/16
 */
public interface MethodResourceService {


        List<MethodResource> getMethodResourceList(MethodResource methodResource, PageRequest pageRequest,Integer[] tagIds);

        ResourceBean getResourceBeanById(int id);

        void updateAll(MethodResource methodResource, AdvertisingStandardDetail advertisingStandardDetail, AdvertisingUnstandardDetail advertisingUnstandardDetail,int id);

        void insertMethodResource(MethodResource methodResource,int id) throws Exception;

        void delMethodResourceOnlyStatus(int id,int updaterId);


}
