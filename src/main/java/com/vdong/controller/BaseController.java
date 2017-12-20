package com.vdong.controller;

import com.vdong.commons.message.SendMessage;
import com.vdong.commons.bean.LoginUser;
import com.vdong.services.OrderManagerService;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


@Controller
public class BaseController {
	
	
	@Autowired
	protected HttpServletRequest request;
	
	
	@Autowired
	private OrderManagerService orderManagerService;
	
	
	public LoginUser  getLoginUser(){
		LoginUser  loginUser=new LoginUser();
		String user_name=(String) request.getSession().getAttribute("user");
		// 获取用户
	List list=	orderManagerService.getUserMsg(user_name);
	if(null!=list&&list.size()>0){
		  Map rmap=(Map<String,String>)list.get(0);
			String roleCode = String.valueOf(null == rmap.get("code") ? "": rmap.get("code")); // 角色编号
			String roleName = String.valueOf(null == rmap.get("roleName") ? "": rmap.get("roleName")); // 角色编号
			String id = String.valueOf(null == rmap.get("id") ? "": rmap.get("id")); // 角色编号
			String name = String.valueOf(null == rmap.get("zhi") ? "": rmap.get("zhi")); // 角色编号
			String username = String.valueOf(null == rmap.get("userName") ? "": rmap.get("userName")); // 角色编号
			String tel = String.valueOf(null == rmap.get("tel") ? "": rmap.get("tel")); // 角色编号
			String roleId = String.valueOf(null == rmap.get("roleId") ? "": rmap.get("roleId")); // 角色编号
			loginUser.setCode(roleCode);
			loginUser.setId(Integer.valueOf(id));
			loginUser.setName(name);
			loginUser.setRoleId(Integer.valueOf(roleId));
			loginUser.setTel(tel);
			loginUser.setUserName(username);
	}
	return loginUser;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String sendMsg(HttpServletRequest request,HttpServletResponse response,String id) throws IOException {
		ClassPathResource cr = new ClassPathResource("config.properties");//会重新加载spring框架
	    Properties pros = new Properties();
		 pros.load(cr.getInputStream());
		Map remap=new HashMap();
		     List list= orderManagerService.getOrderDetail(id);
		     if(list!=null&&list.size()>0){
		    	 Map<String,String> map=(Map<String, String>) list.get(0);
		    	 if(StringUtils.isNotBlank(map.get("msgmap"))){
		    	 String maps[]=map.get("msgmap").split(",");
		    	 Map jsmap=new HashMap();
		    	 if(maps.length>0){
		    		 for (int i = 0; i < maps.length; i++) {
						if(StringUtils.isNotBlank(maps[i])){
							  if(maps[i].equals("tel")){
								  jsmap.put("tel", pros.getProperty("tel").toString());
							  }else{
								  jsmap.put(maps[i], String.valueOf(map.get(maps[i])));
							  }
						}
					}
		    	 }
		    	 JSONObject json=JSONObject.fromObject(jsmap);
		    	 remap.put("json", json.toString());
		    	 remap.put("phone", map.get("mobile"));
		    	 remap.put("moldId", map.get("moldcode"));
		    	 SendMessage.sendMsg(remap);
		     }
		     }
		    	// 调用工具类对短信内容进行封装
		return null;
	}
}
