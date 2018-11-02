package com.hz.dao;

import com.hz.domain.RoleFunction;

public interface RoleFunctionMapper {
    int insert(RoleFunction record);

    int insertSelective(RoleFunction record);
}