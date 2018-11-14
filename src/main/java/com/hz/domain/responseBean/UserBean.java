package com.hz.domain.responseBean;

import com.hz.domain.Role;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author lyp
 * @date 2018/11/13
 */
@Data
public class UserBean {

    private int id;
    private String username;
    private String name;
    private String token;
    private String department;
    private String picUrl;
    private String mobile;
    private List<Role> roles;
    private String roleName;
    private String email;
    private int sex;
    private Date birthday;
    private String describle;
    private int pid;
    private int status;
}
