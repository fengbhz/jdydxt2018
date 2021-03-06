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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@SuppressWarnings("serial")
public class AddSupplierKindAction extends HttpServlet {

	public AddSupplierKindAction() {
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
		String id = UUID.randomUUID().toString().substring(0, 32);
		String jiudianKind = request.getParameter("jiudianKind");
		String jiudianOrder = request.getParameter("jiudianOrder");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String jiudianDate = df.format(new Date());

		String sql = "insert into hotelsupplierkind(id, jiudianKind, jiudianStatus, jiudianOrder, jiudianDate) values('" + id +"', '" + jiudianKind + "', '0', '" + jiudianOrder + "', str_to_date('" + jiudianDate + "','%Y-%m-%d'))";
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
		response.sendRedirect("admin/jiudian/kind.jsp");
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}
}
