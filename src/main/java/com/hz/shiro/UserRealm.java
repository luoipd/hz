package com.hz.shiro;

import com.hz.domain.Function;
import com.hz.domain.Role;
import com.hz.domain.User;
import com.hz.service.UserService;
import com.hz.util.Constants;
import com.hz.util.interceptor.Token;
import com.hz.util.interceptor.TokenUtil;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lenovo on  三月
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    private Logger logger=Logger.getLogger(UserRealm.class);
    /**
     * 提供用户信息，返回权限信息
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("---------------------------->授权认证：");
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        String userName=(String) principals.getPrimaryPrincipal();
        int userId=userService.findByUserName(userName).getId();
        List<Role> roleList = userService.getRoleList(userId);

        List<Function> functions = userService.getFunctionList(userId);


        Set<String> roleSet=new HashSet<String>();
//        Set<Integer>  pemissionIdSet=new HashSet<>();
        Set<String>  pemissionSet=new HashSet<String>();
        for(Function function : functions) {
//              int roleId=roleInfo.getRoleId();
//               roleSet.add( userService.findRoleByRoleId( roleId  ) );
//               //将拥有角色的所有权限放进Set里面，也就是求Set集合的并集
//              pemissionIdSet.addAll( userService.findPermissionIdByRoleId(  roleId ));
        }
        for(Function function : functions) {
            roleSet.add(String.valueOf(function.getRoleId()));
            pemissionSet.add(  function.getUrl() );
        }
         // 将角色名称提供给授权info
        authorizationInfo.setRoles( roleSet );
        // 将权限名称提供给info
        authorizationInfo.setStringPermissions(pemissionSet);

        return authorizationInfo;
    }

    /**
     * 提供帐户信息，返回认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;

        logger.info("验证当前Subject时获取到token为：" +ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));

        System.out.println(ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE)+"dafdsfadfadsf");


        //查出是否有此用户
        User user= userService.findByUserName(token.getUsername());

        if(user==null){
            throw new UnknownAccountException();
        }

        if(user!=null&&user.getStatus().equals(Constants.userStatus_2)){

            throw new  LockedAccountException();
        }
        if(user!=null&&user.getStatus().equals(Constants.userStatus_0)){

            throw new  DisabledAccountException();
        }

        if(user!=null&&user.getStatus().equals(Constants.userStatus_1)){
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验

            SimpleAuthenticationInfo simpleAuthorizationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), ByteSource.Util.bytes("www"),getName());
            Token token1 = TokenUtil.generateToken(user.getUsername(),user.getId());
            User user1 = new User();
            user1.setToken(token1.getSignature());
            user1.setId(user.getId());
            user1.setUpdateTime(new Date());
            userService.updateUser(user1);
            return  simpleAuthorizationInfo;
        }
        return null;
    }
}
