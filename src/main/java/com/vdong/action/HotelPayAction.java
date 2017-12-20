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
public class HotelPayAction extends HttpServlet {

	public HotelPayAction() {
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
		String account = (request.getParameter("account")==null?"":request.getParameter("account"));
		String payWay = (request.getParameter("payWay")==null?"":request.getParameter("payWay"));
		String sum = (request.getParameter("sum")==null?"":request.getParameter("sum"));
		String alterOrderSql = "update hotelorder set payStatus = '1'  , orderStatus='2' "
							 + "where id='" + id + "'";
		Connection conn = DBManager.getConnection();
		PreparedStatement psinsert;
		PreparedStatement psupdate;
		try {
			psinsert = conn.prepareStatement(alterOrderSql);
			Map map = new HashMap();
			psinsert.execute();
			if(payWay.equals("1")){  // 这是度假币支付
				String alterAccountSql = "update hoteluser set virtualCoin = (CAST(virtualCoin AS UNSIGNED INT) - CAST('" + sum + "' AS UNSIGNED INT)) where account = '" + account + "' ";
				System.out.println("alterAccountSql--->"+alterAccountSql);
				psupdate = conn.prepareStatement(alterAccountSql);
				psupdate.execute();
			}
			map.put("flag", "success");
			map.put("total", "支付成功");
			
			
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
