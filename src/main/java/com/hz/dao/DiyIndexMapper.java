package com.hz.dao;

import com.hz.domain.DiyIndex;

import java.util.List;

public interface DiyIndexMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiyIndex record);

    int insertSelective(DiyIndex record);

    DiyIndex selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiyIndex record);

    int updateByPrimaryKey(DiyIndex record);

    List<DiyIndex> selectDiyIndexByUserId(Integer userId);

    List<DiyIndex> getDiyIndexListForCheck(Integer createrId);

    List<DiyIndex> getDiyIndexList();

    void deleteUserIndexbyDiyId(Integer id);
}