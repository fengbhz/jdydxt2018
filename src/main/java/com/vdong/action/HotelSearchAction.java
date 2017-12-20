package com.vdong.action;

import com.vdong.commons.db.DBManager;
import com.vdong.services.HotelManagerService;
import com.vdong.commons.util.Constants;
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
public class HotelSearchAction extends HttpServlet {
	HotelManagerService hotelManagerService ;
	public HotelSearchAction() {
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBManager dbmanager=new DBManager();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String key = (request.getParameter("key")==null?"":request.getParameter("key"));
		String id = (request.getParameter("id")==null?"":request.getParameter("id"));
		String ids="";
		String condition = "";
		if(!key.equals("")){
			condition += " and s.hotelName like '%" + key + "%' or s.address like '%" + key + "%'";
		}
		
		if(!id.equals("")){
			condition += " and r.id = '" + id + "' ";
			 String s_sql="select  CONCAT_WS('',service,installation) as service   from  hotelroom t  where  t.id='" + id + "' ";
			 List list =new ArrayList();
			 list =dbmanager.find(s_sql);
			 if(null!=list&&list.size()>0){
				   for (int i = 0; i < list.size(); i++) {
					Map map=(Map) list.get(0);
					String id_s=(String) map.get("service");
					int index=id_s.lastIndexOf(',');
					 ids=id_s.substring(0, index);
					System.out.println(ids);
				}
			 }
		}
		
		String sql = "SELECT r.id,s.id as  hotelId, r.roomName, r.details, r.payway,r.notes, r.costExplain, r.refundExplain, r.other, r.bedType, r.wifi, r.breakfast, r.virtualCoin, r.coin, s.hotelName, s.tel, s.area, s.address, s.coordinate, r.itemPic, s.hotelKind, r.service, r.installation, r.picArr FROM hotelsupplier s, hotelroom r WHERE r.pid = s.id" + condition;
		String serviceSql = "SELECT id, name, type, pic FROM hotelservice   where  1=1  ";
		  if(!ids.equals("")){
			  serviceSql+="  and id  in ("+ids+")";
		  }
		Connection conn = DBManager.getConnection();
		PreparedStatement pstmt;
		PreparedStatement psservice;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			Map map = new HashMap();
			List tempLst = new ArrayList();
			String service = "";
			String installation = "";
			String picsStr = "";
			while (rs.next()) {
				Map m = new HashMap();
				m.put("id", rs.getString("id"));
				m.put("hotelId", rs.getString("hotelId"));
				m.put("roomName", rs.getString("roomName"));
				m.put("details", rs.getString("details"));
				m.put("notes", rs.getString("notes"));
				m.put("costExplain", rs.getString("costExplain"));
				m.put("refundExplain", rs.getString("refundExplain"));
				m.put("other", rs.getString("other"));
				m.put("bedType", rs.getString("bedType"));
				m.put("wifi", rs.getString("wifi"));
				m.put("payway", rs.getString("wifi"));
				m.put("breakfast", rs.getString("breakfast"));
				//进行判断，看是否在其他地方设置
		     	List list =	hotelManagerService.getProductList(rs.getString("id"), DateUtil.dateFormatForymd(new Date()));
				if(null!=list&&list.size()>0){
					Map<String,String>  remap=(Map<String, String>) list.get(0);
					m.put("virtualCoin", remap.get("djbprice"));
					m.put("coin",        remap.get("price"));
				}else{
					m.put("virtualCoin", rs.getString("virtualCoin"));
					m.put("coin", rs.getString("coin"));
				}
				m.put("hotelName", rs.getString("hotelName"));
				m.put("tel", rs.getString("tel"));
				m.put("area", rs.getString("area"));
				m.put("address", rs.getString("address"));
				m.put("coordinate", rs.getString("coordinate"));
				m.put("itemPic", Constants.URL+"/" + rs.getString("itemPic"));
				m.put("hotelKind", rs.getString("hotelKind"));
				service = rs.getString("service");
				installation = rs.getString("installation");
				picsStr = rs.getString("picArr");
				tempLst.add(m);
			}

			psservice = conn.prepareStatement(serviceSql);
			ResultSet rsservice = psservice.executeQuery();
			List serviceLst = new ArrayList();
			List installationLst = new ArrayList();
			List picLst = new ArrayList();
			String[] serviceArr = service.split(";");
			String[] installationArr = installation.split(";");
			int i = 0;
			while (rsservice.next()) {
				Map m = new HashMap();
				m.put("id", rsservice.getString("id"));
				m.put("name", rsservice.getString("name"));
				m.put("type", rsservice.getString("type"));
				m.put("pic", Constants.URL + (null==rsservice.getString("pic")?"":rsservice.getString("pic").trim())+".png");

				m.put("has",  i<=serviceArr.length-1?serviceArr[i]:"");
				serviceLst.add(m);
				i++;
				if(i == 9 && rsservice.getString("type").equals("0")){
					i = 0;
				}
			}
			if(!picsStr.equals("")){
				String[] picsArr = picsStr.split(",");
				for (int p = 0; p < picsArr.length; p++) {
					Map remap = new HashMap();
					if (null != picsArr[p] && !picsArr[p].equals("")) {
						try {
							String attach_sql = " select * from t_attach where id='"+ picsArr[p] + "'   ";
							List list = dbmanager.find(attach_sql);
							remap = (Map) list.get(0);
						} catch (Exception e) {
							e.printStackTrace();
						}
						String url = (String) remap.get("url");
						picLst.add(Constants.URL  + url);
					}
				}
			}
			 // 这里加载此房型60天内的数据
		  List fxlist =	hotelManagerService.getProductListForDays(id, "");
		    map.put("priceList", fxlist);
			map.put("rows", tempLst);
			map.put("serviceLst", serviceLst);
			map.put("installationLst", installationLst);
			map.put("picLst", picLst);
			map.put("total", tempLst.size());
			JSONObject resultObj = JSONObject.fromObject(map);
			out.println(resultObj.toString());
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
