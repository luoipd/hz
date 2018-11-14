package com.hz.service.impl;

import com.hz.dao.PictureVideoMapper;
import com.hz.domain.PictureVideo;
import com.hz.service.PictureVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lyp
 * @date 2018/11/14
 */
@Service
public class PictureVideoServiceImpl implements PictureVideoService {

    @Autowired
    PictureVideoMapper pictureVideoMapper;

    @Override
    public int insert(PictureVideo pictureVideo) {
        return pictureVideoMapper.insertSelective(pictureVideo);
    }
}
