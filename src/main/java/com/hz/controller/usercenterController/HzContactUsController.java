package com.hz.controller.usercenterController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hz.controller.BaseController;
import com.hz.domain.ContactUs;
import com.hz.service.ContactUsService;
import com.hz.service.PictureVideoService;
import com.hz.service.impl.ImageService;
import com.hz.util.ImageUtil;
import com.hz.util.ResJson;
import com.hz.util.page.PageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * @author lyp
 * @date 2018/12/6
 */
@RestController
@Slf4j
public class HzContactUsController extends BaseController {



    @Autowired
    ContactUsService contactUsService;
    @Autowired
    ImageService imageService;
    @Autowired
    PictureVideoService pictureVideoService;

    @RequestMapping(value = "/api/contactUs/getContactUsList",method = RequestMethod.GET)
    public String ContactUsList(@Valid PageRequest pageRequest, @Valid ContactUs contactUs){
        ResJson resJson = new ResJson();
        PageInfo<ContactUs> contactUss =  contactUsService.getContactUsList(contactUs,pageRequest);
        resJson.setData(contactUss);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/contactUs/getContactUsListStencil",method = RequestMethod.GET)
    public String ContactUsListStencil(@Valid ContactUs contactUs){
        ResJson resJson = new ResJson();
        List<ContactUs> contactUss =  contactUsService.getContactUsListStencil(contactUs);
        resJson.setData(contactUss);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/contactUs/updateContactUs" ,method = RequestMethod.POST)
    public String updateContactUs(@Valid ContactUs contactUs,@Valid MultipartFile file){
        ResJson resJson = new ResJson();
        int picId = imageService.insertPictureFile(file,pictureVideoService,sysUser, ImageUtil.ContactUsFolder);
        if(picId==0){
            log.error("图片上传失败");
            resJson.setStatus(0);
            resJson.setDesc("图片上传失败");
            return JSONObject.toJSONString(resJson);
        }
        if(picId>0){
            contactUs.setPicId(picId);
        }
        contactUs.setUpdaterId(sysUser.getId());
        contactUsService.updateContactUs(contactUs);
        return JSONObject.toJSONString(resJson);
    }
    @RequestMapping(value = "/api/contactUs/insertContactUs",method = RequestMethod.POST)
    public String insertContactUs(@Valid ContactUs contactUs,@Valid MultipartFile file){
        ResJson resJson = new ResJson();
        if(contactUs.getTitle()==null||"".equals(contactUs.getTitle())){
            resJson.setStatus(0);
            resJson.setDesc("请填入title");
            return JSONObject.toJSONString(resJson);
        }else{
            contactUs.setCreaterId(sysUser.getId());
            contactUs.setStatus("1");
            contactUs.setModuleId(22);
        }
        int picId = imageService.insertPictureFile(file,pictureVideoService,sysUser,ImageUtil.ContactUsFolder);
        if(picId==0){
            log.error("图片上传失败");
            resJson.setStatus(0);
            resJson.setDesc("图片上传失败");
            return JSONObject.toJSONString(resJson);
        }
        if(picId>0){
            contactUs.setPicId(picId);
        }
        contactUsService.createContactUs(contactUs);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/contactUs/contactUsInfo" ,method = RequestMethod.GET)
    public String contactUsInfo(@Valid int id){
        ResJson resJson = new ResJson();
        ContactUs contactUs = contactUsService.getContactUsInfo(id);
        resJson.setData(contactUs);
        return JSONObject.toJSONString(resJson);
    }
}
