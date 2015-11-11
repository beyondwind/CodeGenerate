package com.enjoyor.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.enjoyor.common.util.CustomSessionContext;
import com.enjoyor.pojo.SysUser;

public class SercurityFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		SysUser sysUser = (SysUser) ((HttpServletRequest) request).getSession()
				.getAttribute("CURRENTUSER");
		String path = ((HttpServletRequest) request).getServletPath();
		if (path.indexOf("login") > 0) { // 如果为进入登录页面，放行
			filterChain.doFilter(request, response);
		} else {
			if (sysUser == null) {// 用户为空，从session管理器中重新获取session读取用户
				String sessionId = ((HttpServletRequest) request)
						.getParameter("jsessionid");
				HttpSession session = CustomSessionContext.getInstance()
						.getSession(sessionId);
				if (session != null) {
					sysUser = (SysUser) session.getAttribute("CURRENTUSER");
				}
			}
			// 当重新读取用户后再次验证用户
			if (sysUser == null) {
				request.setAttribute("error_msg", "请重新登录");
				request.getRequestDispatcher("/login/loginPage.htm").forward(
						request, response);
			} else
				filterChain.doFilter(request, response);
		}
	}
}
