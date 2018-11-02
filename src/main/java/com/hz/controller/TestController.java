package com.hz.controller;

import com.hz.domain.User;
import com.hz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

    @Autowired
    private UserService userService;

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





}
