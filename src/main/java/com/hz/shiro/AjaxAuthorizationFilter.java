package com.hz.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
		Set<String> stringSet = (Set<String>) session.getAttribute("urls");
		// If the subject isn't identified, redirect to login URL
		if (subject.getPrincipal() == null||token==null||!token.equals(token1)||stringSet.isEmpty()||!getPermission(stringSet,url)) {
			if (isAjaxRequest(httpRequest)) {

			} else {
				saveRequestAndRedirectToLogin(request, response);
			}
		} else {
			return true;

		}


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