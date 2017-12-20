package com.vdong.action;

import com.vdong.commons.db.DBManager;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class HotelUserSelectAction extends HttpServlet {

	public HotelUserSelectAction() {
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String account = (request.getParameter("account")==null?"":request.getParameter("account"));
		String condition = "";
		if(!account.equals("")){
			condition += " and account = '" + account + "' ";
		}
		Map map = new HashMap();
		
		if(!account.equals("")){
			String sql = "SELECT id, name, mobile, idCard, userKind, virtualCoin, lockVirtualCoin, integral, wechat, belongHotelId, belongHotel, role, nickName, gender, language, city, province, country, avatarUrl, code FROM hoteluser where userStutus = '0' " + condition;
			System.out.println(sql);
			Connection conn = DBManager.getConnection();
			PreparedStatement pstmt;
			
			try {
				pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				
				List tempLst = new ArrayList();
				while (rs.next()) {
					Map m = new HashMap();
					m.put("id", rs.getString("id"));
					m.put("name", rs.getString("name"));
					m.put("mobile", rs.getString("mobile"));
					m.put("idCard", rs.getString("idCard"));
					m.put("userKind", rs.getString("userKind"));
					m.put("virtualCoin", rs.getString("virtualCoin"));
					m.put("lockVirtualCoin", rs.getString("lockVirtualCoin"));
					m.put("integral", rs.getString("integral"));
					m.put("wechat", rs.getString("wechat"));
					m.put("belongHotel", rs.getString("belongHotel"));
					m.put("role", rs.getString("role"));
					m.put("nickName", rs.getString("nickName"));
					m.put("gender", rs.getString("gender"));
					m.put("language", rs.getString("language"));
					m.put("city", rs.getString("city"));
					m.put("province", rs.getString("province"));
					m.put("country", rs.getString("country"));
					m.put("avatarUrl", rs.getString("avatarUrl"));
					m.put("code", rs.getString("code"));
					tempLst.add(m);
				}
				if(tempLst.size() > 0){
					map.put("rows", tempLst);
					map.put("flag", "succ");
				}else{
					map.put("flag", "no return records");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			map.put("flag", "err");
		}
		
		JSONObject resultObj = JSONObject.fromObject(map);
		out.println(resultObj.toString());
		
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
