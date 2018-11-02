package com.hz.service;

import com.hz.domain.Function;
import com.hz.domain.Role;
import com.hz.domain.User;
import com.hz.domain.UserRole;

import java.util.List;

public interface UserService {


    User getUser(long id);

    User findByUserName(String name);

    List<UserRole> findByUid(int uid);

    List<Role> getRoleList(int uid);

    List<Function> getFunctionList(int uid);
}
