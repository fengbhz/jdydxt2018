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
public class ModHotelAction extends HttpServlet {

	public ModHotelAction() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String hotelName = (request.getParameter("hotelName")==null?"":request.getParameter("hotelName"));
		String hotelKind = (request.getParameter("hotelKind")==null?"":request.getParameter("hotelKind"));
		String order = (request.getParameter("order")==null?"":request.getParameter("order"));
		String hotelSummary = (request.getParameter("hotelSummary")==null?"":request.getParameter("hotelSummary"));
		String hotelPic = (request.getParameter("hotelPic")==null?"":request.getParameter("hotelPic"));
		String hotelAccount = (request.getParameter("hotelAccount")==null?"":request.getParameter("hotelAccount"));
		String hotelPass = (request.getParameter("hotelPass")==null?"":request.getParameter("hotelPass"));
		String tel = (request.getParameter("tel")==null?"":request.getParameter("tel"));
		String area = (request.getParameter("area")==null?"":request.getParameter("area"));
		String address = (request.getParameter("address")==null?"":request.getParameter("address"));
		String coordinate = (request.getParameter("coordinate")==null?"":request.getParameter("coordinate"));
		String remark = (request.getParameter("remark")==null?"":request.getParameter("remark"));
		
		String sql = "update hotelsupplier set hotelName = '" + hotelName 
				   + "', hotelKind = '" + hotelKind 
				   + "', `order` = '" + order 
				   + "', hotelSummary = '" + hotelSummary
				   + "', hotelPic = '" + hotelPic
				   + "', hotelAccount = '" + hotelAccount
				   + "', hotelPass = '" + hotelPass
				   + "', tel = '" + tel
				   + "', area = '" + area
				   + "', address = '" + address
				   + "', coordinate = '" + coordinate
				   + "', remark = '" + remark
				   + "', submitDate = SYSDATE() where id = '" + id + "'";
		System.out.println(sql);

		
		String up_user=" update admin set password='"+hotelPass+"' where  userName='"+hotelAccount+"'";
		Statement stat = null;
		Connection conn=null;
		try {
			conn=DBManager.getConnection();
			stat = conn.createStatement();
			stat.execute(sql);
			stat.execute(up_user);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stat!=null)
					stat.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		response.sendRedirect("admin/jiudian/list.jsp");
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
