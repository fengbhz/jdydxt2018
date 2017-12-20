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

/**
 * 订单状态
 * 
 * @author win7
 *
 */
public class DelOrderAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DelOrderAction() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		DBManager dbm = new DBManager();
		String id = request.getParameter("sid");
		String value = request.getParameter("value");
		String sql = null;
		if(value.equals("取消订单")){
			sql = "update hotelorder set orderStatus = 2 where id =" + id;
		}else if(value.equals("核销")){
			sql = "update hotelorder set orderStatus = 0 where id =" + id;
		}else if(value.equals("申请退款")){
			sql = "update hotelorder set orderStatus = 3 where id =" + id;
		}else if(value.equals("作废")){
			sql = "update hotelorder set orderStatus = 1 where id =" + id;
		}
		Statement stat = null;
		Connection conn = null;
		try {
			conn = dbm.getConnection();
			stat = conn.createStatement();
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
		System.out.println("订单状态SQL===="+sql);
		response.sendRedirect("admin/order/list.jsp");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
}
