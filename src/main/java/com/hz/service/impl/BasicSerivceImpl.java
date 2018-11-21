package com.hz.service.impl;

import com.hz.dao.TagMapper;
import com.hz.domain.Tag;
import com.hz.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/19
 */
@Service
public class BasicSerivceImpl implements BasicService {


    @Autowired
    TagMapper tagMapper;

    @Override
    public List<Tag> getTagList(int tagType) {
        return tagMapper.selectTagList(tagType);
    }

    @Override
    public void insertTag(Tag tag) {
        tagMapper.insertSelective(tag);
    }

    @Override
    public void deleteTag(int id) {
        tagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateTag(Tag tag) {
        tagMapper.updateByPrimaryKeySelective(tag);
    }
}
