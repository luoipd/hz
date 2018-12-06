package com.hz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hz.dao.MediaMapper;
import com.hz.domain.Media;
import com.hz.service.MediaService;
import com.hz.util.page.PageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/14
 */
@Service
@Slf4j
public class MediaServiceImpl implements MediaService {

    @Autowired
    MediaMapper mediaMapper;
    @Override
    public List<Media> getMediaList(Media media, PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<Media> media1 = mediaMapper.selectListMedia(media);
        PageInfo<Media> mediaPageInfo = new PageInfo<>(media1);
        log.info(mediaPageInfo.getTotal()+"!!!");
        return media1;
    }

    @Override
    public List<Media> getMediaList() {
        List<Media> media1 = mediaMapper.selectListMedia1();

        return media1;
    }


    @Override
    public int countMedia(Media media) {

        int i = mediaMapper.countMedia(media);
        return i;
    }

    @Override
    public void insertMedia(Media media) throws Exception{
        if(media.getMediaName()==null){
            throw new Exception("媒体名称不能为空");
        }
        try{
            mediaMapper.insertSelective(media);
        }catch (Exception e){
            throw new Exception("插入失败");
        }

    }

    @Override
    public void editMedia(Media media) throws Exception{
        try{
            mediaMapper.updateByPrimaryKeySelective(media);
        }catch (Exception e){
            throw new Exception("更新失败");
        }

    }

    @Override
    public void delMediaById(int id) throws Exception{

        try{
            mediaMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            throw new Exception("插入失败");
        }
    }

    @Override
    public Media getMediaInfo(int id) {
        Media media = mediaMapper.selectByPrimaryKey(id);
        return media;
    }


}
