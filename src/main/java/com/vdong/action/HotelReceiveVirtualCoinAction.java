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
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class HotelReceiveVirtualCoinAction extends HttpServlet {

	public HotelReceiveVirtualCoinAction() {
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
		String checkNum = (request.getParameter("checkNum")==null?"":request.getParameter("checkNum"));
		String account = (request.getParameter("account")==null?"":request.getParameter("account"));
		
		String infoSql = "select account, sum from hotelgive where checkNum = '" + checkNum + "' and status = '0'";
		Connection conn = DBManager.getConnection();
		PreparedStatement psselect;
		PreparedStatement psreceive;
		PreparedStatement psgive;
		PreparedStatement psrecord;
		Map map = new HashMap();
		try {
			psselect = conn.prepareStatement(infoSql);
			ResultSet rs = psselect.executeQuery();
			String originAccount = "";
			String sum = "";
			while (rs.next()) {
				originAccount = rs.getString("account");
				sum = rs.getString("sum");
			}
			if(!originAccount.equals("")){
				String receiveSql = "update hoteluser set virtualCoin = (CAST(virtualCoin AS UNSIGNED INT) + CAST('" + sum + "' AS UNSIGNED INT)) where account = '" + account + "'";
				String giveSql =    "update hoteluser set virtualCoin = (CAST(virtualCoin AS UNSIGNED INT) - CAST('" + sum + "' AS UNSIGNED INT)), lockVirtualCoin = (CAST(lockVirtualCoin AS UNSIGNED INT) - CAST('" + sum + "' AS UNSIGNED INT)) where account = '" + originAccount + "'";
				String recodeSql =  "update hotelgive set status = '1',  receiveAccount ='"+account+"', receiveTime = sysdate() where checkNum = '" + checkNum + "' and status = '0'";
				psreceive = conn.prepareStatement(receiveSql);
				psgive = conn.prepareStatement(giveSql);
				psrecord = conn.prepareStatement(recodeSql);
				psreceive.execute();
				psgive.execute();
				psrecord.execute();
				map.put("flag", "success");
			}else{
				map.put("flag", "false");
				map.put("msg", "没有此验证码！");
			}
			conn.close();
		} catch (SQLException e) {
			map.put("flag", "false");
			map.put("msg", "操作错误！");
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		JSONObject resultObj = JSONObject.fromObject(map);
		out.println(resultObj.toString());
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		
	}

}
