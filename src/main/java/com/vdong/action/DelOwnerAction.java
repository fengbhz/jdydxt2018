package com.vdong.action;

import com.vdong.commons.db.DBManager;

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
public class DelOwnerAction extends HttpServlet {

	public DelOwnerAction() {
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
		DBManager dbm = new DBManager();
		String id = request.getParameter("id");
		String status = request.getParameter("status");
		
		String sql = "update hotelowner set status = '" + status + "' where id = '" + id + "'";
		Statement stat = null;
		Connection conn = null;
		try {
			conn = dbm.getConnection();
			stat = conn.createStatement();
			System.out.println(sql);
			stat.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
