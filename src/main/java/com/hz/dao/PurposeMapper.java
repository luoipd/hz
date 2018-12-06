package com.hz.dao;

import com.hz.domain.Purpose;

import java.util.List;

public interface PurposeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Purpose record);

    int insertSelective(Purpose record);

    Purpose selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Purpose record);

    int updateByPrimaryKey(Purpose record);

    List<Purpose> selectPurposeInfoList();
}