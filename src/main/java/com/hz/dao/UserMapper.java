package com.hz.dao;

import com.hz.domain.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    int insertUser(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserName(String name);

    List<User> selectUserList(User user);

    int countUserList(User user);

    User selectUserInfoByToken(String token);

    List<Integer> selectAllSaler(int id);

    List<User> getUserNameByRoleId(int roleId);

    User selectByUserName1(String name);
}