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
import java.util.Date;

/**
 * 添加产品
 * 
 * @author win7
 * 
 */
public class AddProductAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddProductAction() {
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
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		DBManager dbm = new DBManager();
		String id = (request.getParameter("id")==null?"":request.getParameter("id"));
		String pid = (request.getParameter("pid")==null?"":request.getParameter("pid"));// pid 酒店供应商id
		String details = (request.getParameter("details")==null?"":request.getParameter("details"));// details 客房详情
		String notes = (request.getParameter("notes")==null?"":request.getParameter("notes"));// notes 购买须知 --
		String costExplain = (request.getParameter("costExplain")==null?"":request.getParameter("costExplain"));// costExplain 费用说明 --
		String refundExplain = (request.getParameter("refundExplain")==null?"":request.getParameter("refundExplain"));// refundExplain 退款说明
		String other = (request.getParameter("other")==null?"":request.getParameter("other"));// other 其他说明
		String property = (request.getParameter("property")==null?"":request.getParameter("property")); // property 产品属性（推荐、热卖、特价、新品）
		String bedType = (request.getParameter("bedType")==null?"":request.getParameter("bedType")); // bedType 床型（单床、双床、大床）
		String breakfast = (request.getParameter("breakfast")==null?"":request.getParameter("breakfast")); // breakfast 是否含早餐（是、否）
		String wifi = (request.getParameter("wifi")==null?"":request.getParameter("wifi")); // wifi 无线网络（是、否）
		String roomName = (request.getParameter("roomName")==null?"":request.getParameter("roomName")); // roomName 客房名称 --
		String roomNum = (request.getParameter("roomNum")==null?"":request.getParameter("roomNum"));// roomNum 房间数
		String overplusNum = (request.getParameter("overplusNum")==null?"":request.getParameter("overplusNum"));// overplusNum 剩余房间数
		String itemPic = (request.getParameter("itemPic")==null?"":request.getParameter("itemPic")); // itemPic 产品首图
		String virtualCoin = (request.getParameter("virtualCoin")==null?"":request.getParameter("virtualCoin"));// virtualCoin 度假币 --
		String coin = (request.getParameter("coin")==null?"":request.getParameter("coin")); // coin 实体货币 --
		String service = (request.getParameter("service")==null?"":request.getParameter("service"));// service 酒店服务 --
		String installation = (request.getParameter("installation")==null?"":request.getParameter("installation"));// installation 酒店设施 --
		String picArr = (request.getParameter("picArr")==null?"":request.getParameter("picArr"));// picArr 图片集icArr = "";// picArr 图片集
		String date = new Date().toLocaleString();

		
		
		
		
		String sql = "insert into hotelroom(id, pid, details, notes, costExplain, refundExplain, other, property, bedType, breakfast, wifi, roomName, "
				+ "roomNum, overplusNum,  itemPic, virtualCoin, coin, service, installation, picArr)  values('" + id
				+ "','" + pid + "','" + details + "','" + notes + "','" + costExplain + "','" + refundExplain+ "','" + other+ "','" + property+ "','" 
				+ bedType+ "','" + breakfast + "','" + wifi+ "','" + roomName+ "','" + roomNum+ "','" + overplusNum+ "','" + itemPic+ "','" + virtualCoin 
				+ "','" + coin+ "','"+ service+ "','" + installation+ "','"+ picArr+ "')";
		Statement stat = null;
		Connection conn = null;
		try {
			conn = dbm.getConnection();
			stat = conn.createStatement();
			System.out.println("添加产品=========" + sql);
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
