package com.hz.controller.usercenterController;

/**
 * @author lyp
 * @date 2018/11/2
 */

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hz.domain.*;
import com.hz.service.FunctionService;
import com.hz.service.RoleService;
import com.hz.service.UserService;
import com.hz.util.ResJson;
import com.hz.util.page.PageRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HzUserController {


    @Autowired
    private UserService userService;

    @Autowired
    private FunctionService functionService;

    @Autowired
    private RoleService roleService;

    private static final Logger logger = LoggerFactory.getLogger(HzUserController.class);


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String login(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                        HttpServletRequest request) {
        if(bindingResult.hasErrors()){
            return "login";
        }
        ResJson resJson = new ResJson();
        ResponseLoginInfo responseJson = new ResponseLoginInfo();
        String username = user.getUsername();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        token.setRememberMe(true);
        // 获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        try {
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(interceptor)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            logger.info("对用户[" + username + "]进行登录验证..验证开始");
            currentUser.login(token);

            logger.info("对用户[" + username + "]进行登录验证..验证通过");

            //begin 添加记录日志功能
            String logOperatorName = (String) currentUser.getPrincipal();
            //end   添加记录日志功能

        } catch (UnknownAccountException uae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            redirectAttributes.addFlashAttribute("message", "账户不存在");
        } catch (IncorrectCredentialsException ice) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            redirectAttributes.addFlashAttribute("message", "密码不正确");
        } catch (LockedAccountException lae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            redirectAttributes.addFlashAttribute("message", "账户已锁定");
        } catch (DisabledAccountException lae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户未审核");
            redirectAttributes.addFlashAttribute("message", "账户未审核");
        } catch (ExcessiveAttemptsException eae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            // 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }
        // 验证是否登录成功
        if (currentUser.isAuthenticated()) {
            logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");

            SavedRequest re = WebUtils.getAndClearSavedRequest(request);

            logger.info("redirect:" + ((re == null || re.getRequestUrl() == null) ? "/index"
                    : re.getRequestUrl()));
            String url = ((re == null || re.getRequestUrl() == null) ? "/index"
                    : re.getRequestUrl());

            User user1 = userService.findByUserName(username);
            session.setAttribute("user",user1);
            /* 获取该角色的有效url*/
            /*返回用户信息，包含有权限的接口，用户信息*/
            resJson.setDesc("success");
            resJson.setStatus(1);
            resJson.setData(user1);
//            RedisCache redisCache = new RedisCache();
//            redisCache.put("user",user1);
            return JSONObject.toJSONString(resJson);
        } else {
			token.clear();
            Map<String, String> map = (Map) redirectAttributes.getFlashAttributes();
//            String reason = map.get("message");
            responseJson.setStatus(0);
            responseJson.setDesc("登录失败");
            resJson.setStatus(0);
            resJson.setDesc("登录失败");
            return JSONObject.toJSONString(resJson);// "{\"success\":true,\"msg\":false,\"returnStr\":\""+reason+"\"}"
            // ;
        }
    }

    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    @ResponseBody
    public String getUserList(@Valid PageRequest page,@Valid User user){
        ResJson resJson = new ResJson();
        List<User> users = userService.getUserList(user,page);
        resJson.setData(users);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/editUser",method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(@Valid User user){
        ResJson resJson = new ResJson();
        if(user.getPassword()!=null){
            String password = new Md5Hash(user.getPassword(), "www", 1024).toBase64();
            user.setPassword(password);
        }
        try{
            userService.updateUser(user);

        }catch (Exception e){
            e.printStackTrace();
            resJson.setStatus(0);
            resJson.setDesc("数据更新失败");
        }
        return JSONObject.toJSONString(resJson);
    }
    @RequestMapping(value = "/delUser",method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUser(@Valid int id){
        ResJson resJson = new ResJson();
        try{
            userService.deleteUserById(id);

        }catch (Exception e){
            e.printStackTrace();
            resJson.setStatus(0);
            resJson.setDesc("删除失败");
        }
        return JSONObject.toJSONString(resJson);

    }

    @RequestMapping(value = "/createUser",method = RequestMethod.POST)
    @ResponseBody
    public String createUser(@Valid User user){
        ResJson resJson = new ResJson();
        if(user.getPassword()!=null){
            String password = new Md5Hash(user.getPassword(), "www", 1024).toBase64();
            user.setPassword(password);
        }
        try{
            userService.createUser(user);

        }catch (Exception e){
            e.printStackTrace();
            resJson.setStatus(0);
            resJson.setDesc("新增失败");
        }

        return JSONObject.toJSONString(resJson);
    }

    /*
        给用户新增角色
     */
    @RequestMapping(value = "/roleManage",method = RequestMethod.POST)
    @ResponseBody
    public String insertRole(@Valid UserRole userRole){
        ResJson resJson = new ResJson();
        if(userRole.getRoleId()==null||userRole.getUserId()==null){
            resJson.setDesc("用户id,角色id不能为空");
            resJson.setStatus(0);
            return JSONObject.toJSONString(resJson);
        }
        try{
            roleService.insertRole(userRole);

        }catch (Exception e){
            e.printStackTrace();
            resJson.setStatus(0);
            resJson.setDesc("新增失败");
        }
        return JSONObject.toJSONString(resJson);
    }

    /*
    获取角色列表
     */
    @RequestMapping(value = "/roleList",method = RequestMethod.GET)
    @ResponseBody
    public String getRoleList(@Valid Role role){

        ResJson resJson = new ResJson();
        List<Role> roles= roleService.getRoleList(role);
        resJson.setData(roles);
        return JSONObject.toJSONString(resJson);
    }

    /*
    新增角色
     */
    @RequestMapping(value = "/createRole",method = RequestMethod.POST)
    @ResponseBody
    public String insertRole(@Valid Role role){

        ResJson resJson = new ResJson();
        try{
            roleService.insertManageRole(role);
        }catch (Exception e){
            e.printStackTrace();
            resJson.setStatus(0);
            resJson.setDesc("新增失败");
        }

        return JSONObject.toJSONString(resJson);
    }

    /**
     * 根据角色id获取模块
     * @param roleId
     * @param pId
     * @return
     */
    @RequestMapping(value = "/getFunctionList",method = RequestMethod.GET)
    @ResponseBody
    public String getFunctionList(@Valid int roleId,@Valid int pId){
        ResJson resJson = new ResJson();
        List<Function> functions =  functionService.selectFunctionByRolePid(roleId,pId);
        resJson.setData(functions);
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 给角色新增模块(error)
     * @return
     */
    @RequestMapping(value = "functionManage",method = RequestMethod.POST)
    @ResponseBody
    public String functionManage(@Valid RoleFunction roleFunction){
        ResJson resJson = new ResJson();
        try{
            functionService.insertFunction(roleFunction);
        }catch (Exception e){
            e.printStackTrace();
            resJson.setStatus(0);
            resJson.setDesc("角色功能新增失败");

        }
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 新增模块
     * @return
     */
    @RequestMapping(value = "/addFunction",method = RequestMethod.POST)
    @ResponseBody
    public String addFunction(@Valid Function function){
        ResJson resJson = new ResJson();
        try{
            functionService.addFunction(function);
        }catch (Exception e){
            e.printStackTrace();
            resJson.setStatus(0);
            resJson.setDesc("功能新增失败");

        }
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 修改模块
     * @return
     */
    @RequestMapping(value = "/editFunction",method = RequestMethod.POST)
    @ResponseBody
    public String editFunction(@Valid Function function){
        ResJson resJson = new ResJson();
        try{
            functionService.editFunction(function);
        }catch (Exception e){
            e.printStackTrace();
            resJson.setStatus(0);
            resJson.setDesc("功能修改失败");

        }
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 角色添加权限
     * @return
     */
    @RequestMapping(value = "/addRoleFunction",method = RequestMethod.POST)
    @ResponseBody
    public String addRoleFunction(@Valid List<RoleFunction> roleFunctions){
        ResJson resJson = new ResJson();
        try{

            roleService.addRoleFunction(roleFunctions);
        }catch (Exception e){
            e.printStackTrace();
            resJson.setStatus(0);
            resJson.setDesc("功能修改失败");

        }
        return JSONObject.toJSONString(resJson);
    }
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
    @ResponseBody
    public String getUserInfo(@RequestParam("uid") int uid){
        ResJson resJson = new ResJson();
        User user = userService.getUserInfo(uid);
        List<Function> function = new ArrayList<Function>();
        for(Role role:user.getRoles()){
            function.addAll(functionService.selectFunctionsByRoleId(role.getId()));
        }
        Map map = new HashMap();
        map.put("user",user);
        map.put("modules",function);
        user.setPassword(null);
        resJson.setData(map);
        return JSONObject.toJSONString(resJson);
    }
    @RequestMapping(value = "/test/getUserInfo",method = RequestMethod.GET)
    @ResponseBody
    public String getUserInfoTest(@RequestParam("uid") int uid){


        ResJson resJson = new ResJson();
        User user = userService.getUserInfo(uid);
        List<Function> function = new ArrayList<Function>();
        for(Role role:user.getRoles()){
            function.addAll(functionService.selectFunctionsByRoleId(role.getId()));
        }
        Map map = new HashMap();
        map.put("user",user);
        map.put("modules",function);
        user.setPassword(null);
        resJson.setData(map);
        return JSONObject.toJSONString(resJson);
    }


    @RequestMapping(value = "/getModuleList",method = RequestMethod.GET)
    @ResponseBody
    public String getModuleList(@RequestParam("pModule") int pModule){
        ResJson resJson = new ResJson();
        List<FunctionTreeBean> function = functionService.selectFunctionByPid(pModule);
        resJson.setData(function);
        return JSONObject.toJSONString(function, SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteMapNullValue);

    }




}
