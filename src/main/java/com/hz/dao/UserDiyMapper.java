package com.hz.dao;

import com.hz.domain.UserDiy;

public interface UserDiyMapper {
    int insert(UserDiy record);

    int insertSelective(UserDiy record);

    void deleteUserDiyByUserId(int userId);
}