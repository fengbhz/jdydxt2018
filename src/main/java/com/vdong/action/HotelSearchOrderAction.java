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
public class HotelSearchOrderAction extends HttpServlet {

	public HotelSearchOrderAction() {
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		DBManager dbm = new DBManager();
		String hotelName = (request.getParameter("hotelName")==null?"":request.getParameter("hotelName"));
		String checkInDate = (request.getParameter("checkInDate")==null?"":request.getParameter("checkInDate"));
		String checkOutDate = (request.getParameter("checkOutDate")==null?"":request.getParameter("checkOutDate"));
		String account = (request.getParameter("account")==null?"":request.getParameter("account"));
		String condition = "";
		if(!hotelName.equals("")){
			condition += " and s.hotelName like '%" + hotelName + "%'";
		}
		if(!checkInDate.equals("")){
			condition += " and h.checkInDate = STR_TO_DATE('" + checkInDate + "','%Y-%m-%d')";
		}
		if(!checkOutDate.equals("")){
			condition += " and h.checkOutDate = STR_TO_DATE('" + checkOutDate + "','%Y-%m-%d')";;
		}
		if(!account.equals("")){
			condition += " and h.account = '" + account + "'";;
		}
		condition +="  order  by orderTime DESC  ";

				String sql = "SELECT h.id, h.orderId, h.itemId, r.roomName,r.virtualCoin, s.hotelName, h.name, h.mobile, h.idCard, h.roomNum, date_format(h.checkInDate,'%Y-%m-%d') as checkInDate, date_format(h.checkOutDate,'%Y-%m-%d') as checkOutDate, h.sum, h.payStatus, h.payWay, h.orderStatus, date_format(h.orderTime,'%Y-%m-%d %H:%i:%s') as orderTime, s.address FROM hotelorder h, hotelsupplier s, hotelroom r WHERE r.pid = s.id AND r.id = h.itemId " + condition;
		System.out.println(sql);
		Connection conn = dbm.getConnection();
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			Map map = new HashMap();
			List tempLst = new ArrayList();
			while (rs.next()) {
				Map m = new HashMap();
				m.put("id", rs.getString("id"));
				m.put("orderId", rs.getString("orderId"));
				m.put("roomName", rs.getString("roomName"));
				m.put("hotelName", rs.getString("hotelName"));
				m.put("custom", rs.getString("name"));
				m.put("mobile", rs.getString("mobile"));
				m.put("idCard", rs.getString("idCard"));
				m.put("virtualCoin", rs.getString("virtualCoin"));
				m.put("itemId", rs.getString("itemId"));
				m.put("roomNum", rs.getString("roomNum"));
				m.put("checkInDate", rs.getString("checkInDate"));
				m.put("checkOutDate", rs.getString("checkOutDate"));
				m.put("sum", rs.getString("sum"));
				m.put("payStatus", rs.getString("payStatus"));
				m.put("payWay", rs.getString("payWay"));
				m.put("orderStatus", rs.getString("orderStatus"));
				m.put("orderTime", rs.getString("orderTime"));
				m.put("address", rs.getString("address"));
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
		
	}

}
