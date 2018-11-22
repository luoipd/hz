package com.hz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hz.domain.Media;
import com.hz.domain.PictureVideo;
import com.hz.domain.User;
import com.hz.service.PictureVideoService;
import com.hz.util.ImageUtil;
import com.hz.util.ResJson;
import com.hz.util.WebAppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lyp
 * @date 2018/11/16
 */
@Slf4j
@Service
public class ImageService {


    @Autowired
    WebAppConfig webAppConfig;
    @Autowired
    ImageUtil imageUtil;

    /**
     * 插入图片返回picId
     * @param file
     * @param pictureVideoService
     * @param user
     * @return
     */
    public int insertPictureFile(MultipartFile file, PictureVideoService pictureVideoService, User user){
        int id= 0;
        if(file!=null){
            //上传图片
            String filePath = webAppConfig.location + ImageUtil.MediaFolder;
            String fileName = imageUtil.getFileName(ImageUtil.MediaFolder,file.getOriginalFilename());
            try {
                fileName = imageUtil.saveImg(file,filePath,fileName);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("图片上传失败");
                return id;
            }
            PictureVideo pictureVideo = new PictureVideo();
            pictureVideo.setFileName(fileName);
            pictureVideo.setStoragePath(filePath);
            pictureVideo.setUrl(imageUtil.getPicUrl(ImageUtil.MediaFolder,fileName));
            pictureVideo.setSource(ImageUtil.MediaFolder);
            pictureVideo.setType(file.getContentType());

            pictureVideo.setCreaterId(user.getId());
            pictureVideo.setStatus(1);
            pictureVideo.setDesc(file.getOriginalFilename());
            id = pictureVideoService.insert(pictureVideo);
        }else{
            id=-1;
            log.info("未上传图片！！！！");
        }
        return id;

    }

    /**
     * 批量插入图片
     * @param files
     * @param pictureVideoService
     * @param user
     * @return
     */
    public List<Integer> insertPictureFiles(MultipartFile[] files, PictureVideoService pictureVideoService, User user){
        List list = new ArrayList();
        if(files.length!=0){
            //上传图片
            for(MultipartFile file:files){
                int id;
                String filePath = webAppConfig.location + ImageUtil.MediaFolder;
                String fileName = imageUtil.getFileName(ImageUtil.MediaFolder,file.getOriginalFilename());
                try {
                    fileName = imageUtil.saveImg(file,filePath,fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error("图片上传失败");
                }
                PictureVideo pictureVideo = new PictureVideo();
                pictureVideo.setFileName(fileName);
                pictureVideo.setStoragePath(filePath);
                pictureVideo.setUrl(imageUtil.getPicUrl(ImageUtil.MediaFolder,fileName));
                pictureVideo.setSource(ImageUtil.MediaFolder);
                pictureVideo.setType(file.getContentType());

                pictureVideo.setCreaterId(user.getId());
                pictureVideo.setStatus(1);
                pictureVideo.setDesc(file.getOriginalFilename());
                id = pictureVideoService.insert(pictureVideo);
                list.add(id);
            }

        }else{
            log.info("未上传图片！！！！");
        }
        return list;

    }
}
