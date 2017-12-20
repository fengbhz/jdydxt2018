package com.vdong.controller;

import com.vdong.services.OrderManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 订单管理
 * @author Administrator
 *
 */
@Controller
public class LoginController extends  BaseController {
	
@Autowired
private OrderManagerService orderManagerService;

@RequestMapping(value = { "platform/checkLogin" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
public String checkLogin(HttpServletRequest request,
		HttpServletResponse response) throws IOException {
	PrintWriter out = null;
	String msg = "";
	try {
		out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("userpwd");

		if ("".equals(username)) {
			msg = "用户名不能为空!";
			request.setAttribute("msg", msg);
			return "/admin/login";
		}
		if ("".equals(password)) {
			msg = "密码不能为空!";
			request.setAttribute("msg", msg);
			return "/admin/login";
		}

		int count = orderManagerService.checkLogin(username, password);
		if (count <= 0) {
			msg = "帐号或者密码错误!";
			request.setAttribute("msg", msg);
			return "/admin/login";

		}
		
    request.getSession().setAttribute("userLogin", getLoginUser());
    request.getSession().setAttribute("user", username);
    
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "redirect:/werchat/toMain.html";
}
	
	
/**
 * 
 *Description:      去菜单首页
 * @param request
 * @param response
 * @return
 * @throws IOException 
 * data: 2015-12-16
 * return  String
 */
	@RequestMapping(value = { "werchat/toMain" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String toMeanMain(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		 request.getSession().setAttribute("userLogin", getLoginUser());
		return "/admin/index";
	}
	

}
