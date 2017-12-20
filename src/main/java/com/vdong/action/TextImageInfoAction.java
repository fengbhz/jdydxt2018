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
public class TextImageInfoAction extends HttpServlet {

	public TextImageInfoAction() {
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
		String key = (request.getParameter("key")==null?"":request.getParameter("key"));
		String condition = "";
		if(!key.equals("")){
			condition = " where adKind like '%" + key + "%' or adTitle like '%" + key + "%'";
		}
		String sql = "SELECT id, adKind, adTitle, adPic, adContent, DATE_FORMAT(adSubmitDate, '%Y-%m-%d') AS adSubmitDate, adStatus FROM hotelad" + condition;
		System.out.println(sql);
		Connection conn = DBManager.getConnection();
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			Map map = new HashMap();
			List tempLst = new ArrayList();
			while (rs.next()) {
				Map m = new HashMap();
				m.put("id", rs.getString("id"));
				m.put("adKind", rs.getString("adKind"));
				m.put("adTitle", rs.getString("adTitle"));
				m.put("adPic",  rs.getString("adPic"));
				m.put("adContent", rs.getString("adContent"));
				m.put("adSubmitDate", rs.getString("adSubmitDate"));
				m.put("adStatus", rs.getString("adStatus"));
				tempLst.add(m);
			}
			map.put("rows", tempLst);
			map.put("total", tempLst.size());
			JSONObject resultObj = JSONObject.fromObject(map);
			out.println(resultObj.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
