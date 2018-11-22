package com.hz.dao;

import com.hz.domain.Media;

import java.util.List;

public interface MediaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Media record);

    int insertSelective(Media record);

    Media selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Media record);

    int updateByPrimaryKey(Media record);

    List<Media> selectListMedia(Media media);

    List<Media> selectListMedia1();

    int countMedia(Media media);
}