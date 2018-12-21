package com.hz.shiro;

import com.alibaba.fastjson.JSONObject;
import com.hz.domain.User;
import com.hz.util.Constants;
import com.hz.util.MD5Util;
import com.hz.util.ResJson;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 1.自定义角色鉴权过滤器(满足其中一个角色则认证通过) 2.扩展异步请求认证提示功能;
 * 
 * @author shadow
 * 
 */
public class AjaxAuthorizationFilter extends AuthorizationFilter {

	@Override
	public boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		// super.onAccessDenied(request, response);
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		request.setCharacterEncoding("UTF-8");
		ResJson resJson = new ResJson();
		if(!isAvlidRequest(httpRequest)){
			resJson.setDesc("非法请求！！！！");
			resJson.setStatus(0);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter printWriter = response.getWriter();
			printWriter.write(JSONObject.toJSONString(resJson));
			return false;
		}
		String token = httpRequest.getHeader("token");
		String url = httpRequest.getRequestURI();
		if("/api/sys/login".equals(url)){
			return true;
		}
		Subject subject = getSubject(request, response);
		Session session = subject.getSession();
		System.out.println(session);
		String token1 = (String) session.getAttribute("token");
		User user = (User) session.getAttribute("user");
		if(user!=null&&user.getId()==1){
			return true;
		}
		Set<String> stringSet = (Set<String>) session.getAttribute("urls");
		// If the subject isn't identified, redirect to login URL
		if (subject.getPrincipal() == null) {
			if (isAjaxRequest(httpRequest)) {

			} else {
				resJson.setStatus(110);
				resJson.setDesc("登录信息失效,请重新登录！！");
			}
		}else if(!token.equals(token1)){
			resJson.setDesc("无效的token！！！");
			resJson.setStatus(0);
		}else if(token==null){
			resJson.setDesc("请求缺少token");
			resJson.setStatus(0);
		}else if(stringSet.isEmpty()||!getPermission(stringSet,url)){
			resJson.setDesc("没有配置权限，请联系系统管理员。@—|—@！");
			resJson.setStatus(0);
		} else{
			return true;

		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.write(JSONObject.toJSONString(resJson));
		return false;
	}


	boolean getPermission(Set<String> strings,String url){

		for(String url1:strings){
			if(url.contains(url1)){
				return true;
			}
		}
		return false;
	}

	static boolean isAvlidRequest(HttpServletRequest request){
		String timestamp = request.getHeader("timestamp");
		long currentTime = System.currentTimeMillis();
		long requetsTime = Long.parseLong(timestamp);
		if((currentTime-requetsTime)/1000>30){
			return false;
		}
		int salt_id = Integer.parseInt(request.getHeader("saltId"));
		String token = request.getHeader("token");
		String salt = (String)Constants.map.get(salt_id);
		String sdf = timestamp+"#"+salt+"$"+token;
		String md4 = MD5Util.encryptMD5(sdf);
		String tokenfake = request.getParameter("sessionId");
		if(tokenfake.equals(md4)){
			return true;
		}
		return false;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
	    System.out.println(request);
		return false;
	}

	/**
	 * 判断是否为Ajax请求
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return 是true, 否false
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With");
		if (requestType != null && requestType.equals("XMLHttpRequest")) {
			return true;
		} else {
			return false;
		}
	}

}