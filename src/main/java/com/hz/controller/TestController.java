package com.hz.controller;

import com.hz.domain.User;
import com.hz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/testUser", method = RequestMethod.GET)
    public User findUser() {
        return userService.getUser(1);
    }





}
