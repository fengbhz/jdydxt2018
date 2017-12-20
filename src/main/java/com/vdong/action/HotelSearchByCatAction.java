package com.vdong.action;

import com.vdong.commons.db.DBManager;
import com.vdong.services.HotelManagerService;
import com.vdong.commons.util.DateUtil;
import net.sf.json.JSONObject;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@SuppressWarnings("serial")
public class HotelSearchByCatAction extends HttpServlet {
	
	HotelManagerService hotelManagerService ;
	public HotelSearchByCatAction() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		this.doPost(request, response);
		out.close();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		DBManager dbm = new DBManager();
		String categoryId = (request.getParameter("categoryId")==null?"":request.getParameter("categoryId"));
		String key = (request.getParameter("key")==null?"":request.getParameter("key"));
		String condition = "";
		if(!categoryId.equals("")){
			condition += " and r.other = '" + categoryId + "' ";
		}
		if(!key.equals("")){
			condition += " and ( s.hotelName like '%" + key + "%' or s.address like '%" + key + "%') ";
		}
		condition +="  and r.isdeleted='0' ";
				String sql = "SELECT r.id, r.roomName, r.payway , r.virtualCoin, r.coin, s.hotelName, s.tel, s.area, s.address, r.itemPic FROM hotelroom r  join 	hotelsupplier  s on r.pid = s.id  WHERE 1 = 1" + condition;
		System.out.println(sql);
		Connection conn = dbm.getConnection();
		PreparedStatement pstmt;
		PreparedStatement psrecommend;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			Map map = new HashMap();
			List tempLst = new ArrayList();
			while (rs.next()) {
				Map m = new HashMap();
				m.put("id", rs.getString("id"));
				m.put("roomName", rs.getString("roomName"));
				List list =	hotelManagerService.getProductList(rs.getString("id"), DateUtil.dateFormatForymd(new Date()));
				if(null!=list&&list.size()>0){
					Map<String,String>  remap=(Map<String, String>) list.get(0);
					m.put("virtualCoin", remap.get("djbprice"));
					m.put("coin",        remap.get("price"));
				}else{
					m.put("virtualCoin", rs.getString("virtualCoin"));
					m.put("coin", rs.getString("coin"));
				}
				m.put("payway", rs.getString("payway"));
				m.put("hotelName", rs.getString("hotelName"));
				m.put("tel", rs.getString("tel"));
				m.put("area", rs.getString("area"));
				m.put("address", rs.getString("address"));
				m.put("itemPic", rs.getString("itemPic"));
				tempLst.add(m);
			}
			
			List recommendLst = new ArrayList();
			String recommendSql = "select id, jiudianKind from hotelsupplierkind where jiudianStatus = '0'    ";
			
			psrecommend = conn.prepareStatement(recommendSql);
			ResultSet rsRecommend = psrecommend.executeQuery();
			while (rsRecommend.next()) {
				Map m = new HashMap();
				m.put("id", rsRecommend.getString("id"));
				m.put("jiudianKind", rsRecommend.getString("jiudianKind"));
				recommendLst.add(m);
			}
			
			map.put("rows", tempLst);
			map.put("recommendRows", recommendLst);
			map.put("total", tempLst.size());
			JSONObject resultObj = JSONObject.fromObject(map);
			out.println(resultObj.toString());
			System.out.println(resultObj.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		super.init(); 
     WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()); 
     hotelManagerService = (HotelManagerService) wac.getBean("hotelManagerService"); 
	}

}
