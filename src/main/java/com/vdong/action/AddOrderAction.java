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
import java.util.UUID;

/**
 * 代客下单==添加订单
 * 
 * @author win7
 * 
 */
public class AddOrderAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddOrderAction() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String id = UUID.randomUUID().toString().substring(0, 32);// 订单ID
		String orderId = System.currentTimeMillis() + "";// 订单编号
		String hotelId = request.getParameter("hotelId");// 所属酒店
		String itemId = request.getParameter("itemId");// 房型
		String name = request.getParameter("name");// 游客
		String mobile = request.getParameter("mobile");// 手机号
		String idCard = request.getParameter("idCard");
		String roomNum = request.getParameter("roomNum");// 房间数
		String checkInDate = request.getParameter("checkInDate");// 预定日期
		String checkOutDate = request.getParameter("checkOutDate");// 离店日期
		String sum = request.getParameter("sum");// 金额
		String payStatus = request.getParameter("payStatus");// 支付状态
		String payWay = request.getParameter("payWay");// 支付方式
		String orderStatus = request.getParameter("orderStatus");// 订单状态
		// String orderTime = request.getParameter("orderTime");// 下单时间
		String sql = "insert into hotelorder(id, orderId, hotelId,itemId, `name`, mobile, idCard, roomNum, checkInDate, checkOutDate, `sum`, payStatus, payWay, orderStatus, orderTime, itemName, account) "
				+ "select '" + id + "', '" + orderId + "', '" + hotelId + "', '" + itemId + "', '" + name + "', '"
				+ mobile + "', '" + idCard + "', '" + roomNum + "', DATE_FORMAT('" + checkInDate
				+ "','%Y-%m-%d'), DATE_FORMAT('" + checkOutDate + "','%Y-%m-%d'), '" + sum + "', '" + payStatus + "', '"
				+ payWay + "', '" + orderStatus + "', sysdate(), '', ''";
		if (name != "" || idCard != "" || mobile != "") {
		Statement stat = null;
		Connection conn = null;
		try {
				conn = DBManager.getConnection();
				stat = conn.createStatement();
				System.out.println("添加订单=========" + sql);
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
		response.sendRedirect("admin/product/list.jsp");
		out.flush();
		out.close();
		}else{
			response.sendRedirect("admin/product/addOrder.jsp?id="+itemId+"&pid="+hotelId);
			out.flush();
			out.close();
		}
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
