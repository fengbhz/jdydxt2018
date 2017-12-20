package com.vdong.controller;

import com.vdong.commons.bean.LoginUser;
import com.vdong.commons.dao.BaseDao;
import com.vdong.commons.db.DBManager;
import com.vdong.services.OrderManagerService;
import com.vdong.services.SystemCodeService;
import com.vdong.commons.util.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单管理
 * 
 * @author Administrator
 * 
 */
@Controller
public class OrderController extends BaseController {

	@Autowired
	private OrderManagerService orderManagerService;

	@Autowired
	private SystemCodeService systemCodeService;

	@Autowired
	private BaseDao  baseDao;

	/**
	 * 申请退款[微信前端退款]
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "platform/orderRefuse" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	@SuppressWarnings( { "rawtypes", "unchecked" })
	public String vailYz(HttpServletRequest reque, HttpServletResponse response)
			throws IOException {
		boolean flag = true;
		Map map = new HashMap();
		String id = request.getParameter("id");
		try {
			flag = orderManagerService.updateOrder(id);
			if (flag) {
				//sendMsg(reque, response, id);
			}
			map.put("success", flag);
			response.reset();
		} catch (Exception e) {
			map.put("success", false);
		}
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}

	/**
	 * 添加订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "platform/addOrder" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	@SuppressWarnings( { "rawtypes", "unchecked" })
	public String addOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		boolean flag = true;
		Map map = new HashMap();
		String id = tool.newGUID();
		String hotelId = (request.getParameter("orderId") == null ? ""
				: request.getParameter("orderId"));// // 实际上后台传过来的是酒店的ID
		String account = (request.getParameter("account") == null ? ""
				: request.getParameter("account"));
		String custom = (request.getParameter("custom") == null ? "" : request
				.getParameter("custom"));
		String itemId = (request.getParameter("itemId") == null ? "" : request
				.getParameter("itemId"));
		String itemName = (request.getParameter("itemName") == null ? ""
				: request.getParameter("itemName"));
		String orderId = PinYin.cn2py(itemName)
				+ DateUtil.dateFormatShort(new Date()) + tool.function();
		String mobile = (request.getParameter("mobile") == null ? "" : request
				.getParameter("mobile"));
		String idCard = (request.getParameter("idCard") == null ? "" : request
				.getParameter("idCard"));
		String roomNum = (request.getParameter("roomNum") == null ? ""
				: request.getParameter("roomNum"));
		String checkInDate = (request.getParameter("checkInDate") == null ? ""
				: request.getParameter("checkInDate"));
		String checkOutDate = (request.getParameter("checkOutDate") == null ? ""
				: request.getParameter("checkOutDate"));
		String sum = (request.getParameter("sum") == null ? "" : request
				.getParameter("sum"));
		String payWay = (request.getParameter("payWay") == null ? "" : request
				.getParameter("payWay"));

		Map<String, String> remap = new HashMap<String, String>();
		remap.put("id", id);
		remap.put("hotelId", hotelId);
		remap.put("account", account);
		remap.put("custom", custom);
		remap.put("itemId", itemId);
		remap.put("orderId", orderId);
		remap.put("mobile", mobile);
		remap.put("idCard", idCard);
		remap.put("roomNum", roomNum);
		remap.put("checkInDate", checkInDate);
		remap.put("checkOutDate", checkOutDate);
		remap.put("sum", sum);
		remap.put("payWay", payWay);
		try {
			flag = orderManagerService.insertOrder(remap);
			if (flag) {
				sendMsg(request, response, id);
			}
			map.put("orderId", id);
			map.put("flag", flag);
			map.put("total", "成功下单");
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}
		JSONObject resultObj = JSONObject.fromObject(map);
		return resultObj.toString();
	}

	/**
	 * 根据ID 对退款进行操作
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "platform/refundOrder" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	@SuppressWarnings( { "rawtypes", "unchecked" })
	public String refund(HttpServletRequest request,
			HttpServletResponse response, String id, String repayNum) {
		boolean flag = true;
		Map map = new HashMap();
		Map<String, String> remap = new HashMap<String, String>();
		// 这里有三种情况进行退款
		if (remap.get("success").equals("true")) {
			flag = true;
		} else {
			flag = false;
		}
		try {
			remap = orderManagerService.refundOrder(id, repayNum);
			if (flag) {
				sendMsg(request, response, id);
			}
			map.put("success", flag);
		} catch (Exception e) {
			map.put("success", false);
		}
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}

	/**
	 * 对订单状态进行修改
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @param status
	 *            (新状态)
	 * @return
	 */
	@RequestMapping(value = { "platform/updateOrderStatus" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	@SuppressWarnings( { "rawtypes", "unchecked" })
	public String updateOrderStatus(HttpServletRequest request,
			HttpServletResponse response, String id, String status) {
		boolean flag = true;
		Map map = new HashMap();
		Map<String, String> remap = new HashMap<String, String>();
		// 这里有三种情况进行退款
		try {
			LoginUser user = getLoginUser();
			remap = orderManagerService.updateOrderStatus(id, status, user);
			if (remap.get("success").equals("true")) {
				flag = true;
			} else {
				flag = false;
			}
			if (flag) {
				sendMsg(request, response, id);
			}
			map.put("success", flag);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}

	/**
	 * 进入订单列表
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @param status
	 *            (新状态)
	 * @return
	 */
	@RequestMapping(value = { "platform/OrderList" })
	@SuppressWarnings( { "rawtypes", "unchecked" })
	public String OrderList(HttpServletRequest request,
			HttpServletResponse response) {
		boolean flag = true;
		Map map = new HashMap();
		Map<String, String> remap = new HashMap<String, String>();
		// 获取所有的订单状态
		List _dlist = systemCodeService.getList("OS", null);
		request.setAttribute("_dlist", _dlist);
		try {
			String username = (String) request.getSession()
					.getAttribute("user");
			// 获取用户
			List list = orderManagerService.getUserMsg(username);
			if (null != list && list.size() > 0) {
				Map rmap = (Map<String, String>) list.get(0);
				String roleCode = String.valueOf(null == rmap.get("code") ? ""
						: rmap.get("code")); // 角色编号
				request.setAttribute("roleCode", roleCode);

				if (roleCode.equals(Constants.JD_MANAGER)) { // 酒吧管理人员
					request.setAttribute("id", rmap.get("id"));
				} else if (roleCode.equals(Constants.PT_MANAGER)) {
					request.setAttribute("id", "-1");
				} else {
					map.put("success", false);
				}
			}
			map.put("success", flag);
		} catch (Exception e) {
			map.put("success", false);
		}
		return "admin/order/list";
	}
	
	
	
	/**
	 * 订单页面获取分页数据查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "order/json" },  method = { org.springframework.web.bind.annotation.RequestMethod.POST })
    @ResponseBody
	@SuppressWarnings( { "rawtypes", "unchecked" })
	public String getList(HttpServletRequest request,HttpServletResponse response) {
		boolean flag = true;
		Map map = new HashMap();
		Map _map = new HashMap();
		DBManager db=new DBManager();
		String phoneNum = request.getParameter("phoneNum");
		String orderNum = request.getParameter("orderNum");
		String VerifitimeStart = request.getParameter("VerifitimeStart");
		String VerifitimeEnd = request.getParameter("VerifitimeEnd");
		String checkInDate = request.getParameter("checkInDate");
	    String checkOutDate = request.getParameter("checkOutDate");
		String _curPage = request.getParameter("curPage") == null ? "1": request.getParameter("curPage");
		String _pageNum = request.getParameter("pageNum") == null ? "": request.getParameter("pageNum");
		String ids=String.valueOf(getLoginUser().getId()==1?-1:getLoginUser().getId());
		String roleCode=getLoginUser().getCode();
		Map<String, String> remap = new HashMap<String, String>();
		// 获取所有的订单状态
			Connection conn = DBManager.getConnection();
			String hotelName = request.getParameter("hotelName");
			String orderStatus = request.getParameter("orderStatus");//orderStatus
			String payStatus = request.getParameter("payStatus");//payStatus
			String payWay = request.getParameter("payWay");//payWay
			String sql = null;
				 sql = "SELECT   o.orderId,o.payWay,o.orderStatus,o.name,o.mobile, o.roomNum,o.sum,o.payStatus,o.id, date_format(o.checkInDate, '%Y-%m-%d')as checkInDate, date_format(o.checkOutDate, '%Y-%m-%d')as checkOutDate, date_format(o.orderTime, '%m/%d %H:%i:00') AS orderTimes,s.hotelName,k.roomName,tsc.dmmc as os,k.details  FROM hotelorder o "
			         + "LEFT JOIN (select ts.* from t_systemcode ts   where  ts.type='OS'   and ts.status='0' ) tsc   on  tsc.dmz=o.orderStatus ";
			     sql+=" LEFT JOIN hotelsupplier s ON o.hotelId = s.id LEFT JOIN hotelroom k ON k.id = o.itemId  where  1=1 ";
			  
			  	System.out.print("sql1--->"+sql);
			 if(null!=ids&&!"".equals(ids)&&!"-1".equals(ids)){
					sql	+= "  and  s.userid=  "+ids;
		       }
		     
		     if(StringUtils.isNotBlank(orderStatus)){
		         sql	+= " and  o.orderStatus=  "+orderStatus;
		     }
		     
		      if(StringUtils.isNotBlank(payStatus)){
		         sql	+= " and  o.payStatus=  "+payStatus;
		     }
		     
		      if(StringUtils.isNotBlank(payWay)){
		         sql	+= " and  o.payWay=  "+payWay;
		     }
		     
		     
		     if(StringUtils.isNotBlank(phoneNum)){
		         sql	+= " and  o.mobile=  "+phoneNum;
		     }
		     
		      if(StringUtils.isNotBlank(orderNum)){
		         sql	+= " and orderId like '%" + orderNum + "%'";
		     }
		     
		      if(StringUtils.isNotBlank(VerifitimeStart)){
		         sql	+= " and  Verifitime >= '" + VerifitimeStart + "'";
		     }
		     
		      if(StringUtils.isNotBlank(VerifitimeEnd)){
		         sql	+= " and  Verifitime <= '" + VerifitimeEnd + "'";
		     }
		     
		      if(StringUtils.isNotBlank(checkInDate)){
		         sql	+= " and  checkInDate >= '" + checkInDate + "'";
		     }
		     
		      if(StringUtils.isNotBlank(checkOutDate)){
		         sql	+= " and  checkOutDate <= '" + checkOutDate + "'";
		     }
			sql+="  order by o.ordertime  desc";
			System.out.print("sql2--->"+sql);

		    int pageNum = Integer.parseInt(_pageNum);  
			int currPage = Integer.parseInt(_curPage);
			
			int start = pageNum * (currPage -1);
			int end = 10;
				String ywSql = tool.paginationFormysql(sql.toString(), start, end);//拼装sql（分页）
				System.out.println("拼装的sql  "+ywSql);
				List  mqryList;
			int count;
			try {
			mqryList = db.find(ywSql);
			count = db.find(sql.toString()).size();
			System.out.println("获取分页后信息：" + mqryList);
		
				_map.put("rowcount",count);
				//总共有多少页
				int pageCount = 0;
				if(count > 0) {
					pageCount = (count -1)/pageNum + 1;
				}
				_map.put("pagecount", pageCount);
				_map.put("info", mqryList);
			   
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONObject   json=JSONObject.fromObject(_map);
			return json.toString();
	}
	
	
	
	
	
	
	
	
	
	

	/**
	 * 根据状态和id来跳转操作页面
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @param status
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "platform/updateOrderJsp" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String updateOrderJsp(HttpServletRequest request,
			HttpServletResponse response, String id, String status)
			throws IOException {
		List list = orderManagerService.getOrderDetail(id);
		if (null != list && list.size() > 0) {
			Map map = (Map<String, String>) list.get(0);
			request.setAttribute("orderL", map);
			System.out.println(map);
		}
		request.setAttribute("status", status);
		return "admin/order/updateOrderJsp";
	}

	/**
	 * 后台订单操作完成后保存修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "platform/updateOrderJsp" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public String updateOrderJsp(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String id = (request.getParameter("id") == null ? "" : request
				.getParameter("id"));// // 实际上后台传过来的是酒店的ID
		String checkInDate = (request.getParameter("checkInDate") == null ? ""
				: request.getParameter("checkInDate"));
		String checkOutDate = (request.getParameter("checkOutDate") == null ? ""
				: request.getParameter("checkOutDate"));
		String name = (request.getParameter("name") == null ? "" : request
				.getParameter("name"));
		String mobile = (request.getParameter("mobile") == null ? "" : request
				.getParameter("mobile"));
		String roomNum = (request.getParameter("roomNum") == null ? ""
				: request.getParameter("roomNum"));
		String refuNum = (request.getParameter("refuNum") == null ? ""
				: request.getParameter("refuNum"));
		String status = (request.getParameter("status") == null ? "" : request
				.getParameter("status"));

		Map<String, String> remap = new HashMap<String, String>();
		Map<String, Object> map = new HashMap<String, Object>();
		remap.put("id", id);
		remap.put("name", name);
		remap.put("mobile", mobile);
		remap.put("refuNum", refuNum);
		remap.put("roomNum", roomNum);
		remap.put("checkInDate", checkInDate);
		remap.put("checkOutDate", checkOutDate);
		remap.put("status", status);
		try {
			orderManagerService.updateOrderByStatus(remap);
			sendMsg(request, response, id);
			map.put("success", true);
			response.reset();
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();

	}

	/**
	 * 后台订单操作完成后保存修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "order/pay" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public void orderPay(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String id = (request.getParameter("id") == null ? "" : request
				.getParameter("id"));
		String account = (request.getParameter("account") == null ? ""
				: request.getParameter("account"));
		String payWay = (request.getParameter("payWay") == null ? "" : request
				.getParameter("payWay"));
		String sum = (request.getParameter("sum") == null ? "" : request
				.getParameter("sum"));
		String alterOrderSql = "update hotelorder set payStatus = '1'  , orderStatus='2' "
				+ "where id='" + id + "'";
		Connection conn = DBManager.getConnection();
		PreparedStatement psinsert;
		PreparedStatement psupdate;
		try {
			psinsert = conn.prepareStatement(alterOrderSql);
			Map map = new HashMap();
			psinsert.execute();
			// 这是度假币支付
			if (payWay.equals("1")) {
				String alterAccountSql = "update hoteluser set virtualCoin = (CAST(virtualCoin AS UNSIGNED INT) - CAST('"
						+ sum
						+ "' AS UNSIGNED INT)) where account = '"
						+ account + "' ";
				System.out.println("alterAccountSql--->" + alterAccountSql);
				psupdate = conn.prepareStatement(alterAccountSql);
				psupdate.execute();
			}




			map.put("flag", "success");
			map.put("total", "支付成功");
			sendMsg(request, response, id);
			JSONObject resultObj = JSONObject.fromObject(map);
			out.println(resultObj.toString());
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

	/**
	 * 
	 * 订单内容筛选导出【不做安全性处理】
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "order/exportExcel")
	public void exportExcel(HttpServletRequest request,HttpServletResponse response) {
		
		String phoneNum = request.getParameter("phoneNum");
		String orderNum = request.getParameter("orderNum");
		String VerifitimeStart = request.getParameter("VerifitimeStart");
		String VerifitimeEnd = request.getParameter("VerifitimeEnd");
		String checkInDate = request.getParameter("checkInDate");
	    String checkOutDate = request.getParameter("checkOutDate");

	 	System.out.print("orderNum--->"+orderNum);
		String hotelName = request.getParameter("hotelName");
		String orderStatus = request.getParameter("orderStatus");//orderStatus
		String payStatus = request.getParameter("payStatus");//payStatus
		String payWay = request.getParameter("payWay");//payWay
		String sql = null;
		
		//  先获取用户权限
	LoginUser user=	getLoginUser();
		
			sql = "SELECT o.*, DATE_FORMAT(o.orderTime, '%m/%d %H:%i:00') AS orderTimes, u.account as uaccount , s.hotelName,k.roomName,tsc.dmmc as os, k.details FROM hotelorder o "
					+ "LEFT JOIN hotelsupplier s ON o.hotelId = s.id "
					+ "LEFT JOIN (select ts.* from t_systemcode ts   where  ts.type='OS'   and ts.status='0' ) tsc   on  tsc.dmz=o.orderStatus "
					+" left join (select * from hoteluser u )  u on u.account=o.account "
					+ "LEFT JOIN hotelroom k ON k.id = o.itemId where 1=1 ";
			   
	
		if (user.getCode().equals(Constants.JD_MANAGER)) {// 酒店管理员
			sql += "and  s.userid=  " + user.getCode();
		}

		if (StringUtils.isNotBlank(orderStatus)) {
			sql += "and  o.orderStatus=  " + orderStatus;
		}

		if (StringUtils.isNotBlank(payStatus)) {
			sql += "and  o.payStatus=  " + payStatus;
		}

		if (StringUtils.isNotBlank(payWay)) {
			sql += "and  o.payWay=  " + payWay;
		}

		if (StringUtils.isNotBlank(phoneNum)) {
			sql += "and  o.mobile=  " + phoneNum;
		}

		if (StringUtils.isNotBlank(orderNum)) {
			sql += "and orderId like '%" + orderNum + "%'";
		}

		if (StringUtils.isNotBlank(payWay)) {
			sql += "and  payWay like '%" + payWay + "%'";
		}

		if (StringUtils.isNotBlank(VerifitimeStart)) {
			sql += "and  Verifitime >= '" + VerifitimeStart + "'";
		}

		if (StringUtils.isNotBlank(VerifitimeEnd)) {
			sql += "and  Verifitime <= '" + VerifitimeEnd + "'";
		}

		if (StringUtils.isNotBlank(checkInDate)) {
			sql += "and  checkInDate >= '" + checkInDate + "'";
		}

		if (StringUtils.isNotBlank(payWay)) {
			sql += "and  checkOutDate <= '" + checkOutDate + "'";
		}
		sql+="  order by o.ordertime  desc";
		
		DBManager db=new DBManager();
	     List list=db.find(sql);
        Map<String,String>  remap= exportExcelUtil.exportQM(list, "ORDER", request, response);
        try {
			exportExcelUtil.downLoadFile(remap.get("path"), response, remap.get("fileName"), "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
