package com.hz.dao;

import com.hz.domain.AdvertisingStyle;

import java.util.List;

public interface AdvertisingStyleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdvertisingStyle record);

    int insertSelective(AdvertisingStyle record);

    AdvertisingStyle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdvertisingStyle record);

    int updateByPrimaryKey(AdvertisingStyle record);

    List<AdvertisingStyle> selectListByPid(Integer id);

    int countListByPid(Integer id);
}