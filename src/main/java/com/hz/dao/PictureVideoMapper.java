package com.hz.dao;

import com.hz.domain.PictureVideo;

import java.util.List;

public interface PictureVideoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PictureVideo record);

    int insertSelective(PictureVideo record);

    PictureVideo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PictureVideo record);

    int updateByPrimaryKey(PictureVideo record);

    List<PictureVideo> selectPicVideoByModuleAndDataId(int moduleId,int dataId);
}