package com.hz.service.impl;

import com.hz.dao.TagMapper;
import com.hz.domain.Tag;
import com.hz.domain.User;
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
    public void deleteTag(int id, User user) {
        Tag tag = new Tag();
        tag.setId(id);
        tag.setStatus(0);
        tag.setUpdaterId(user.getId());
        tagMapper.updateByPrimaryKeySelective(tag);
    }

    @Override
    public void updateTag(Tag tag) {
        tagMapper.updateByPrimaryKey(tag);
    }
}
