package com.hz.service.impl;

import com.github.pagehelper.PageHelper;
import com.hz.dao.*;
import com.hz.domain.*;
import com.hz.service.UserService;
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

    @Autowired
    PictureVideoMapper pictureVideoMapper;


    public User getUser(long id){
        return userMapper.selectByPrimaryKey((int) id);
    }

    public User getUserInfo(int id){
        User user = userMapper.selectByPrimaryKey(id);
        PictureVideo pictureVideo = pictureVideoMapper.selectByPrimaryKey(user.getPicId());
        user.setPicUrl(pictureVideo.getUrl());
        user.setPicId(null);
        List<Role> roles = roleMapper.getRoleList(user.getId());
        user.setRoles(roles);
        return user;
    }

    public User findByUserName(String name){
        User user = userMapper.selectByUserName(name);
        List<Role> roles = roleMapper.getRoleList(user.getId());
        user.setRoles(roles);
        return user;
    }

    @Override
    public User findUserByToken(String token) {
        return userMapper.selectUserInfoByToken(token);
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
            List<Function> functions1 = functionMapper.getFunctionsByRoleId1(role.getId());
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
        //获取角色信息
        for(User user1:users){
            List<Role> roles = roleMapper.getRoleList(user1.getId());
            user1.setRoles(roles);
            if(roles.size()==0){
                break;
            }
            StringBuffer roleName = new StringBuffer();
            for(Role role:roles){
                roleName.append(role.getRoleName()+"/");
            }
            user1.setRoleName(roleName.substring(0,roleName.length()-1));
        }
        return users;
    }

    public int countUser(User user){
        int counts = userMapper.countUserList(user);
        return counts;
    }

    public void deleteUserById(int id){
        userMapper.deleteByPrimaryKey(id);
    }

    public void createUser(User user) throws Exception{
        if(user.getUsername().isEmpty()){
            throw new Exception();
        }
        userMapper.insertUser(user);
    }


}
