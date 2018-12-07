package com.hz.service;

import com.github.pagehelper.PageInfo;
import com.hz.domain.*;
import com.hz.domain.responseBean.UserBean;
import com.hz.util.page.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {


    User getUser(long id);

    User getUserInfo(int id);

    User findByUserName(String name);

    User findUserByToken(String token);

    List<UserRole> findByUid(int uid);

    List<Role> getRoleList(int uid);

    List<Function> getFunctionList(int uid);

    void updateUser(User user);

    void updateUserAll(User user);

    PageInfo<User> getUserList(User user, PageRequest pageRequest);

    void deleteUserById(int id);

    void createUser(User user) throws Exception;

    int countUser(User user);

    List<Integer> getDailishangIdList();

    List<User> getRoluUserList(int roleId);
}
