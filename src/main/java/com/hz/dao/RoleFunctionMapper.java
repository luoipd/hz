package com.hz.dao;

import com.hz.domain.RoleFunction;

import java.util.List;

public interface RoleFunctionMapper {
    int insert(RoleFunction record);

    int insertSelective(RoleFunction record);

    List<String> selectFunctionIdByRoleId(int roleId);
}