package com.hz.controller.usercenterController;

import com.alibaba.fastjson.JSONObject;
import com.hz.controller.BaseController;
import com.hz.domain.*;
import com.hz.domain.responseBean.ResourceBean;
import com.hz.domain.responseBean.ResourceParamBean;
import com.hz.service.MethodResourceService;
import com.hz.service.PictureVideoService;
import com.hz.service.impl.ImageService;
import com.hz.util.ResJson;
import com.hz.util.page.PageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lyp
 * @date 2018/11/16
 */
@Slf4j
@RestController
public class HzResourceController extends BaseController {


    @Autowired
    MethodResourceService methodResourceService;
    @Autowired
    PictureVideoService pictureVideoService;
    @Autowired
    ImageService imageService;

    /**
     * 根据媒体，根据行业根据标签
     * @param methodResource
     * @param pageRequest
     * @return
     */
    @RequestMapping(value = "/api/hzResource/methodResourceList",method = RequestMethod.GET)
    @ResponseBody
        public String getMethodSourceList(@Valid MethodResource methodResource, @Valid PageRequest pageRequest){
        ResJson resJson = new ResJson();
        List<MethodResource> methodResources = methodResourceService.getMethodResourceList(methodResource,pageRequest);
        int cout = methodResourceService.countMethodResource(methodResource);
        Map map = new HashMap();
        map.put("methodResources",methodResources);
        map.put("total",cout);
        resJson.setData(map);
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

    /**
     * 创建资源
     * @param
     * @return
     */
    @RequestMapping(value = "/api/hzResource/createMethodResource",method = RequestMethod.POST)
    @ResponseBody
    public String createMethodResource(@Valid MethodResource methodResource){
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
     * 新增与修改资源
     * @param resourceParamBean
     * @return
     */
    @RequestMapping(value = "/api/hzResource/updateMethodResourceInfo",method = RequestMethod.POST)
    @ResponseBody
    public String updateMethodResourceInfo(@Valid ResourceParamBean resourceParamBean){
        MethodResource methodResource1 = new MethodResource();
        AdvertisingUnstandardDetail advertisingUnstandardDetail1 = new AdvertisingUnstandardDetail();
        AdvertisingStandardDetail advertisingStandardDetail1 = new AdvertisingStandardDetail();
        methodResource1.setId(resourceParamBean.getMethodSourceId());
        methodResource1.setName(resourceParamBean.getName());
        methodResource1.setMethodType(resourceParamBean.getMethodType());
        methodResource1.setMediaId(resourceParamBean.getMediaId());
        methodResource1.setPrice(resourceParamBean.getPrice());
        methodResource1.setLightExposure(resourceParamBean.getLightExposure());
        methodResource1.setPv(resourceParamBean.getPv());
        methodResource1.setImportLevel(resourceParamBean.getImportLevel());
        methodResource1.setDesc(resourceParamBean.getDesc());
        methodResource1.setTagIds(resourceParamBean.getTagIds());
        methodResource1.setCharacteristic(resourceParamBean.getCharacteristic());
        advertisingStandardDetail1.setParentId(resourceParamBean.getMethodSourceId());
        advertisingStandardDetail1.setName(resourceParamBean.getName());
        advertisingStandardDetail1.setPlatform(resourceParamBean.getPlatform());
        advertisingStandardDetail1.setChargeMode(resourceParamBean.getChargeMode());
        advertisingUnstandardDetail1.setParentId(resourceParamBean.getMethodSourceId());
        advertisingUnstandardDetail1.setName(resourceParamBean.getName());
        advertisingUnstandardDetail1.setXiwei(resourceParamBean.getXiwei());
        advertisingUnstandardDetail1.setScheduling(resourceParamBean.getScheduling());
        advertisingUnstandardDetail1.setDetail(resourceParamBean.getDetail());
        advertisingUnstandardDetail1.setIsSplit(resourceParamBean.getIsSplit());
        ResJson resJson = new ResJson();
        methodResourceService.updateAll(methodResource1,advertisingStandardDetail1,advertisingUnstandardDetail1,sysUser.getId());
        return JSONObject.toJSONString(resJson);
    }

    /**
         * 资源删除标签
     * @param tagMethod
     * @return
     */
    @RequestMapping(value = "/api/hzResource/delTagMethod",method = RequestMethod.POST)
    @ResponseBody
    public String delTagMethod(@Valid TagMethod tagMethod){
        ResJson resJson = new ResJson();
        if(tagMethod.getTagId()==null||tagMethod.getMethodId()==null){
            resJson.setStatus(0);
            resJson.setDesc("缺少资源id和标签id");
            return JSONObject.toJSONString(resJson);
        }
        methodResourceService.deleteTagMethod(tagMethod);
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 资源绑定标签
     * @param
     * @return
     */
    @RequestMapping(value = "/api/hzResource/createTagMethod",method = RequestMethod.POST)
    @ResponseBody
    public String createTagMethod(@Valid int methodId,@Valid Integer[] tagIds){
        ResJson resJson = new ResJson();
        if(methodId==0||tagIds.length==0){
            resJson.setStatus(0);
            resJson.setDesc("缺少资源id和标签id");
            return JSONObject.toJSONString(resJson);
        }
        List<TagMethod> tagMethods = new ArrayList<>();
        for(int tagId:tagIds){
            TagMethod tagMethod = new TagMethod();
            tagMethod.setTagId(tagId);
            tagMethod.setMethodId(methodId);
            tagMethod.setUpdaterId(sysUser.getId());
            tagMethod.setCreaterId(sysUser.getId());
            tagMethods.add(tagMethod);
        }
        methodResourceService.createTagMethod(tagMethods,methodId);
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 获取所有绑定标签
     * @param methodId
     * @return
     */
    @RequestMapping(value = "/api/hzResource/getTags",method = RequestMethod.GET)
    @ResponseBody
    public String getTagList(@Valid int methodId){
        ResJson resJson = new ResJson();
        List<Tag> tagList = methodResourceService.selectTagList(methodId);
        resJson.setData(tagList);
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

    /**
     *新增回报组合
     */
    @RequestMapping(value = "/api/hzResource/createHuibao",method = RequestMethod.POST)
    @ResponseBody
    public String createHuibao(@Valid HuiBao huiBao,@Valid MultipartFile file){
        ResJson resJson = new ResJson();

        if(huiBao.getHuibaoName()!=null&&huiBao.getHuibaoName().length()>0){
            int picId = imageService.insertPictureFile(file,pictureVideoService,sysUser);
            if(picId==0){
                log.error("图片上传失败");
                resJson.setStatus(0);
                resJson.setDesc("图片上传失败");
                return JSONObject.toJSONString(resJson);
            }
            if(picId>0){
                huiBao.setPicId(picId);
            }
            huiBao.setCreaterId(sysUser.getId());
        }else{
            resJson.setDesc("缺少回报名称");
            resJson.setStatus(0);
            return JSONObject.toJSONString(resJson);
        }
        methodResourceService.insertHuibao(huiBao);
        return JSONObject.toJSONString(resJson);
    }

    /**
     *更新回报组合
     */

    @RequestMapping(value = "/api/hzResource/updateHuibao",method = RequestMethod.POST)
    @ResponseBody
    public String updateHuibao(@Valid HuiBao huiBao,@Valid MultipartFile file){
        ResJson resJson = new ResJson();
        if(huiBao.getId()==null||huiBao.getId()==0){
            resJson.setData("缺少主键id！！");
            resJson.setStatus(0);
            return JSONObject.toJSONString(resJson);
        }
        int picId = imageService.insertPictureFile(file,pictureVideoService,sysUser);
        if(picId==0){
            log.error("图片上传失败");
            resJson.setStatus(0);
            resJson.setDesc("图片上传失败");
            return JSONObject.toJSONString(resJson);
        }
        if(picId>0){
            huiBao.setPicId(picId);
        }
        huiBao.setUpdaterId(sysUser.getId());
        methodResourceService.updateHuibao(huiBao);
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 根据回报id获取信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/hzResource/huibaoInfo",method = RequestMethod.GET)
    @ResponseBody
    public String getHuibaoInfo(@Valid int id){
        ResJson resJson = new ResJson();
        HuiBao huiBao = methodResourceService.selectHuibaoById(id);
        resJson.setData(huiBao);
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 删除回报组合
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/hzResource/delHuibao",method = RequestMethod.POST)
    @ResponseBody
    public String deleteHuibao(@Valid int id){
        ResJson resJson = new ResJson();
        methodResourceService.deleteHuibaobyId(id);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/hzResource/createAdvertisingStyle",method = RequestMethod.POST)
    @ResponseBody
    public String createAdvertisingStyle(@Valid AdvertisingStyle advertisingStyle,@Valid MultipartFile file ){
        ResJson resJson = new ResJson();
        if(advertisingStyle.getStyleName()!=null&&advertisingStyle.getStyleName().length()>0){
            int picId = imageService.insertPictureFile(file,pictureVideoService,sysUser);
            if(picId==0){
                log.error("图片上传失败");
                resJson.setStatus(0);
                resJson.setDesc("图片上传失败");
                return JSONObject.toJSONString(resJson);
            }
            if(picId>0){
                advertisingStyle.setPicId(picId);
            }
            advertisingStyle.setCreaterId(sysUser.getId());
        }else{
            resJson.setDesc("缺少样式名称");
            resJson.setStatus(0);
            return JSONObject.toJSONString(resJson);
        }
        methodResourceService.insertAdvertisingStyle(advertisingStyle);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/hzResource/updateAdvertisingStyle",method = RequestMethod.POST)
    @ResponseBody
    public String updateAdvertisingStyle(@Valid AdvertisingStyle advertisingStyle,@Valid MultipartFile file){
        ResJson resJson = new ResJson();
        if(advertisingStyle.getId()==null||advertisingStyle.getId()==0){
            resJson.setData("缺少主键id！！");
            resJson.setStatus(0);
            return JSONObject.toJSONString(resJson);
        }
        int picId = imageService.insertPictureFile(file,pictureVideoService,sysUser);
        if(picId==0){
            log.error("图片上传失败");
            resJson.setStatus(0);
            resJson.setDesc("图片上传失败");
            return JSONObject.toJSONString(resJson);
        }
        if(picId>0){
            advertisingStyle.setPicId(picId);
        }
        advertisingStyle.setUpdaterId(sysUser.getId());
        methodResourceService.updateAdvertisingStyle(advertisingStyle);
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 根据广告样式id获取信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/hzResource/advertisingStyleInfo",method = RequestMethod.GET)
    @ResponseBody
    public String getAdvertisingStyleInfo(@Valid int id){
        ResJson resJson = new ResJson();
        AdvertisingStyle advertisingStyle = methodResourceService.selectAdvertisingStyleById(id);
        resJson.setData(advertisingStyle);
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 删除广告样式
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/hzResource/delAdvertisingStyle",method = RequestMethod.POST)
    @ResponseBody
    public String delAdvertisingStyle(@Valid int id){
        ResJson resJson = new ResJson();
        methodResourceService.deleteAdvertisingStylebyId(id);
        return JSONObject.toJSONString(resJson);
    }




}
