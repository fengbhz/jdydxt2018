package com.vdong.controller;

import com.vdong.commons.dao.HotelOwnerDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 成为业主校验
 * @author Administrator
 *
 */
@Controller
public class VailYzController {
	
@Autowired
private HotelOwnerDao hotelOwnerDao;
	
/**
 * 前端已有账号
 * @param request
 * @param response
 * @return
 * @throws IOException
 */
	@RequestMapping(value = { "platform/vailYz" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String vailYz(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		boolean flag=true;
		Map map=new HashMap();
		String phone=request.getParameter("phone");
		try {
			flag= hotelOwnerDao.bindingYz(phone);
			map.put("success", flag);
		} catch (Exception e) {
			map.put("success", false);
		}
		JSONObject  json= JSONObject.fromObject(map);
		return json.toString();
	}

}
