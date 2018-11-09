package com.hz.util;

/**
 * @author lyp
 * @date 2018/11/9
 */
public class UserException extends Exception {

    public UserException(){}
    public UserException(String msg){      //传递异常信息
        super(msg) ;
    }
}
