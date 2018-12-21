package com.hz.dao;

import com.hz.domain.Industry;

import java.util.List;

public interface IndustryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Industry record);

    int insertSelective(Industry record);

    Industry selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Industry record);

    int updateByPrimaryKey(Industry record);

    List<Industry> selectIndustryInfoList(Industry industry);

    List<Industry> selectIndustryInfoListCheck(String industryName);
}