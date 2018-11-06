package com.hz.dao;

import com.hz.domain.RequestParams;
import com.hz.domain.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserName(String name);

    User selectRoleByUserName(String name);

    User selectUserInfoById(int id);

    List<User> selectUserList(User user);

    int countUserList(User user);
}