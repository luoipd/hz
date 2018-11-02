package com.hz.dao;

import com.hz.domain.UserRole;

import java.util.List;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> userRoles(int uid);


}