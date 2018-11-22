package com.hz.controller.usercenterController;

import com.alibaba.fastjson.JSONObject;
import com.hz.controller.BaseController;
import com.hz.domain.Media;
import com.hz.domain.PictureVideo;
import com.hz.domain.User;
import com.hz.service.MediaService;
import com.hz.service.PictureVideoService;
import com.hz.service.impl.ImageService;
import com.hz.util.ImageUtil;
import com.hz.util.ResJson;
import com.hz.util.WebAppConfig;
import com.hz.util.page.PageRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lyp
 * @date 2018/11/14
 */
@Slf4j
@RestController
public class HzMediaController extends BaseController {


    @Autowired
    MediaService mediaService;

    @Autowired
    PictureVideoService pictureVideoService;
    @Autowired
    WebAppConfig webAppConfig;
    @Autowired
    ImageUtil imageUtil;
    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/api/hzMedia/mediaList", method = RequestMethod.GET)
    @ResponseBody
    public String getMediaList(@Valid Media media, @Valid PageRequest pageRequest){
        ResJson resJson = new ResJson();
        List<Media> medias = mediaService.getMediaList(media,pageRequest);
        int count = mediaService.countMedia(media);
        Map map = new HashMap();
        map.put("medias",medias);
        map.put("total",count);
        resJson.setData(map);
        return JSONObject.toJSONString(resJson);
    }
    @RequestMapping(value = "/api/hzMedia/mediaListForCheck", method = RequestMethod.GET)
    @ResponseBody
    public String getMediaListForCheck(){
        ResJson resJson = new ResJson();
        List<Media> medias = mediaService.getMediaList();
        resJson.setData(medias);
        return JSONObject.toJSONString(resJson);
    }




    @RequestMapping(value = "/api/hzMedia/getMediaInfo",method = RequestMethod.GET)
    @ResponseBody
    public String getMediaInfo(@Valid int id){
        Media media = mediaService.getMediaInfo(id);
        ResJson resJson = new ResJson();
        resJson.setData(media);
        return JSONObject.toJSONString(resJson);
    }


    @RequestMapping(value = "/api/hzMedia/createMedia",method = RequestMethod.POST)
    @ResponseBody
    public String createMedia(@Valid Media media,@Valid MultipartFile file){
        ResJson resJson = new ResJson();
        int picId = imageService.insertPictureFile(file,pictureVideoService,sysUser);
        if(picId==0){
            log.error("图片上传失败");
            resJson.setStatus(0);
            resJson.setDesc("图片上传失败");
            return JSONObject.toJSONString(resJson);
        }
        if(picId>0){
            media.setPicId(picId);
        }
        if(media.getMediaName()!=null&&media.getMediaName().length()>0){
            media.setCreaterId(sysUser.getId());
            media.setStatus(1);
        }
        try {
            mediaService.insertMedia(media);
        } catch (Exception e) {
            e.printStackTrace();
            resJson.setDesc("创建媒体失败！！");
            resJson.setStatus(0);
        }
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/hzMedia/updateMedia",method = RequestMethod.POST)
    @ResponseBody
    public String updateMedia(@Valid Media media,@Valid MultipartFile file){
        ResJson resJson = new ResJson();
        if(media.getId()==null||media.getId()==0){
            resJson.setDesc("缺少主键");
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
            media.setPicId(picId);
        }
        media.setUpdaterId(sysUser.getId());
        try {
            mediaService.editMedia(media);
        } catch (Exception e) {
            e.printStackTrace();
            resJson.setDesc("媒体修改失败！！");
            resJson.setStatus(0);
        }
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/hzMedia/delMedia",method = RequestMethod.POST)
    @ResponseBody
    public String delMedia(@Valid int id){
        ResJson resJson = new ResJson();
        Media media = new Media();
        media.setId(id);
        media.setStatus(0);
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User)session.getAttribute("user");
        media.setUpdaterId(user.getId());
        try {
            mediaService.editMedia(media);
        } catch (Exception e) {
            e.printStackTrace();
            resJson.setDesc("媒体删除失败！！");
            resJson.setStatus(0);
        }
        return JSONObject.toJSONString(resJson);
    }


    @RequestMapping(value = "/api/hzMedia/upload",method = RequestMethod.POST)
    @ResponseBody
    public String updload(@RequestParam("file")MultipartFile multipartFile){
        String contentType = multipartFile.getContentType();
        String root_fileName = multipartFile.getOriginalFilename();
        String filePath = webAppConfig.location + ImageUtil.MediaFolder;
        String fileName = imageUtil.getFileName(ImageUtil.MediaFolder,root_fileName);
        String filename = "";
        try {
            filename = imageUtil.saveImg(multipartFile,filePath,fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }




}
