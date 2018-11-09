package com.hz.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 
 * 1.自定义角色鉴权过滤器(满足其中一个角色则认证通过) 2.扩展异步请求认证提示功能;
 * 
 * @author shadow
 * 
 */
public class AjaxAuthorizationFilter extends AuthorizationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		// super.onAccessDenied(request, response);
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String token = httpRequest.getHeader("token");
		String url = httpRequest.getRequestURI();
		if("/api/sys/login".equals(url)){
			return true;
		}
		Subject subject = getSubject(request, response);
		Session session = subject.getSession();
		System.out.println(session);
		String token1 = (String) session.getAttribute("token");
		// If the subject isn't identified, redirect to login URL
		if (subject.getPrincipal() == null||token==null||!token.equals(token1)) {
			if (isAjaxRequest(httpRequest)) {

			} else {
				saveRequestAndRedirectToLogin(request, response);
			}
		} else {
				return true;
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