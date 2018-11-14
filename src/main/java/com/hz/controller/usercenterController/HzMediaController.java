package com.hz.controller.usercenterController;

import com.alibaba.fastjson.JSONObject;
import com.hz.domain.Media;
import com.hz.domain.PictureVideo;
import com.hz.domain.User;
import com.hz.service.MediaService;
import com.hz.service.PictureVideoService;
import com.hz.util.ImageUtil;
import com.hz.util.ResJson;
import com.hz.util.WebAppConfig;
import com.hz.util.page.PageRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class HzMediaController extends WebAppConfig {


    @Autowired
    MediaService mediaService;

    @Autowired
    PictureVideoService pictureVideoService;

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


    @RequestMapping(value = "/api/hzMedia/createMedia",method = RequestMethod.POST)
    @ResponseBody
    public String createMedia(@Valid Media media,@Valid MultipartFile file){
        ResJson resJson = new ResJson();
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User)session.getAttribute("user");
        if(file!=null){
            //上传图片
            String filePath = location + ImageUtil.MediaFolder;
            String fileName = ImageUtil.getFileName(ImageUtil.MediaFolder,file.getOriginalFilename());
            try {
                fileName = ImageUtil.saveImg(file,filePath,fileName);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("图片上传失败");
                resJson.setStatus(0);
                resJson.setDesc("图片上传失败");
                return JSONObject.toJSONString(resJson);
            }
            PictureVideo pictureVideo = new PictureVideo();
            pictureVideo.setFileName(fileName);
            pictureVideo.setStoragePath(filePath);
            pictureVideo.setUrl(ImageUtil.getPicUrl(ImageUtil.MediaFolder,fileName));
            pictureVideo.setSource(ImageUtil.MediaFolder);
            pictureVideo.setType(file.getContentType());

            pictureVideo.setCreaterId(user.getId());
            pictureVideo.setStatus(1);
            pictureVideo.setDesc(file.getOriginalFilename());
            int id = pictureVideoService.insert(pictureVideo);
            media.setPicId(id);
        }else{
            log.info("未上传图片！！！！");
        }
        media.setCreaterId(user.getId());

        try {
            mediaService.insertMedia(media);
        } catch (Exception e) {
            e.printStackTrace();
            resJson.setDesc("创建媒体失败！！");
            resJson.setStatus(0);
        }
        return JSONObject.toJSONString(resJson);
    }


    @RequestMapping(value = "/api/hzMedia/upload",method = RequestMethod.POST)
    @ResponseBody
    public String updload(@RequestParam("file")MultipartFile multipartFile){
        String contentType = multipartFile.getContentType();
        String root_fileName = multipartFile.getOriginalFilename();
        String filePath = location + ImageUtil.MediaFolder;
        String fileName = ImageUtil.getFileName(ImageUtil.MediaFolder,root_fileName);
        String filename = "";
        try {
            filename = ImageUtil.saveImg(multipartFile,filePath,fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

}
