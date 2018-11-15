package com.hz.service;

import com.hz.domain.Role;
import com.hz.domain.RoleFunction;
import com.hz.domain.UserRole;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/1
 */
public interface RoleService {

    void insertRole(UserRole userRole);

    List<Role> getRoleList(Role role);

    void insertManageRole(Role role);

    void addRoleFunction(List<RoleFunction> roleFunctions);

    void insertRoles(List<UserRole> userRoles) throws Exception;

    void insertRoleFunction(List<String> functions,int roleId);

    void editRole(Role role);

    void delRole(int id);
}
