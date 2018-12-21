package com.hz.service.impl;

import com.hz.dao.IndustryMapper;
import com.hz.dao.PurposeMapper;
import com.hz.dao.TagMapper;
import com.hz.domain.Industry;
import com.hz.domain.Purpose;
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
    @Autowired
    IndustryMapper industryMapper;
    @Autowired
    PurposeMapper purposeMapper;

    @Override
    public List<Tag> getTagList(Tag tag) {
        return tagMapper.selectTagList(tag);
    }

    @Override
    public List<Tag> getTagListCheck(Tag tag) {
        return tagMapper.selectTagListCheck(tag);
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
        tagMapper.updateByPrimaryKeySelective(tag);
    }

    @Override
    public List<Industry> getIndustryList(Industry industry) {

        return industryMapper.selectIndustryInfoList(industry);
    }

    @Override
    public List<Industry> getIndustryListCheck(String industryName) {

        return industryMapper.selectIndustryInfoListCheck(industryName);
    }

    @Override
    public void insertIndustry(Industry industry){
        industryMapper.insertSelective(industry);
    }

    @Override
    public void updateIndustry(Industry industry) {
        industryMapper.updateByPrimaryKeySelective(industry);
    }

    @Override
    public void deleteIndustryById(int id) {
        industryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Purpose> getPurposeList() {

        return purposeMapper.selectPurposeInfoList();
    }

    @Override
    public void insertPurpose(Purpose purpose){
        purposeMapper.insertSelective(purpose);
    }

    @Override
    public void updatePurpose(Purpose purpose) {
        purposeMapper.updateByPrimaryKeySelective(purpose);
    }

    @Override
    public void deletePurposeById(int id) {
        purposeMapper.deleteByPrimaryKey(id);
    }
}
