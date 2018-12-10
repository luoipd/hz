package com.hz.service.impl;

import com.hz.dao.RoleFunctionMapper;
import com.hz.dao.RoleMapper;
import com.hz.dao.UserMapper;
import com.hz.dao.UserRoleMapper;
import com.hz.domain.Role;
import com.hz.domain.RoleFunction;
import com.hz.domain.User;
import com.hz.domain.UserRole;
import com.hz.service.RoleService;
import com.hz.util.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lyp
 * @date 2018/11/1
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RoleFunctionMapper roleFunctionMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public void insertRole(UserRole userRole) {
        List<UserRole> userRoles = userRoleMapper.userRoles(userRole.getUserId());
        if(userRoles.size()==0){
            userRoleMapper.insert(userRole);
            return;
        }
        for(UserRole userRole1:userRoles){

            if(userRole1.getRoleId()==userRole.getRoleId()){
                userRoleMapper.update(userRole);
            }else{
                userRoleMapper.insert(userRole);
            }
        }

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

    @Override
    public void insertRoles(List<UserRole> userRoles,Integer pid) throws Exception{

        if(userRoles.size()!=0){
            if(pid!=null&&pid!=0){
                User user = new User();
                user.setId(userRoles.get(0).getUserId());
                user.setPid(pid);
                userMapper.updateByPrimaryKeySelective(user);
            }
            userRoleMapper.deleteUserRoles(userRoles.get(0).getUserId());
            userRoleMapper.insertRoles(userRoles);
        }
    }

    @Override
    @Transactional
    public void insertRoleFunction(List<String> functions, int roleId) {
        roleFunctionMapper.deleteRoleFunction(roleId);
        for(String fun:functions){
            RoleFunction roleFunction = new RoleFunction();
            roleFunction.setRoleId(roleId);
            roleFunction.setFunctionId(Integer.parseInt(fun));
            roleFunctionMapper.insert(roleFunction);
        }
    }

    @Override
    public void editRole(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void delRole(int id){
        roleMapper.deleteByPrimaryKey(id);
    }
}
