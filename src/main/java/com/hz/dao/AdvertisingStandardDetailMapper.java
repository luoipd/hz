package com.hz.dao;

import com.hz.domain.AdvertisingStandardDetail;

public interface AdvertisingStandardDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdvertisingStandardDetail record);

    int insertSelective(AdvertisingStandardDetail record);

    AdvertisingStandardDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdvertisingStandardDetail record);

    int updateByPrimaryKey(AdvertisingStandardDetail record);

    AdvertisingStandardDetail selectByPid(Integer id);
}