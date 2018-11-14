package com.hz.service.impl;

import com.hz.domain.Function;
import com.hz.domain.User;
import com.hz.domain.responseBean.UserBean;
import com.hz.service.SessionManage;
import com.hz.service.UserService;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lyp
 * @date 2018/11/12
 */
@Service
public class SessionManagerImpl implements SessionManage {

    @Autowired
    UserService userService;


    public void setSession(User user, Session session){

        session.setAttribute("user",user);
        session.setAttribute("token",user.getToken());
        List<Function> functions = userService.getFunctionList(user.getId());
        Set<String> stringSet = new HashSet<String>();
        for(Function function : functions) {
//            roleSet.add(String.valueOf(function.getUrl()));
//            pemissionSet.add( function.getUrl());
            if(function.getUrl()==null||function.getUrl()==""){
                continue;
            }
            stringSet.add(function.getUrl());
        }
        session.setAttribute("urls",stringSet);
    }
}
