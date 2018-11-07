package com.hz.shiro.shiroDomain;

import java.lang.annotation.*;

/**
 * 获取Shiro当前用户
 * @author 张劲航
 * @see SessionUserArgumentResolver
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SessionUser {
}
