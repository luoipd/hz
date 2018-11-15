package com.hz.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lyp
 * @date 2018/11/15
 */

public class BaseController {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
//    protected Session session = SecurityUtils.getSubject().getSession();

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
    }
}
