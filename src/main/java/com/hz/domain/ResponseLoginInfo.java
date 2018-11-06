package com.hz.domain;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/2
 */
public class ResponseLoginInfo {


    User user;

    List urlList;

    List<Function> modules;

    public List<Function> getModules() {
        return modules;
    }

    public void setModules(List<Function> modules) {
        this.modules = modules;
    }

    int status;

    String desc;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List getUrlList() {
        return urlList;
    }

    public void setUrlList(List urlList) {
        this.urlList = urlList;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
