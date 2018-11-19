package com.hz.controller.usercenterController;

import com.alibaba.fastjson.JSONObject;
import com.hz.controller.BaseController;
import com.hz.domain.AdvertisingStandardDetail;
import com.hz.domain.AdvertisingUnstandardDetail;
import com.hz.domain.MethodResource;
import com.hz.domain.responseBean.ResourceBean;
import com.hz.service.MethodResourceService;
import com.hz.util.ResJson;
import com.hz.util.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    /**
     * 根据媒体，根据行业根据标签
     * @param methodResource
     * @param pageRequest
     * @return
     */
    @RequestMapping(value = "/api/hzResource/methodResourceList",method = RequestMethod.GET)
    @ResponseBody
        public String getMethodSourceList(@Valid MethodResource methodResource, @Valid PageRequest pageRequest,@Valid Integer[] tagIds){
        ResJson resJson = new ResJson();
        List<MethodResource> methodResources = methodResourceService.getMethodResourceList(methodResource,pageRequest,tagIds);
        resJson.setData(methodResources);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/hzResource/methodResourceInfo",method = RequestMethod.GET)
    @ResponseBody
    public String getResourceInfo(@Valid int id){

        ResourceBean resourceBean = methodResourceService.getResourceBeanById(id);
        ResJson resJson = new ResJson();
        resJson.setData(resourceBean);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/hzResource/updateMethodResourceInfo",method = RequestMethod.POST)
    @ResponseBody
    public String updateMethodResourceInfo(@Valid  MethodResource methodResource, @Valid AdvertisingUnstandardDetail advertisingUnstandardDetail,
                                           @Valid AdvertisingStandardDetail advertisingStandardDetail ,@Valid Integer[] tagIds){
        ResJson resJson = new ResJson();
        if(tagIds!=null&&tagIds.length>0){
            methodResource.setTagIds(tagIds);
        }
        methodResourceService.updateAll(methodResource,advertisingStandardDetail,advertisingUnstandardDetail,sysUser.getId());
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/hzResource/createMethodResource",method = RequestMethod.POST)
    @ResponseBody
    public String createMethodResource(MethodResource methodResource){
        ResJson resJson = new ResJson();
        try {
            methodResourceService.insertMethodResource(methodResource,sysUser.getId());
        } catch (Exception e) {
            e.printStackTrace();
            resJson.setStatus(0);
            resJson.setDesc("缺少资源名称和资源类型");
        }
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 物理删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/hzResource/delMethodResource",method = RequestMethod.POST)
    @ResponseBody
    public String deleteMethodResource(@Valid int id){
        ResJson resJson = new ResJson();
        methodResourceService.delMethodResourceOnlyStatus(id,sysUser.getId());
        return JSONObject.toJSONString(resJson);
    }





}
