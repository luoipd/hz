package com.hz.shiro;

import com.alibaba.fastjson.JSONObject;
import com.hz.domain.User;
import com.hz.util.ResJson;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
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

		ServletContext sc = httpRequest.getSession().getServletContext();
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
		ResJson resJson = new ResJson();
		if (subject.getPrincipal() == null) {
			if (isAjaxRequest(httpRequest)) {

			} else {
				resJson.setStatus(0);
				resJson.setDesc("登录信息失效,请重新登录！！");
			}
		}else if(token==null||!token.equals(token1)){
			resJson.setDesc("该账号在另一个地方登录，请重新登录！！！");
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

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
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