package com.vdong.action;

import com.vdong.commons.util.HttpUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class SosoMapAction  extends  HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Map map = new HashMap();
		String adress = (request.getParameter("adress")==null?"":request.getParameter("adress"));
	    String key=	"TEUBZ-EZKH5-IX5IV-QXUHS-MUD63-3KBKA";
	    BufferedReader in = null;
        String result = "";
	    if(adress.equals("")){
	    	map.put("sucess", false);
	    	map.put("date", null);
	    }
	      String url="http://apis.map.qq.com/ws/geocoder/v1/?address="+URLEncoder.encode(adress, "utf-8")+"&key="+key;
	      String str=   HttpUtil.loadJSON(url);
	      JSONObject jsonObject=JSONObject.fromObject(str);
	        if(jsonObject.get("status").toString().equals("0")){  //  返回参数成功
	        	JSONObject json=JSONObject.fromObject(jsonObject.get("result"));
	        	map.put("sucess", true);
		    	map.put("date", json.get("location"));
	        }else{
	        	map.put("sucess", false);
		    	map.put("date", jsonObject.get("message"));
	        }
	   
			JSONObject resultObj = JSONObject.fromObject(map);
			out.println(resultObj.toString());
		    out.flush();
		    out.close();
	}
	
}
