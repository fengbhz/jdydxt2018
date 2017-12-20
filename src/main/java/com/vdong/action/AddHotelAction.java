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
public class AddHotelAction extends HttpServlet {

	public AddHotelAction() {
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
		String hotelName = (request.getParameter("hotelName")==null?"":request.getParameter("hotelName"));
		String hotelid = (request.getParameter("hotelid")==null?"":request.getParameter("hotelid"));
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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String submitDate = df.format(new Date());
		String sql = "insert into hotelsupplier(id, hotelName, hotelSummary, hotelPic, hotelAccount, hotelPass, tel, area, address, coordinate, remark, `order`, submitDate, hotelKind, status) values('" 
				   + id + "', '" 
				   + hotelName + "', '"
				   + hotelSummary + "', '" 
				   + hotelPic + "', '"
				   + hotelAccount+"', '"
				   + hotelPass + "', '"
				   + tel + "', '"
				   + area + "', '"
				   + address + "', '"
				   + coordinate + "', '"
				   + remark + "', '"
				   + order + "', str_to_date('"
				   + submitDate + "','%Y-%m-%d'), '"
				   + hotelid + "', '0')";
		Statement stat = null;
		Connection conn = null;
		try {
			conn = dbm.getConnection();
			stat = conn.createStatement();
			System.out.println(sql);
			stat.execute(sql);
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
		response.sendRedirect("admin/jiudian/list.jsp");

		
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
