package com.hz.controller.usercenterController;

import com.alibaba.fastjson.JSONObject;
import com.hz.controller.BaseController;
import com.hz.domain.CustomerCase;
import com.hz.domain.PictureVideo;
import com.hz.service.CustomerCaseService;
import com.hz.service.PictureVideoService;
import com.hz.service.impl.ImageService;
import com.hz.util.ResJson;
import com.hz.util.page.PageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lyp
 * @date 2018/12/5
 */
@Slf4j
@RestController
public class HzCustomerCaseController extends BaseController {

    @Autowired
    CustomerCaseService customerCaseService;
    @Autowired
    ImageService imageService;
    @Autowired
    PictureVideoService pictureVideoService;

    @RequestMapping(value = "/api/customerCase/getCustomerCaseList",method = RequestMethod.GET)
    public String CustomerCaseList(@Valid PageRequest pageRequest,@Valid CustomerCase customerCase){
        ResJson resJson = new ResJson();
        List<CustomerCase> customerCases =  customerCaseService.getCustomerCaseList(customerCase,pageRequest);
        int count = customerCaseService.countCustomerCase(customerCase);
        Map map = new HashMap();
        map.put("customerCases",customerCases);
        map.put("total",count);
        resJson.setData(map);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/customerCase/updateCustomerCase" ,method = RequestMethod.POST)
    public String updateCustomerCase(@Valid CustomerCase customerCase,@Valid MultipartFile file){
        ResJson resJson = new ResJson();
        int picId = imageService.insertPictureFile(file,pictureVideoService,sysUser);
        if(picId==0){
            log.error("图片上传失败");
            resJson.setStatus(0);
            resJson.setDesc("图片上传失败");
            return JSONObject.toJSONString(resJson);
        }
        if(picId>0){
            customerCase.setPicId(picId);
        }
        customerCase.setUpdaterId(sysUser.getId());
        customerCaseService.updateCustomerCase(customerCase);
        return JSONObject.toJSONString(resJson);
    }
    @RequestMapping(value = "/api/customerCase/insertCustomerCase",method = RequestMethod.POST)
    public String insertCustomerCase(@Valid CustomerCase customerCase,@Valid MultipartFile file){
        ResJson resJson = new ResJson();
        if(customerCase.getCustomerName()==null||"".equals(customerCase.getCustomerName())){
            resJson.setStatus(0);
            resJson.setDesc("请填入客户名称");
            return JSONObject.toJSONString(resJson);
        }else{
            customerCase.setCreaterId(sysUser.getId());
            customerCase.setStatus("1");
            customerCase.setModuleId(21);
        }
        int picId = imageService.insertPictureFile(file,pictureVideoService,sysUser);
        if(picId==0){
            log.error("图片上传失败");
            resJson.setStatus(0);
            resJson.setDesc("图片上传失败");
            return JSONObject.toJSONString(resJson);
        }
        if(picId>0){
            customerCase.setPicId(picId);
        }
        customerCaseService.createCustomerCase(customerCase);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/customerCase/customerCaseInfo" ,method = RequestMethod.GET)
    public String customerCaseInfo(@Valid int id){
        ResJson resJson = new ResJson();
        CustomerCase customerCase = customerCaseService.getCustomerCaseInfo(id);
        resJson.setData(customerCase);
        return JSONObject.toJSONString(resJson);
    }



}
