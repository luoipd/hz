package com.hz.service;

import com.hz.domain.MethodResource;
import com.hz.domain.responseBean.ResourceBean;
import com.hz.util.page.PageRequest;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/16
 */
public interface MethodResourceService {


        List<MethodResource> getMethodResourceList(MethodResource methodResource, PageRequest pageRequest);

        ResourceBean getResourceBeanById(int id);



}
