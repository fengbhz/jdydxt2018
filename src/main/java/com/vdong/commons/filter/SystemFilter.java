package com.vdong.commons.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SystemFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("user");
		if (StringUtils.isNotBlank(username))
			chain.doFilter(request, response);
		else
			request.getRequestDispatcher("/admin/login.jsp").forward(req,
					resp);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
