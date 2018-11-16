package com.hz.dao;

import com.hz.domain.HuiBao;

import java.util.List;

public interface HuiBaoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HuiBao record);

    int insertSelective(HuiBao record);

    HuiBao selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HuiBao record);

    int updateByPrimaryKey(HuiBao record);

    List<HuiBao> selectListByPid(Integer id);
}