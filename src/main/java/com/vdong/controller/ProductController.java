package com.vdong.controller;

import com.vdong.commons.bean.Msg;
import com.vdong.commons.bean.PriceManager;
import com.vdong.commons.db.DBManager;
import com.vdong.services.HotelManagerService;
import com.vdong.services.SystemCodeService;
import com.vdong.commons.util.AccountDate;
import com.vdong.commons.util.Constants;
import com.vdong.commons.util.DateUtil;
import com.vdong.commons.util.tool;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@Controller
public class ProductController {
	
	@Autowired
	private HotelManagerService hotelManagerService;
	

	@Autowired
	private SystemCodeService systemCodeService;
	
	
	
	/**
	 * 
	 * Description: logo图片的上传
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 *             data: 2015-12-11 return void
	 */
	@RequestMapping(value = { "platform/toSavelogo" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String toSavelogo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html");
		String id = (request.getParameter("id")==null?"":request.getParameter("id"));
		String sid = (request.getParameter("sid")==null?"":request.getParameter("sid"));
		String pid = (request.getParameter("pid")==null?"":request.getParameter("pid"));// pid 酒店供应商id
		String details = (request.getParameter("details")==null?"":request.getParameter("details"));// details 客房详情
		String notes = (request.getParameter("notes")==null?"":request.getParameter("notes"));// notes 购买须知 --
		String costExplain = (request.getParameter("costExplain")==null?"":request.getParameter("costExplain"));// costExplain 费用说明 --
		String refundExplain = (request.getParameter("refundExplain")==null?"":request.getParameter("refundExplain"));// refundExplain 退款说明
		String other = (request.getParameter("other")==null?"":request.getParameter("other"));// other 其他说明
		String property = (request.getParameter("property")==null?"":request.getParameter("property")); // property 产品属性（推荐、热卖、特价、新品）
		String bedType = (request.getParameter("bedType")==null?"":request.getParameter("bedType")); // bedType 床型（单床、双床、大床）
		String breakfast = (request.getParameter("breakfast")==null?"":request.getParameter("breakfast")); // breakfast 是否含早餐（是、否）
		String roomName = (request.getParameter("roomName")==null?"":request.getParameter("roomName")); // roomName 客房名称 --
		String roomNum = (request.getParameter("roomNum")==null?"":request.getParameter("roomNum"));// roomNum 房间数
		String overplusNum = (request.getParameter("overplusNum")==null?"":request.getParameter("overplusNum"));// overplusNum 剩余房间数
		String virtualCoin = (request.getParameter("virtualCoin")==null?"":request.getParameter("virtualCoin"));// virtualCoin 度假币 --
		String coin = (request.getParameter("coin")==null?"":request.getParameter("coin")); // coin 实体货币 --
		String picArr = (request.getParameter("picarry")==null?"":request.getParameter("picarry"));// picArr 图片集
		String picUrl = (request.getParameter("picUrl")==null?"":request.getParameter("picUrl"));// picArr  原来的图片路径
		String picname = "";
		String payway = (request.getParameter("payway")==null?"":request.getParameter("payway")); // bedType 床型（单床、双床、大床）

		
		String wifi_s = "";
		String installationCheckBox_s = "";
		String serviceCheckBox_s = "";
		String wifi[] =request.getParameterValues("wifi");// wifi
		if (null!=wifi&&wifi.length > 0) {   
			for (String str : wifi) {
				wifi_s += str + ",";
			}
		}
		String installationCheckBox[]=request.getParameterValues("installationCheckBox"); // 酒店设备
		if(null!=installationCheckBox&&installationCheckBox.length > 0){
		for (String str : installationCheckBox) {
			installationCheckBox_s += str + ",";
		}
		}
		
		String serviceCheckBox[]=request.getParameterValues("serviceCheckBox"); //酒店服务
		if(null!=serviceCheckBox&&serviceCheckBox.length > 0){
		for (String str : serviceCheckBox) { 
			serviceCheckBox_s += str + ",";
		}
		}
		String name = request.getParameter("name");
		try {
			Map<String, String> remap = new HashMap<String, String>();
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile myfiles = multipartRequest.getFile("file");
			String realPath = request.getSession().getServletContext()
					.getRealPath("/upload");
			remap = tool.uploadExcel(request, response, realPath, myfiles);
			if ("true".equals(remap.get("flag"))) {// 文件上传成功
				 picname ="upload/"+remap.get("filename"); // 文件名称
				// 存入数据库
				Map map = new HashMap();
				map.put("name", name);
				map.put("picname", picname);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(null==picname||picname.equals("")){
			picname=picUrl;
		}
		
		String sql = "update hotelroom set id = '"+sid+"', details = '" + details + "', notes = '" + notes 
				+ "', costExplain = '" + costExplain + "', refundExplain = '" + refundExplain + "', other = '" 
				+ other + "', property = '" + property + "', bedType = '" + bedType + "', breakfast = '" 
				+ breakfast + "', wifi = '" + wifi_s + "', roomName = '" + roomName + "', roomNum = '" + roomNum 
				+ "', overplusNum = '" + overplusNum + "',  virtualCoin = '" 
				+ virtualCoin + "', coin = '" + coin + "', service = '" + serviceCheckBox_s + "', installation = '" 
				+ installationCheckBox_s + "', ";
		
		if (StringUtils.isNotBlank(picname)) {
			sql += " itemPic = '" + picname + "' , ";
		}
		if (StringUtils.isNotBlank(picname)) {
			sql += " picArr = '" + picArr + "',  ";
		}
		if (StringUtils.isNotBlank(payway)) {
			sql += " payway = '" + payway + "',  ";
		}
		sql+="  pid = '" + pid + "'    where id = '" + id +"' ";
		
		
		
		Statement stat = null;
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			stat = conn.createStatement();
			System.out.println("修改产品=========" + sql);
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
		return  "admin/product/list";
	}
	
	
	/**
	 * 
	 * Description:   产品添加
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 *             data: 2015-12-11 return void
	 */
	@RequestMapping(value = { "platform/addProduct" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String addProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html");
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
		String picArr = (request.getParameter("picarry")==null?"":request.getParameter("picarry"));// 轮播图存储ID
		String payway = (request.getParameter("payway")==null?"":request.getParameter("payway"));// 轮播图存储ID
		String wifi_s = "";
		String installationCheckBox_s = "";
		String serviceCheckBox_s = "";
		String wifi[] =request.getParameterValues("wifi");// wifi
		if (null!=wifi&&wifi.length > 0) {   
			for (String str : wifi) {
				wifi_s += str + ",";
			}
		}
		String installationCheckBox[]=request.getParameterValues("installationCheckBox"); // 酒店设备
		if(null!=installationCheckBox&&installationCheckBox.length > 0){
		for (String str : installationCheckBox) {
			installationCheckBox_s += str + ",";
		}
		}
		
		String serviceCheckBox[]=request.getParameterValues("serviceCheckBox"); //酒店服务
		if(null!=serviceCheckBox&&serviceCheckBox.length > 0){
		for (String str : serviceCheckBox) { 
			serviceCheckBox_s += str + ",";
		}
		}
		String roomName = (request.getParameter("roomName")==null?"":request.getParameter("roomName")); // roomName 客房名称 --
		String roomNum = (request.getParameter("roomNum")==null?"":request.getParameter("roomNum"));// roomNum 房间数
		String overplusNum = (request.getParameter("overplusNum")==null?"":request.getParameter("overplusNum"));// overplusNum 剩余房间数
		String itemPic = (request.getParameter("itemPic")==null?"":request.getParameter("itemPic")); // itemPic 产品首图
		String virtualCoin = (request.getParameter("virtualCoin")==null?"":request.getParameter("virtualCoin"));// virtualCoin 度假币 --
		String coin = (request.getParameter("coin")==null?"":request.getParameter("coin")); // coin 实体货币 --
		String service = (request.getParameter("service")==null?"":request.getParameter("service"));// service 酒店服务 --
		String installation = (request.getParameter("installation")==null?"":request.getParameter("installation"));// installation 酒店设施 --
	     //String picArr = (request.getParameter("picArr")==null?"":request.getParameter("picArr"));// picArr 图片集icArr = "";// picArr 图片集
		String date = new Date().toLocaleString();
		String dstPath = request.getSession().getServletContext().getRealPath("upload") + "\\";
		String picname = "";
	
		String name = request.getParameter("name");
		try {
			Map<String, String> remap = new HashMap();
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile myfiles = multipartRequest.getFile("file");
			String realPath = request.getSession().getServletContext()
					.getRealPath("/upload");
			remap = tool.uploadExcel(request, response, realPath, myfiles);
			if ("true".equals(remap.get("flag"))) {// 文件上传成功
				 picname ="upload/"+remap.get("filename"); // 文件名称
				// 存入数据库

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql = "insert into hotelroom(id, pid, details, notes, costExplain, refundExplain, other, property, bedType, breakfast, wifi, roomName, "
				+ "roomNum, overplusNum,  itemPic, virtualCoin, coin, service, installation, picArr,payway)  values('" + id
				+ "','" + pid + "','" + details + "','" + notes + "','" + costExplain + "','" + refundExplain+ "','" + other+ "','" + property+ "','" 
				+ bedType+ "','" + breakfast + "','" + wifi_s+ "','" + roomName+ "','" + roomNum+ "','" + overplusNum+ "','" + picname+ "','" + virtualCoin 
				+ "','" + coin+ "','"+ serviceCheckBox_s+ "','" + installationCheckBox_s+ "','"+ picArr+ "','"+payway+"')";
		Statement stat = null;
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
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
		return  "admin/product/list";
	}

	
	
/**
 *  添加产品页面跳转
 * @param model
 * @return
 */
	@RequestMapping(value= { "product/addproduct"  })
	public String addProduct(Model model){
		return  "admin/product/addProduct";
	}
	
	
	/**
	 * 添加产品页面跳转
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "product/updateProduct" })
	public String updateProduct(Model model, String id) {
		Map<String, String> map = new HashMap<String, String>();
		  Map  t_map;
		map.put("id", id);
		List list = hotelManagerService.getRoomList(map);
		List<Map> attachL=new ArrayList<Map>();
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> _map = (Map<String, String>) list.get(0);
				String _id[] = _map.get("picArr").split(",");
				  for (int j = 0; j < _id.length; j++) {
					  if(StringUtils.isNotBlank(_id[j])){
						  Map r_map=new HashMap();
						  r_map.put("id", _id[j]);
						  if(null!=systemCodeService.getAttach(r_map)&&systemCodeService.getAttach(r_map).size()>0){
							    t_map= (Map) systemCodeService.getAttach(r_map).get(0);
						  }else{
							  t_map=null;  
						  }
						  attachL.add(t_map);
					  }
				}
			}
		}
		model.addAttribute("attachL", attachL);
		return "admin/product/modProduct";
	}
	
	/**
	 * 房型价格批量设置
	 * @param model
	 * @param id 房型ID
	 * @return
	 */
	@RequestMapping(value = { "product/priceManager" })
	public String priceManager(Model model,String id){
      // 加载房型价格信息
	   List list =	hotelManagerService.getProductList(id, "");
	   if(null!=list&&list.size()>0){
		   JSONArray  jsl=JSONArray.fromObject(list);
		   model.addAttribute("pjson", jsl);
	   }else{
		   model.addAttribute("pjson", list); 
	   }
		model.addAttribute("roomId", id);
		return "admin/product/PriceManager";
	}
	
	@RequestMapping(value = { "product/insertPrice" })
	@ResponseBody
	public Msg insertPrice(Model model, String data, String roomId) {
		// 加载房型价格信息
		Msg msg = new Msg();
		try {
			JSONArray ja = JSONArray.fromObject(data);
			List<PriceManager> li = JSONArray.toList(ja, PriceManager.class);
			for (PriceManager priceManager : li) {
				priceManager.setRoomId(roomId);
				hotelManagerService.insertProduct(priceManager);
			}
			msg.setResource(true);
			msg.setBundle("设置成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setResource(false);
			msg.setBundle("系统错误！");
		}
		return msg;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "product/HotelSearchByCatAction" })
	@ResponseBody
	public String HotelSearchByCatAction(Model model,HttpServletRequest request,HttpServletResponse response) {
		Msg msg = new Msg();
		JSONObject resultObj=new JSONObject();
		String categoryId = (request.getParameter("categoryId")==null?"":request.getParameter("categoryId"));
		String key = (request.getParameter("key")==null?"":request.getParameter("key"));
		String condition = "";
		if(!categoryId.equals("")){
			condition += " and r.other = '" + categoryId + "' ";
		}
		if(!key.equals("")){
			condition += " and s.hotelName like '%" + key + "%' or s.address like '%" + key + "%'";
		}
		condition +="  and r.isdeleted='0' ";
		String sql = "SELECT r.id, r.roomName, r.virtualCoin, r.coin, r.payway , s.hotelName, s.tel, s.area, s.address, r.itemPic FROM hotelroom r  join 	hotelsupplier  s on r.pid = s.id  WHERE 1 = 1 and r.isdeleted='0' " + condition;
		System.out.println(sql);
		Connection conn = DBManager.getConnection();
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
				m.put("payway", rs.getString("payway"));
				m.put("hotelName", rs.getString("hotelName"));
				m.put("tel", rs.getString("tel"));
				m.put("area", rs.getString("area"));
				m.put("address", rs.getString("address"));
				m.put("itemPic", rs.getString("itemPic"));
				tempLst.add(m);
			}
			List recommendLst = new ArrayList();
			String recommendSql = "select id, jiudianKind from hotelsupplierkind where jiudianStatus = '0' ";
			
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
			 resultObj = JSONObject.fromObject(map);
			msg.setData(resultObj.toString());
			System.out.println(resultObj.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultObj.toString();
	} 
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "product/HotelSearchAction" })
	@ResponseBody
	public String HotelSearchAction(Model model,HttpServletRequest request,HttpServletResponse response) {
		Msg msg = new Msg();
		DBManager dbmanager=new DBManager();
		JSONObject resultObj=new JSONObject();
		String key = (request.getParameter("key")==null?"":request.getParameter("key"));
		String id = (request.getParameter("id")==null?"":request.getParameter("id"));
		String ids="";
		String condition = "";
		if(!key.equals("")){
			condition += " and s.hotelName like '%" + key + "%' or s.address like '%" + key + "%'";
		}
		
		if(!id.equals("")){
			condition += " and r.id = '" + id + "' ";
			 String s_sql="select  CONCAT_WS('',service,installation) as service   from  hotelroom t  where  t.isdeleted='0' and t.id='" + id + "' ";
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
		
		String sql = "SELECT r.id,s.id as  hotelId, r.roomName, r.details, r.notes,r.payway, r.costExplain, r.refundExplain, r.other, r.bedType, r.wifi, r.breakfast, r.virtualCoin, r.coin, s.hotelName, s.tel, s.area, s.address, s.coordinate, r.itemPic, s.hotelKind, r.service, r.installation, r.picArr FROM hotelsupplier s, hotelroom r WHERE   r.isdeleted='0'  and r.pid = s.id" + condition;
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
				m.put("payway", rs.getString("payway"));
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
				m.put("breakfast", rs.getString("breakfast"));
				//进行判断，看是否在其他地方设置，详情页，通用价格，不需要给出当天价格
		     	List list =	hotelManagerService.getProductList(rs.getString("id"), DateUtil.dateFormatForymd(new Date()));
				if(null!=list&&list.size()>0){
                   Map<String,String>  remap=(Map<String, String>) list.get(0);
					m.put("virtualCoin", remap.get("djbprice"));
					m.put("coin",    remap.get("price"));
				}else{
				}
					m.put("virtualCoin", rs.getString("virtualCoin"));
					m.put("coin", rs.getString("coin"));

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
			
		List erevyList=new ArrayList();	
			 // 这里加载此房型60天内的数据
		String _d=DateUtil.dateFormatForymd(new Date());
	    List fxlist =	hotelManagerService.getProductListForDays(id, "");
	    if(null!=fxlist&&fxlist.size()>0){
	    	  for (int j = 0; j < fxlist.size(); j++) {
				Map<String,String> dmap=(Map<String,String>) fxlist.get(j);
				  String d=dmap.get("date");
				/*  if(AccountDate.countDay(DateUtil.dateFormatForymd(new Date()), d)==1L){
					  erevyList.add(fxlist.get(j)); 
				  }*/
				  
				  long l=  AccountDate.countDay(_d, d);
				    if(l>=1){
				    	for (int k = 0; k < l; k++) {
				    		erevyList.add("{date=null}");
						}
						erevyList.add(fxlist.get(j));
					}else{
				    	  erevyList.add(fxlist.get(j)); 
				    }
				  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
				  _d=DateUtil.getSpecifiedDayAfter(d);  // 重新赋值，继续循环
			}
	    }
	        map.put("priceList", erevyList);
			map.put("rows", tempLst);
			map.put("serviceLst", serviceLst);
			map.put("installationLst", installationLst);
			map.put("picLst", picLst);
			map.put("total", tempLst.size());
			 resultObj = JSONObject.fromObject(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultObj.toString();
	}

	@RequestMapping(value = {"product/list"},  method = {org.springframework.web.bind.annotation.RequestMethod.POST})
	@ResponseBody
	public String productList(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String queryName = request.getParameter("queryName"); // 获取账号
		String _curPage = request.getParameter("curPage") == null ? "1" : request.getParameter("curPage");
		String _pageNum = request.getParameter("pageNum") == null ? "" : request.getParameter("pageNum");
		Map map = new HashMap();
		Map _map = new HashMap();
		Connection conn = DBManager.getConnection();
		DBManager db = new DBManager();
		String sql = "SELECT r.*, s.hotelName FROM hotelroom r LEFT JOIN hotelsupplier s ON r.pid = s.id where  1=1";
		if (queryName != null) {
			sql += " and  roomName like '%"+queryName+"%'";
		}
		sql += " order by isdeleted ";
		int pageNum = Integer.parseInt(_pageNum);
		int currPage = Integer.parseInt(_curPage);
		int start = pageNum * (currPage - 1);
		int end = 20;
		String ywSql = tool.paginationFormysql(sql.toString(), start, end);//拼装sql（分页）
		System.out.println("拼装的sql  " + ywSql);
		List mqryList;
		int count;
		try {
			mqryList = db.find(ywSql);
			count = db.find(sql.toString()).size();
			System.out.println("获取分页后信息：" + mqryList);

			_map.put("rowcount", count);
			//总共有多少页
			int pageCount = 0;
			if (count > 0) {
				pageCount = (count - 1) / pageNum + 1;
			}
			_map.put("pagecount", pageCount);
			_map.put("info", mqryList);



		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json=JSONObject.fromObject(_map);
		return json.toString();
	}
	public static void main(String[] args) {
		List a=new ArrayList();
		for (int i = 0; i < 5; i++) {
			int [] _l ={1,2,3};
			a.add("{date=null}");
		}
		System.out.println(a);
	}
	
	
}


