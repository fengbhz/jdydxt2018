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
public class HotelWelcomeAction extends HttpServlet {

	public HotelWelcomeAction() {
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
		String sloganSql =  "SELECT id, title, content FROM hotelwelcome WHERE id = 'welcome'";
		String turnPicSql = "SELECT id, itemPic FROM hotelroom where property = '0'";
		String kindSql =    "SELECT id, hotelKind FROM hotelkind where status = '0' ";
		//String  lbPic_sql=" select  itemPic from  hotelroom where  property='0' ";
		System.out.println(sloganSql);
		Connection conn = DBManager.getConnection();
		PreparedStatement pstmt;
		PreparedStatement pstp;
		PreparedStatement pskind;
		try {
			pstmt = conn.prepareStatement(sloganSql);
			ResultSet rs = pstmt.executeQuery();
			Map map = new HashMap();
			List slogan = new ArrayList();
			while (rs.next()) {
				Map m = new HashMap();
				m.put("id", rs.getString("id"));
				m.put("title", rs.getString("title"));
				m.put("content", rs.getString("content"));
				slogan.add(m);
			}
			map.put("slogan", slogan);
			
			pstp = conn.prepareStatement(turnPicSql);
			ResultSet rstp = pstp.executeQuery();
			List turnPic = new ArrayList();
			while (rstp.next()) {
				Map m = new HashMap();
				m.put("id", rstp.getString("id"));
				m.put("photo", rstp.getString("itemPic"));
				turnPic.add(m);
			}
			map.put("turnPic", turnPic);
			
			pskind = conn.prepareStatement(kindSql);
			ResultSet rskind = pskind.executeQuery();
			List kindLst = new ArrayList();
			while (rskind.next()) {
				Map m = new HashMap();
				m.put("id", rskind.getString("id"));
				m.put("hotelKind", rskind.getString("hotelKind"));
				kindLst.add(m);
			}
			map.put("kindLst", kindLst);
			
			JSONObject resultObj = JSONObject.fromObject(map);
			out.println(resultObj.toString());
			conn.close();
			pstp.close();
			pstmt.close();
			pskind.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
