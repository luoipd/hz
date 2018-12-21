package com.hz.dao;

import com.hz.domain.Tag;

import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    List<Tag> selectTagList(Tag tag);

    List<Tag> selectTagListCheck(Tag tag);

    List<Tag> selectTagListByMethodId(int methodId);
}