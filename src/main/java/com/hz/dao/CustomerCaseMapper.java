package com.hz.dao;

import com.hz.domain.CustomerCase;

public interface CustomerCaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerCase record);

    int insertSelective(CustomerCase record);

    CustomerCase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerCase record);

    int updateByPrimaryKey(CustomerCase record);
}