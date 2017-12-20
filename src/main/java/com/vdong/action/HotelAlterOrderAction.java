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

@SuppressWarnings("serial")
public class HotelAlterOrderAction extends HttpServlet {

	public HotelAlterOrderAction() {
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
		String id = (request.getParameter("id")==null?"":request.getParameter("id"));
		String custom = (request.getParameter("custom")==null?"":request.getParameter("custom"));
		String mobile = (request.getParameter("mobile")==null?"":request.getParameter("mobile"));
		String idCard = (request.getParameter("idCard")==null?"":request.getParameter("idCard"));
		String roomNum = (request.getParameter("roomNum")==null?"":request.getParameter("roomNum"));
		String checkInDate = (request.getParameter("checkInDate")==null?"":request.getParameter("checkInDate"));
		String checkOutDate = (request.getParameter("checkOutDate")==null?"":request.getParameter("checkOutDate"));
		String sum = (request.getParameter("sum")==null?"":request.getParameter("sum"));
		String payWay = (request.getParameter("payWay")==null?"":request.getParameter("payWay"));
		String alterOrderSql = "update hotelorder set name = decode('" + custom + "', '', name, '" + custom + "'), "
				             + " mobile = decode('" + mobile + "', '', mobile, '" + mobile + "'), "
				             + " idCard = decode('" + idCard + "', '', idCard, '" + idCard + "'), "
				             + " roomNum = decode('" + roomNum + "', '', roomNum, '" + roomNum + "'), "
				             + " checkInDate = decode('" + checkInDate + "', '', checkInDate, str_to_date('" + checkInDate + "','%Y-%m-%d')), "
				             + " checkOutDate = decode('" + checkOutDate + "', '', checkOutDate, str_to_date('" + checkOutDate + "','%Y-%m-%d')), "
				             + " sum = decode('" + sum + "', '', sum, '" + sum + "'), "
				             + " payWay = decode('" + payWay + "', '', payWay, '" + payWay + "') "
							 + " where id='" + id + "'";
		Connection conn = dbm.getConnection();
		PreparedStatement psinsert;
		try {
			psinsert = conn.prepareStatement(alterOrderSql);
			Map map = new HashMap();
			psinsert.execute();
			map.put("flag", "success");
			map.put("total", "成功修改下单");
			
			
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
