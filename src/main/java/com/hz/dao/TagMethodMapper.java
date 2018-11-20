package com.hz.dao;

import com.hz.domain.TagMethod;

import java.util.List;

public interface TagMethodMapper {
    int insert(TagMethod record);

    int insertSelective(TagMethod record);

    List selectTagIds(int methodId);

    int deleteMethodResource(int methodId);

    int deleteTagMethod(TagMethod tagMethod);

    int insertTagMethods(List<TagMethod> tagMethods);
}