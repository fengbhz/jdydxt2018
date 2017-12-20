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

@SuppressWarnings("serial")
public class ModKindAction extends HttpServlet {

	public ModKindAction() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String hotelKind=request.getParameter("hotelKind");
		String orderNo=request.getParameter("orderNo");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String alterDate = df.format(new Date());
		DBManager dbm = new DBManager();
		
		String sql = "update hotelkind set hotelKind = '" + hotelKind + "', orderNo = '" + orderNo + "', alterDate = str_to_date('" + alterDate+"','%Y-%m-%d') where id = '" + id + "'";
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
		response.sendRedirect("admin/product/kind.jsp");
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
