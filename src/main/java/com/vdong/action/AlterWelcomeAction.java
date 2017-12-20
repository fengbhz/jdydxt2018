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
public class AlterWelcomeAction extends HttpServlet {

	public AlterWelcomeAction() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		DBManager dbm = new DBManager();
		
		String sql = "update hotelwelcome set title = '" + title + "', content = '" + content + "' where id = 'welcome'";
		System.out.println(sql);

		Statement stat = null;
		Connection conn=null;
		try {
			conn=dbm.getConnection();
			stat = conn.createStatement();
			stat.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(stat!=null)
					stat.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect("admin/index/alterWelcome.jsp");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
