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
public class HotelGiveVirtualCoinAction extends HttpServlet {

	public HotelGiveVirtualCoinAction() {
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
		String id = UUID.randomUUID().toString().substring(0, 32);
		String checkNum = UUID.randomUUID().toString().substring(0, 12);
		String account = (request.getParameter("account")==null?"":request.getParameter("account"));
		String sum = (request.getParameter("sum")==null?"":request.getParameter("sum"));
		
		String giveSql = "update hoteluser set lockVirtualCoin = (CAST(lockVirtualCoin AS UNSIGNED INT) + CAST('" + sum + "' AS UNSIGNED INT)) where account = '" + account + "'";
		
		String recordSql = "insert into hotelgive(id, account, sum, checkNum, status, giveTime) select '" 
		                 + id + "', '"
		                 + account + "', '"
		                 + sum + "', '"
		                 + checkNum + "', '0', sysdate()"
		                 + " from dual";
		
		Connection conn = DBManager.getConnection();
		PreparedStatement psupdate;
		PreparedStatement psinsert;
		try {
			psupdate = conn.prepareStatement(giveSql);
			psinsert = conn.prepareStatement(recordSql);
			Map map = new HashMap();
			psupdate.execute();
			psinsert.execute();
			
			map.put("checkNum", checkNum);
			map.put("flag", "success");
			
			JSONObject resultObj = JSONObject.fromObject(map);
			out.println(resultObj.toString());
			conn.close();
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
		
	}

}
