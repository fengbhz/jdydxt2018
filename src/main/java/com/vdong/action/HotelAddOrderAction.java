package com.vdong.action;

import com.vdong.commons.db.DBManager;
import com.vdong.commons.util.DateUtil;
import com.vdong.commons.util.PinYin;
import com.vdong.commons.util.tool;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("serial")
public class HotelAddOrderAction extends HttpServlet {

	public HotelAddOrderAction() {
		super();
	}

	public void destroy() {
		super.destroy();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		this.doPost(request, response);
		out.close();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String id = UUID.randomUUID().toString().substring(0, 32);
		String hotelId = request.getParameter("orderId");// 所属酒店
		String account = (request.getParameter("account")==null?"":request.getParameter("account"));
		String custom = (request.getParameter("custom")==null?"":request.getParameter("custom"));
		String itemId = (request.getParameter("itemId")==null?"":request.getParameter("itemId"));
		String itemName = (request.getParameter("itemName")==null?"":request.getParameter("itemName"));
		String orderId = PinYin.cn2py(itemName)+DateUtil.dateFormatShort(new Date())+tool.function();
		String mobile = (request.getParameter("mobile")==null?"":request.getParameter("mobile"));
		String idCard = (request.getParameter("idCard")==null?"":request.getParameter("idCard"));
		String roomNum = (request.getParameter("roomNum")==null?"":request.getParameter("roomNum"));
		String checkInDate = (request.getParameter("checkInDate")==null?"":request.getParameter("checkInDate"));
		String checkOutDate = (request.getParameter("checkOutDate")==null?"":request.getParameter("checkOutDate"));
		String sum = (request.getParameter("sum")==null?"":request.getParameter("sum"));
		String payWay = (request.getParameter("payWay")==null?"":request.getParameter("payWay"));
		String addOrderSql = "insert into hotelorder(id, orderId, itemId, itemName, account, name, mobile, idCard, roomNum, checkInDate, checkOutDate, sum, payStatus, payWay, orderStatus, orderTime, orderOrigin, orderUser, remark,hotelId) "
				 		   + "select '" + id + "', '" + orderId + "', '" + itemId + "', '" + itemName + "', '" + account + "', '" + custom + "', '" + mobile + "', '" + idCard + "', '" + roomNum + "', str_to_date('" + checkInDate + "','%Y-%m-%d'), str_to_date('" + checkOutDate + "','%Y-%m-%d'),"
				 		   		+ " '" + sum + "', '0', '" + payWay + "', '0', sysdate(), '', '', '','"+hotelId+"' from dual";
		Connection conn = DBManager.getConnection();
		PreparedStatement psinsert;
		try {
			psinsert = conn.prepareStatement(addOrderSql);
			Map map = new HashMap();
			psinsert.execute();
			map.put("orderId", id);
			map.put("flag", "success");
			map.put("total", "成功下单");
			JSONObject resultObj = JSONObject.fromObject(map);
			out.println(resultObj.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}
	public void init() throws ServletException {
		
	}

}
