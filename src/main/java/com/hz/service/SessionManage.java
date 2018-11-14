package com.hz.service;

import com.hz.domain.User;
import com.hz.domain.responseBean.UserBean;
import org.apache.shiro.session.Session;

/**
 * @author lyp
 * @date 2018/11/12
 */
public interface SessionManage {


    void setSession(User user, Session session);
}
