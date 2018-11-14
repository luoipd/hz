package com.hz.controller;

import com.hz.service.UserService;
import com.hz.shiro.UserPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPermission userPermission;

//    @RequestMapping(value = "/testUser", method = RequestMethod.GET)
//    public User findUser() {
//        return userService.getUser(1);
//    }
//    @RequestMapping(value = "/index1", method = RequestMethod.GET)
//    public String index(ModelMap modelMap) {
//        User user = userService.getUser(1);
//        modelMap.addAttribute("user",user);
//
//        return "/login";
//    }
    @RequestMapping(value = "/testUser", method = RequestMethod.GET)
    public String getTest(){
        userService.getUser(1);
        userPermission.getPermission("1","dfsadf");
        return "";
    }






}
