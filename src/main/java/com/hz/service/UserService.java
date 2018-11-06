package com.hz.service;

import com.hz.domain.*;
import com.hz.util.page.PageRequest;

import java.util.List;

public interface UserService {


    User getUser(long id);

    User getUserInfo(int id);

    User findByUserName(String name);

    List<UserRole> findByUid(int uid);

    List<Role> getRoleList(int uid);

    List<Function> getFunctionList(int uid);

    User selectRoleByUserName(String name);

    void updateUser(User user);

    List<User> getUserList(User user, PageRequest pageRequest);

    void deleteUserById(int id);

    void createUser(User user);
}
