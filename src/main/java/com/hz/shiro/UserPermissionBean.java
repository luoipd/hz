package com.hz.shiro;

import com.hz.service.impl.UserPermissionImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lyp
 * @date 2018/11/12
 */
@Configuration
public class UserPermissionBean {

    @Bean("userPermission")
    public UserPermission getPermission(){
        return new UserPermissionImpl();
    }
}
