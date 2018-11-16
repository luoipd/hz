package com.hz.controller.usercenterController;

import com.alibaba.fastjson.JSONObject;
import com.hz.controller.BaseController;
import com.hz.domain.MethodResource;
import com.hz.domain.responseBean.ResourceBean;
import com.hz.service.MethodResourceService;
import com.hz.util.ResJson;
import com.hz.util.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author lyp
 * @date 2018/11/16
 */
@RestController
public class HzResourceController extends BaseController {


    @Autowired
    MethodResourceService methodResourceService;

    @RequestMapping(value = "/api/hzResource/methodResourceList")
    @ResponseBody
    public String getMethodSourceList(@Valid MethodResource methodResource, @Valid PageRequest pageRequest){
        ResJson resJson = new ResJson();
        List<MethodResource> methodResources = methodResourceService.getMethodResourceList(methodResource,pageRequest);
        resJson.setData(methodResource);
        return JSONObject.toJSONString(methodResources);
    }

    @RequestMapping(value = "/api/hzResource/methodResourceInfo")
    @ResponseBody
    public String getResourceInfo(@Valid int id){

        ResourceBean resourceBean = methodResourceService.getResourceBeanById(id);
        ResJson resJson = new ResJson();
        resJson.setData(resourceBean);
        return JSONObject.toJSONString(resJson);
    }


}
