package com.hz.service;

import com.hz.domain.Tag;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/19
 */
public interface BasicService {

    List<Tag> getTagList(int tagType);

    void insertTag(Tag tag);

    void deleteTag(int id);
}
