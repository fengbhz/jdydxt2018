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
public class HotelCheckVirtualCoinAction extends HttpServlet {

	public HotelCheckVirtualCoinAction() {
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
		DBManager dbm = new DBManager();
		String account = (request.getParameter("account")==null?"":request.getParameter("account"));
		String vcSql = "SELECT virtualCoin, lockVirtualCoin FROM hoteluser WHERE account = '" + account + "'";
		
		System.out.println(vcSql);
		Connection conn = dbm.getConnection();
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(vcSql);
			ResultSet rs = pstmt.executeQuery();
			Map map = new HashMap();
			List vcLst = new ArrayList();
			while (rs.next()) {
				Map m = new HashMap();
				m.put("virtualCoin", rs.getString("virtualCoin"));
				m.put("lockVirtualCoin", rs.getString("lockVirtualCoin"));
				m.put("realityCoin", Integer.toString(Integer.parseInt(rs.getString("virtualCoin")) - Integer.parseInt(rs.getString("lockVirtualCoin"))));
				vcLst.add(m);
			}
			map.put("vcLst", vcLst);
			
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
