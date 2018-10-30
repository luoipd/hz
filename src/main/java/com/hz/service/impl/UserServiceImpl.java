package com.hz.service.impl;

import com.hz.dao.UserMapper;
import com.hz.domain.User;
import com.hz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lyp
 * @date 2018/10/30
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    public User getUser(long id){
        return userMapper.selectByPrimaryKey((int) id);
    }


}
