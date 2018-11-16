package com.hz.controller.usercenterController;

/**
 * @author lyp
 * @date 2018/11/2
 */

import com.alibaba.fastjson.JSONObject;
import com.hz.controller.BaseController;
import com.hz.domain.*;
import com.hz.service.*;
import com.hz.service.impl.ImageService;
import com.hz.util.Constants;
import com.hz.util.FunctionTree;
import com.hz.util.ResJson;
import com.hz.util.UserException;
import com.hz.util.page.PageRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@RestController
public class HzUserController extends BaseController {


    @Autowired
    private UserService userService;

    @Autowired
    private FunctionService functionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SessionManage sessionManager;

    @Autowired
    private PictureVideoService pictureVideoService;

    @Autowired
    private ImageService imageService;

    private static final Logger logger = LoggerFactory.getLogger(HzUserController.class);


    @RequestMapping(value = "/api/sys/login", method = RequestMethod.GET)
    @ResponseBody
    public String login(@Valid User user, BindingResult bindingResult,
                        HttpServletRequest request) {
        if(bindingResult.hasErrors()){
            return "login";
        }
        ResJson resJson = new ResJson();
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

        } catch (UnknownAccountException uae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            resJson.setStatus(Constants.userStatus_3);
            resJson.setDesc("账户不存在");
            return JSONObject.toJSONString(resJson);
        } catch (IncorrectCredentialsException ice) {
            resJson.setStatus(Constants.userStatus_4);
            resJson.setDesc("密码不正确");
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            return JSONObject.toJSONString(resJson);
        } catch (LockedAccountException lae) {
            resJson.setStatus(Constants.userStatus_5);
            resJson.setDesc("账户已锁定");
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            return JSONObject.toJSONString(resJson);
        } catch (DisabledAccountException lae) {
            resJson.setStatus(Constants.userStatus_6);
            resJson.setDesc("账户未审核");
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户未审核");
            return JSONObject.toJSONString(resJson);
        } catch (ExcessiveAttemptsException eae) {
            resJson.setStatus(Constants.userStatus_7);
            resJson.setDesc("用户名或密码错误次数过多");
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            return JSONObject.toJSONString(resJson);

        } catch (AuthenticationException ae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            resJson.setStatus(Constants.userStatus_8);
            resJson.setDesc("用户名或密码不正确");
            return JSONObject.toJSONString(resJson);
        }
        // 验证是否登录成功
        if (currentUser.isAuthenticated()&&username!=null) {
            logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            User user1 = userService.findByUserName(username);
            sessionManager.setSession(user1,session);
            /* 获取该角色的有效url*/
            /*返回用户信息，包含有权限的接口，用户信息*/
            resJson.setDesc("success");
            resJson.setStatus(1);
            resJson.setData(user1);
            return JSONObject.toJSONString(resJson);
        } else {
			token.clear();
            resJson.setStatus(0);
            resJson.setDesc("权限认证无效，请重新登陆");
            return JSONObject.toJSONString(resJson);// "{\"success\":true,\"msg\":false,\"returnStr\":\""+reason+"\"}"
            // ;
        }
    }

    @RequestMapping(value = "/api/sys/userList", method = RequestMethod.GET)
    @ResponseBody
    public String getUserList(@Valid PageRequest page,@Valid User user){
        ResJson resJson = new ResJson();
        List<User> users = userService.getUserList(user,page);
        int count = userService.countUser(user);
        Map map = new HashMap();
        map.put("users",users);
        map.put("total",count);
        resJson.setData(map);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/sys/getUserInfoById", method = RequestMethod.GET)
    @ResponseBody
    public String getUserInfoById(@RequestParam("uid") int uid){
        ResJson resJson = new ResJson();
        User user = userService.getUserInfo(uid);
        user.setPassword(null);
        resJson.setData(user);
        return JSONObject.toJSONString(resJson);
    }


    @RequestMapping(value = "/api/sys/editUser",method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(@Valid User user, @Valid MultipartFile file){
        ResJson resJson = new ResJson();
        if(user.getPassword()!=null){
            String password = new Md5Hash(user.getPassword(), "www", 1024).toBase64();
            user.setPassword(password);
        }
        int picId = imageService.insertPictureFile(file,pictureVideoService,sysUser);
        if(picId>0){
            user.setPicId(picId);
        }
        if(picId==0){
            log.error("图片上传失败");
            resJson.setStatus(0);
            resJson.setDesc("图片上传失败!!");
            return JSONObject.toJSONString(resJson);
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
    @RequestMapping(value = "/api/sys/delUser",method = RequestMethod.DELETE)
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

    @RequestMapping(value = "/api/sys/createUser",method = RequestMethod.POST)
    @ResponseBody
    public String createUser(@Valid User user,@Valid MultipartFile file){
        ResJson resJson = new ResJson();
        if(user.getPassword()!=null){
            String password = new Md5Hash(user.getPassword(), "www", 1024).toBase64();
            user.setPassword(password);
        }else{
            resJson.setStatus(0);
            resJson.setDesc("请设置密码");
            return JSONObject.toJSONString(resJson);
        }
        int picId = imageService.insertPictureFile(file,pictureVideoService,sysUser);
        if(picId>0){
            user.setPicId(picId);
        }
        if(picId==0){
            log.error("图片上传失败");
            resJson.setStatus(0);
            resJson.setDesc("图片上传失败!!!");
            return JSONObject.toJSONString(resJson);
        }
        if(picId==-1){
            user.setPicId(1);//设置默认图片
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
    @RequestMapping(value = "/api/sys/roleManage",method = RequestMethod.POST)
    @ResponseBody
    public String insertRole(@Valid String userRoles) throws Exception{
        ResJson resJson = new ResJson();
        List<UserRole> userRoles1 = JSONObject.parseArray(userRoles,UserRole.class);
        try {
            roleService.insertRoles(userRoles1);
        } catch (UserException e) {
            e.printStackTrace();
            resJson.setStatus(1);
            resJson.setDesc(e.getMessage());
        }
        return JSONObject.toJSONString(resJson);
    }

    /*
    获取角色列表
     */
    @RequestMapping(value = "/api/sys/roleList",method = RequestMethod.GET)
    @ResponseBody
    public String getRoleList(@Valid Role role){

        ResJson resJson = new ResJson();
        List<Role> roles= roleService.getRoleList(role);
        resJson.setData(roles);
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 角色新增
     * @param role
     * @return
     */
    @RequestMapping(value = "/api/sys/createRole",method = RequestMethod.POST)
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
     * 角色修改
     */
    @RequestMapping(value = "/api/sys/editRole",method = RequestMethod.POST)
    @ResponseBody
    public String editRole(@Valid Role role){
        ResJson resJson = new ResJson();
        if(role.getId()==null||role.getId()==0){
            resJson.setStatus(0);
            resJson.setDesc("缺少主键！！");
            return JSONObject.toJSONString(resJson);

        }
        roleService.editRole(role);
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 角色删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/sys/delRole",method = RequestMethod.POST)
    @ResponseBody
    public String delRole(@Valid int id){
        ResJson resJson = new ResJson();
        if(id==0){
            resJson.setStatus(0);
            resJson.setDesc("缺少主键！！");
            return JSONObject.toJSONString(resJson);

        }
        roleService.delRole(id);
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 根据角色id获取模块
     * @param roleId
     * @param pId
     * @return
     */
    @RequestMapping(value = "/api/sys/getFunctionList",method = RequestMethod.GET)
    @ResponseBody
    public String getFunctionList(@Valid int roleId,@Valid int pId){
        ResJson resJson = new ResJson();
        List<FunctionTree> functions =  functionService.selectFunctionByRolePid(roleId,pId);
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
    @RequestMapping(value = "/api/sys/addFunction",method = RequestMethod.POST)
    @ResponseBody
    public String addFunction(@Valid Function function){
        ResJson resJson = new ResJson();
        if(function.getFunctionName()!=null&&function.getPid()!=null){
            if(function.getUrl()==null){
                if(function.getLevel()==null){
                    resJson.setStatus(0);
                    resJson.setDesc("请传入数据level!");
                    return JSONObject.toJSONString(resJson);
                }
            }else{
                function.setLevel(0);
            }
            functionService.addFunction(function);
        }else{
            resJson.setStatus(0);
            resJson.setDesc("功能新增失败,缺少父id或者名称");
        }
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/sys/delFunction",method = RequestMethod.POST)
    @ResponseBody
    public String delFunction(@Valid int id){
        ResJson resJson = new ResJson();
        functionService.delFunction(id);
        resJson.setStatus(1);
        resJson.setDesc("删除成功");
        return JSONObject.toJSONString(resJson);
    }


    /**
     * 修改模块
     * @return
     */
    @RequestMapping(value = "/api/sys/editFunction",method = RequestMethod.POST)
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
     * 删除模块
     */
    /**
     * 角色添加权限
     * @return
     */
    @RequestMapping(value = "/api/sys/addRoleFunction",method = RequestMethod.POST)
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
    @RequestMapping(value = "/api/sys/insertRoleFunction",method = RequestMethod.POST)
    @ResponseBody
    public String insertRoleFunction(@RequestParam("functions") List<String> functions,@RequestParam("roleId") int roleId){
        ResJson resJson = new ResJson();
        try{

            roleService.insertRoleFunction(functions,roleId);
        }catch (Exception e){
            e.printStackTrace();
            resJson.setStatus(0);
            resJson.setDesc("功能修改失败");

        }
        return JSONObject.toJSONString(resJson);
    }


    @RequestMapping(value = "/api/sys/getUserInfo",method = RequestMethod.GET)
    @ResponseBody
    public String getUserInfo(@RequestParam("uid") int uid){
        ResJson resJson = new ResJson();
        User user = userService.getUserInfo(uid);
        user.setPassword(null);
        List<Function> function = new ArrayList<Function>();
        for(Role role:user.getRoles()){
            function.addAll(functionService.selectFunctionsByRoleId(role.getId()));
        }
        Map map = new HashMap();
        map.put("user",user);
        map.put("modules",function);

        resJson.setData(map);
        return JSONObject.toJSONString(resJson);
    }


    @RequestMapping(value = "/api/sys/getModuleList",method = RequestMethod.GET)
    @ResponseBody
    public String getModuleList(@RequestParam("pId") int pModule){
        ResJson resJson = new ResJson();
        List<FunctionTree> function = functionService.selectFunctionByPid(pModule);
        resJson.setData(function);
        return JSONObject.toJSONString(resJson);

    }

    @RequestMapping(value="/api/sys/listModule",method = RequestMethod.GET)
    @ResponseBody
    public String listModule(@RequestParam("pId") int pid){
        ResJson resJson = new ResJson();
        List<Function> function = functionService.getFunctionList(pid);
        resJson.setData(function);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value="/api/sys/listCheckedModule",method = RequestMethod.GET)
    @ResponseBody
    public String getModule(@Valid int roleId){
        ResJson resJson = new ResJson();
        List<String> datas = functionService.getCheckedFunction(roleId);
        resJson.setData(datas);
        return JSONObject.toJSONString(resJson);
    }
















}
