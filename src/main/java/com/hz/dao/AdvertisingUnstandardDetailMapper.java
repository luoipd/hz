package com.hz.dao;

import com.hz.domain.AdvertisingUnstandardDetail;

public interface AdvertisingUnstandardDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdvertisingUnstandardDetail record);

    int insertSelective(AdvertisingUnstandardDetail record);

    AdvertisingUnstandardDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdvertisingUnstandardDetail record);

    int updateByPrimaryKey(AdvertisingUnstandardDetail record);

    AdvertisingUnstandardDetail selectByPid(Integer id);
}