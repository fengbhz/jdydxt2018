package com.vdong.action;

import com.vdong.commons.message.SendMessage;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class HotelMessageAction extends HttpServlet {

	public HotelMessageAction() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		this.doPost(request, response);
		out.close();
	}
	
	@SuppressWarnings({ "rawtypes" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String mobile = (request.getParameter("mobile")==null?"":request.getParameter("mobile"));
		String checkCodeStr = (request.getParameter("checkCode")==null?"":request.getParameter("checkCode"));
		Map<String, String> jsmap=new HashMap<String, String>();
		Map<String, String> map=new HashMap<String, String>();
		
		  jsmap.put("code", checkCodeStr);
		  jsmap.put("tiem", "");
		  JSONObject  json=JSONObject.fromObject(jsmap);
		
		  map.put("phone", mobile);
		  map.put("moldId", "SMS_78655049");
		  map.put("json",json.toString());
		  SendMessage.sendMsg(map);
	}
		

	public void init() throws ServletException {
		// Put your code here
	}

}
