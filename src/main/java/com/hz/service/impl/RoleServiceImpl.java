package com.hz.service.impl;

import com.hz.dao.RoleFunctionMapper;
import com.hz.dao.RoleMapper;
import com.hz.dao.UserRoleMapper;
import com.hz.domain.Role;
import com.hz.domain.RoleFunction;
import com.hz.domain.UserRole;
import com.hz.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/1
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RoleFunctionMapper roleFunctionMapper;

    @Override
    public void insertRole(UserRole userRole) {
        userRoleMapper.insert(userRole);
    }

    @Override
    public List<Role> getRoleList(Role role) {
        List<Role> roles = roleMapper.selectRoleByRole(role);
        return roles;
    }

    @Override
    public void insertManageRole(Role role) {
        roleMapper.insertSelective(role);
    }

    @Override
    public void addRoleFunction(List<RoleFunction> roleFunctions) {
        for(RoleFunction roleFunction:roleFunctions){
            roleFunctionMapper.insert(roleFunction);
        }

    }
}
