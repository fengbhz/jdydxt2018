package com.vdong.commons.filter;


import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain arg2) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		arg2.doFilter(request, response);
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	

}
