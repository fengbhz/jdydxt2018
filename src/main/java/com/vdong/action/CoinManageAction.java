package com.vdong.action;

import com.vdong.commons.db.DBManager;
import com.vdong.commons.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

@SuppressWarnings("serial")
public class CoinManageAction extends HttpServlet {

	public CoinManageAction() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@SuppressWarnings("unused")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("ownerId");
		String coin = request.getParameter("coin");
		String remark = request.getParameter("remark");
		String opType = request.getParameter("opType");
		String sql = "";
		String logSql="";
		if (opType.equals("0")) {
			sql = "update hoteluser set virtualCoin = (CAST(virtualCoin AS UNSIGNED INT) + CAST('" + coin + "' AS UNSIGNED INT)) where account = (select max(o.account) from hotelowner o where o.id = '" + id + "')";



		} else if (opType.equals("1")) {
			sql = "update hoteluser set virtualCoin = (CAST(virtualCoin AS UNSIGNED INT) - CAST('" + coin + "' AS UNSIGNED INT)) where account = (select max(o.account) from hotelowner o where o.id = '" + id + "')";
		}
		logSql=" insert  into t_log (originalCoin,coin,type,account,remark,creattime) values((select virtualCoin from hoteluser where  account=(select max(o.account) from hotelowner o where o.id = '" + id + "')), '"+coin+"','"+opType+"',(select max(o.account) from hotelowner o where o.id = '" + id + "'),'"+remark+"','"+ DateUtil.dateFormat(new Date())+"') ";
		 
		Statement stat = null;
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			stat = conn.createStatement();
			System.out.println(sql);
			stat.execute(sql);
			stat.execute(logSql);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (stat != null)
					stat.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect("admin/owner/owner.jsp");

		
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}
}
