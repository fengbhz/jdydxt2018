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
public class HotelCancelOrderAction extends HttpServlet {

	public HotelCancelOrderAction() {
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
		String id = (request.getParameter("id")==null?"":request.getParameter("id"));
		String cancelOrderSql = "update hotelorder set orderStatus = '1' where id = '" + id + "'";
		Connection conn = DBManager.getConnection();
		PreparedStatement psinsert;
		try {
			psinsert = conn.prepareStatement(cancelOrderSql);
			Map map = new HashMap();
			
			psinsert.execute();
			map.put("flag", "success");
			map.put("total", "成功取消预订");
			
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
