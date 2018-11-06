package com.hz.service.impl;

import com.github.pagehelper.PageHelper;
import com.hz.dao.FunctionMapper;
import com.hz.dao.RoleMapper;
import com.hz.dao.UserMapper;
import com.hz.dao.UserRoleMapper;
import com.hz.domain.*;
import com.hz.service.UserService;
import com.hz.util.page.PageBean;
import com.hz.util.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lyp
 * @date 2018/10/30
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    FunctionMapper functionMapper;

    public User getUser(long id){
        return userMapper.selectByPrimaryKey((int) id);
    }

    public User getUserInfo(int id){
        return userMapper.selectUserInfoById(id);
    }

    public User findByUserName(String name){
        return userMapper.selectByUserName(name);
    }

    public User selectRoleByUserName(String name){
        return userMapper.selectRoleByUserName(name);
    }

    public List<UserRole> findByUid(int uid){
        return userRoleMapper.userRoles(uid);
    }

    public List<Role> getRoleList(int uid){
        return roleMapper.getRoleList(uid);
    }

    public List<Function> getFunctionList(int uid){

        List<Role> roles = getRoleList(uid);
        List<Function> functions = new ArrayList<Function>();
        for(Role role: roles){
            List<Function> functions1 = functionMapper.getFunctionsByRoleId(role.getId());
            functions.addAll(functions1);
        }

        return functions;
    }

    public void updateUser(User user){

        userMapper.updateByPrimaryKeySelective(user);

    }

    public List<User> getUserList(User user, PageRequest pageRequest){

        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<User> users = userMapper.selectUserList(user);
        int countUsers = userMapper.countUserList(user);
        PageBean<User> pageData = new PageBean<User>(pageRequest.getPageNum(),pageRequest.getPageSize(),countUsers);
        pageData.setItems(users);
        return pageData.getItems();
    }

    public void deleteUserById(int id){
        userMapper.deleteByPrimaryKey(id);
    }

    public void createUser(User user){
        userMapper.insertSelective(user);
    }


}
