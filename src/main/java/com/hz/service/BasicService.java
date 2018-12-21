package com.hz.service;

import com.hz.domain.Industry;
import com.hz.domain.Purpose;
import com.hz.domain.Tag;
import com.hz.domain.User;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/19
 */
public interface BasicService {

    List<Tag> getTagList(Tag tag);

    List<Tag> getTagListCheck(Tag tag);

    void insertTag(Tag tag);

    void deleteTag(int id, User user);

    void updateTag(Tag tag);

    List<Industry> getIndustryList(Industry industry);

    List<Industry> getIndustryListCheck(String industryName);

    void insertIndustry(Industry industry);

    void updateIndustry(Industry industry);

    void deleteIndustryById(int id);

    List<Purpose> getPurposeList();

    void insertPurpose(Purpose purpose);

    void updatePurpose(Purpose purpose);

    void deletePurposeById(int id);
}
