package com.hz.shiro;

/**
 * @author lyp
 * @date 2018/11/9
 */
public interface UserPermission {


    boolean getPermission(String token, String url);

}
