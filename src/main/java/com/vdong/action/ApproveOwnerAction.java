package com.vdong.action;

import com.vdong.commons.db.DBManager;
import com.vdong.commons.util.tool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("serial")
public class ApproveOwnerAction extends HttpServlet {

	public ApproveOwnerAction() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("ownerId1");
		String status = request.getParameter("aType");
		String account = request.getParameter("account");
		String userid = tool.newGUID();
		 String upSql="";
		if (status.equals("0") || status.equals("2")) {
           upSql=" update hoteluser set  userKind=1  where  account='"+account+"'  ";
		}

		if(status.equals("0")){
			status = "1";
		}
		if(status.equals("2")){
			status = "3";
		}
		
		String sql = "update hotelowner set applyStatus = '" + status + "' where id = '" + id + "'";
		Statement stat = null;
		Statement stat2 = null;
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			stat = conn.createStatement();
			stat2 = conn.createStatement();
			System.out.println(sql);
			stat.execute(sql);
			if(!upSql.equals("")){
				stat2.execute(upSql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stat != null)
					stat.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
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
