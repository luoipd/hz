package com.hz.controller.usercenterController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hz.domain.User;
import com.hz.service.UserService;
import com.hz.util.RequestParams;
import com.hz.util.ResponseJson;
import com.hz.util.page.Pagination;
import com.hz.util.page.PaginationResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);


	@Autowired
	private UserService userService;


 


	@RequestMapping(value = "/index")
	public String index() {

		return "index";
	}
	@RequestMapping(value = "/")
	public String login() {

		return "login";
	}
	@RequestMapping(value = "/regist")
	public String regist() {
		
		return "regist";
	}

	@RequestMapping(value = "/forgetPwd")
	public String forgetPwd() {
		return "forgetPwd";
	}

	/**
	 * 修改密码
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/admin/user/password")
	@ResponseBody
	public String password(String password, String oldpassword, String username) {

		ResponseJson responseJson = new ResponseJson();
		User user = new User();
		try {
			user = userService.findByUserName(username);




			oldpassword = new Md5Hash(oldpassword, "www",1024).toBase64();


			if (!oldpassword.equals(user.getPassword())) {
				responseJson.setMsg("passwordError");
			} else {

				user.setPassword(new Md5Hash(password, "www", 1024).toBase64());

//				userService.save(user);
				responseJson.setMsg("success");
			}

		} catch (Exception e) {
			responseJson.setMsg("exception");

			logger.info(e.toString());
		}

		logger.info(JSONObject.toJSONString(responseJson));
		return JSONObject.toJSONString(responseJson);
	}



	/**
	 * 校验用户名或者邮箱是否存在
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/checkNameOrEmallExist")
	@ResponseBody
	public String checkNameOrEmallExist() {
		Map<String, String> map=new HashMap<String,String>();
//		ResponseJson responseJson = new ResponseJson();
//		try {
//			responseJson = userService.checkNameOrEmallExist(shiroUser);
//			if("true".equals(responseJson.getMsg())){
//
//				map.put("valid", "true");
//				return JSONObject.toJSONString(map);
//			}else{
//				map.put("valid", "false");
//				return JSONObject.toJSONString(map);
//			}
//
//		} catch (Exception e) {
//			map.put("valid", "true");
//			logger.info(e.toString());
//		}
//
//		logger.info(JSONObject.toJSONString(responseJson));
		return JSONObject.toJSONString(map);
	}



	/**
	 * 管理员在后台修改 用户 用户注册,企业用户注册，管理员添加本单位用户、上级用户。
	 * 用户类别：0-企业用户,1-本单位普通用户，2-系统管理员，3-上级单位
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/admin/editUser")
	@ResponseBody
	public String editUser(User shiroUser) {

		ResponseJson responseJson = new ResponseJson();

//		try {
//			responseJson = userService.editUser(shiroUser);
//
//			//begin 添加记录日志功能
//			Subject currentUser = SecurityUtils.getSubject();
//			String logOperatorName = (String) currentUser.getPrincipal();
//			//end   添加记录日志功能
//
//		} catch (Exception e) {
//			responseJson.setMsg("exception");
//			logger.info(e.toString());
//		}

		return JSONObject.toJSONString(responseJson);
	}

	/**
	 * 管理员在后台添加 用户 用户注册,企业用户注册，管理员添加本单位用户、上级用户。
	 * 用户类别：0-企业用户,1-本单位普通用户，2-系统管理员，3-上级单位
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/admin/addUser")
	@ResponseBody
	public String addUser(User shiroUser) {
		ResponseJson responseJson = new ResponseJson();
//		try {
//			responseJson = userService.addUser(shiroUser);
//
//			//begin 添加记录日志功能
//			Subject currentUser = SecurityUtils.getSubject();
//			String logOperatorName = (String) currentUser.getPrincipal();
//			if("success".equals(responseJson.getMsg())){
//			}
//
//			//end   添加记录日志功能
//
//		} catch (Exception e) {
//			responseJson.setMsg("exception");
//			logger.info(e.toString());
//		}
		return JSONObject.toJSONString(responseJson);
	}

	/**
	 * 获取role列表，以及当前用户role。
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/admin/toAddRolePage")
	@ResponseBody
	public String toAddRolePage(String username) {
		ResponseJson responseJson = new ResponseJson();


//		ShiroUser shiroUser = userService.findByUsername(username);
//		List<ShiroRole> roleListC = shiroUser.getRoleList();
//		List<ShiroRole> roleListA = shiroRoleService.findRoleList();
//
//		Map<Integer,Boolean> map =new HashMap<Integer,Boolean>();
//
//		for(int i=0;i<roleListC.size();i++){
//			map.put(roleListC.get(i).getId(), true);
//
//		}
//
//
//
//		List<UserRoleListCheck> list =new ArrayList<>();
//
//		for(int i=0;i<roleListA.size();i++){
//
//			UserRoleListCheck userRoleListCheck = new UserRoleListCheck();
//			userRoleListCheck.setUsername(shiroUser.getUsername());
//			userRoleListCheck.setRoleName(roleListA.get(i).getRoleName());
//
//			if(map.get(roleListA.get(i).getId())!=null){
//				userRoleListCheck.setCheck(true);
//			}
//			list.add(userRoleListCheck);
//
//		}

		logger.info(JSONObject.toJSONString(null));

		return JSONObject.toJSONString(null);
	}



	/**
	 * 用户角色修改
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/admin/editUserRole")
	@ResponseBody
	public String editUserRole(String username,String role) {

		logger.info(role);
		ResponseJson responseJson = new ResponseJson();

		try {
//			responseJson = userService.editUserRole(username, role);

			//begin 添加记录日志功能
			Subject currentUser = SecurityUtils.getSubject();
			String logOperatorName = (String) currentUser.getPrincipal();
			if("success".equals(responseJson.getMsg())){
			}

			//end   添加记录日志功能

		} catch (Exception e) {
			responseJson.setMsg("exception");
			responseJson.setSuccess(true);
			logger.info(e.toString());
		}

		return JSONObject.toJSONString(responseJson);
	}


	 /**
     * 删除
     *
     * @return
     */
    @RequestMapping(value = "/admin/user/del/{ids}")
    @ResponseBody
    String Del(@PathVariable("ids") String ids) {
        ResponseJson responseJson = new ResponseJson();
//
//
//        try {
//        	logger.info("ids====="+ids);
//            responseJson = userService.del(ids);
//
//            //begin 添加记录日志功能
//			Subject currentUser = SecurityUtils.getSubject();
//			String logOperatorName = (String) currentUser.getPrincipal();
//			//end   添加记录日志功能
//
//        } catch (Exception e) {
//        	logger.info(e.toString());
//            responseJson.setMsg(e.getMessage());
//
//       }
        return JSONObject.toJSONString(responseJson);
    }




	/**
	 * 验证用户登录名是否存在,如果不存在 返回success。
	 *
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/usernameExistValid")
	@ResponseBody
	public String usernameExistValid(String username) {

		ResponseJson responseJson = new ResponseJson();

//		ShiroUser shiroUserTemp = new ShiroUser();
//		shiroUserTemp = userService.findByUsername(username);
//		if (shiroUserTemp == null) {
//			responseJson.setMsg("success");
//		} else {
//			responseJson.setMsg("false");
//		}
		return JSONObject.toJSONString(responseJson);

	}



	/**
	 * 重置密码
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/resetPassword")
	@ResponseBody
	public String resetPassword(User shiroUser) {

		ResponseJson responseJson = new ResponseJson();

//		try {
//			logger.info("shiroUser.getUsername()=" + shiroUser.getUsername());
//			logger.info("shiroUser.getPassword()=" + shiroUser.getPassword());
//			String password = new Md5Hash(shiroUser.getPassword(), "www", 1024).toBase64();
//			shiroUser = userService.findByUsername(shiroUser.getUsername());
//
//			shiroUser.setPassword(password);
//			userService.save(shiroUser);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		responseJson.setMsg("success");

		return JSONObject.toJSONString(responseJson);

	}

	/**
	 * 审核
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/admin/audit")
	@ResponseBody
	public String audit(String auditStatus, String username) {

		ResponseJson responseJson = new ResponseJson();
//		try {
//
//				responseJson = userService.audit(username, auditStatus);
//				responseJson.setMsg("success");
//				//begin 添加记录日志功能
//				Subject currentUser = SecurityUtils.getSubject();
//				String logOperatorName = (String) currentUser.getPrincipal();
//				//end   添加记录日志功能
//
//		} catch (Exception e) {
//
//			responseJson.setMsg(e.getMessage());
//		}


		return JSONObject.toJSONString(responseJson);
	}




	/**
	 * 进入用户列表页面
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/table_managed")
	public String table_managed(Model model) {

		return "admin/table_managed";
	}

	/**
	 *
	 * 用户列表
	 *
	 * @return
	 */
	@RequestMapping(value = "/admin/findUserList")
	@ResponseBody
	String findListByUserName(@RequestBody RequestParams requestParams) {


		logger.info(requestParams.toString());
		return null;
//		PaginationResult<User> pageResonse = shiroUserService.findAllByPage(pagination);
//
//		JSONObject jsonObject = (JSONObject) JSON.toJSON(pageResonse);
//		logger.info(jsonObject.toJSONString());
//		return jsonObject.toJSONString(pageResonse, SerializerFeature.WriteDateUseDateFormat,
//				SerializerFeature.WriteMapNullValue);
	}

	/**
	 *
	 * 获取待修改用户的值
	 *
	 * @return
	 */
	@RequestMapping(value = "/admin/toEditUserPage")
	@ResponseBody
	String toEditUserPage(String username, HttpServletRequest request) {

//		ShiroUser shiroUser = userService.findByUsername(username);
//		logger.info(JSONObject.toJSONString(shiroUser));

		return JSONObject.toJSONString(null);

	}



	/**
	 *
	 * 进入强制修改密码，完善用户信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/admin/user/toInformationPage")
	public String toInformationPage(HttpServletRequest request) {

		Subject currentUser = SecurityUtils.getSubject();

		String username = (String) currentUser.getPrincipal();
		User shiroUser = userService.findByUserName(username);
		request.setAttribute("shiroUser", shiroUser);

		return "/admin/user/information";

	}



	@RequestMapping(value = "/md5")
	@ResponseBody
	public String md5w() {
		String md5 = new Md5Hash("111111", "www",1024).toBase64();
        return md5;
	}








	@RequestMapping(value = "/admin/logout")
	public String logout(RedirectAttributes redirectAttributes) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		// redirectAttributes.addFlashAttribute("message", "您已安全退出");
		return "redirect:/login";
	}

	@RequestMapping(value = "/resetPasswordError")
	public String resetPasswordError(RedirectAttributes redirectAttributes) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		// redirectAttributes.addFlashAttribute("message", "您已安全退出");
		return "/resetPasswordError";

	}



}