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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("serial")
public class HotelBecomeOwnerAction extends HttpServlet {

	public HotelBecomeOwnerAction() {
		super();
	}

	public void destroy() {
		super.destroy();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		this.doPost(request, response);
		out.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		DBManager dbm = new DBManager();
		String id = UUID.randomUUID().toString().substring(0, 32);
		String hasAccount = (request.getParameter("hasAccount")==null?"":request.getParameter("hasAccount"));
		String account = (request.getParameter("account")==null?"":request.getParameter("account"));
		String checkNum = (request.getParameter("checkNum")==null?"":request.getParameter("checkNum"));
		String ownerName = (request.getParameter("name")==null?"":request.getParameter("name"));
		String mobile = (request.getParameter("mobile")==null?"":request.getParameter("mobile"));
		String hotelName = (request.getParameter("hotelName")==null?"":request.getParameter("hotelName"));
		String idCard = (request.getParameter("idCard")==null?"":request.getParameter("idCard"));
		
		String ownerSql = "insert into hotelowner(id, account, name, mobile, idCard, kind, hotelName, status, applyStatus, inputDate, orderNo) select '"
				        + id + "', '"
				        + account + "', '"
				        + ownerName + "', '"
				        + mobile + "', '"+idCard+"', ifnull((select max(s.area) from hotelsupplier s where s.hotelName like '%" + hotelName + "%'), ''), '" 
				        + hotelName
				        + "', '0', '0', sysdate(), (select max(o.orderNo) + 1 from hotelowner o) "
				        + "from dual";
		
		System.out.println(ownerSql);
		Connection conn = dbm.getConnection();
		PreparedStatement psinsert;
		try {
			psinsert = conn.prepareStatement(ownerSql);
			Map map = new HashMap();
			
			psinsert.execute();
			map.put("flag", "success");
			map.put("total", "已提交申请");
			
			JSONObject resultObj = JSONObject.fromObject(map);
			out.println(resultObj.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		
	}

}
