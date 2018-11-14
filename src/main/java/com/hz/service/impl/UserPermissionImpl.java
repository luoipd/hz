package com.hz.service.impl;

import com.hz.domain.Function;
import com.hz.domain.Role;
import com.hz.service.UserService;
import com.hz.shiro.UserPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/12
 */
@Service
public class UserPermissionImpl implements UserPermission {

    @Autowired
    public UserService userService;

//    @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
//    public void init() {
//        userPermission = this;
//        userPermission.userService = this.userService;
//        //初使化时将已静态化的accessTokenOcrRepository实例化
//    }

    public boolean getPermission(String token,String url){

        int userId=userService.findUserByToken(token).getId();
        List<Role> roleList = userService.getRoleList(userId);

        List<Function> functions = userService.getFunctionList(userId);


//        Set<String> roleSet=new HashSet<String>();
//        Set<Integer>  pemissionIdSet=new HashSet<>();
//        Set<String>  pemissionSet=new HashSet<String>();
        for(Function function : functions) {
//            roleSet.add(String.valueOf(function.getUrl()));
//            pemissionSet.add( function.getUrl());
            if(function.getUrl()==null||function.getUrl()==""){
                continue;
            }
            if(url.contains(function.getUrl())){
                return true;
            }
        }
        return false;
    }
}
