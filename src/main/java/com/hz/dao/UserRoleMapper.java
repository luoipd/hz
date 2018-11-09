package com.hz.dao;

import com.hz.domain.UserRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> userRoles(int uid);

    int update(UserRole userRole);

    int insertRoles(List<UserRole> userRoles);

    List<UserRole> getUserRoles(int uid);

    int deleteUserRoles(int uid);


}