package com.vdong.controller;

import com.vdong.commons.bean.Msg;
import com.vdong.commons.db.DBManager;
import com.vdong.services.HotelManagerService;
import com.vdong.commons.util.tool;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 酒吧添加修改账号管理
 * 
 * @author Administrator
 * 
 */
@Controller
public class HotelManagerController {

	@Autowired
	private HotelManagerService hotelManagerService;

	/**
	 * 添加订单
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = {"hotel/addHotel"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
	@SuppressWarnings({"rawtypes", "unchecked"})
	public String addHotel(HttpServletRequest request,
						   HttpServletResponse response) throws IOException {
		boolean flag = true;
		Map map = new HashMap();

		String id = UUID.randomUUID().toString().substring(0, 32);
		String hotelName = (request.getParameter("hotelName") == null ? ""
				: request.getParameter("hotelName"));
		String hotelid = (request.getParameter("hotelid") == null ? ""
				: request.getParameter("hotelid"));
		String order = (request.getParameter("order") == null ? "" : request
				.getParameter("order"));
		String hotelSummary = (request.getParameter("hotelSummary") == null ? ""
				: request.getParameter("hotelSummary"));
		String hotelPic = (request.getParameter("hotelPic") == null ? ""
				: request.getParameter("hotelPic"));
		String hotelAccount = (request.getParameter("hotelAccount") == null ? ""
				: request.getParameter("hotelAccount"));
		String hotelPass = (request.getParameter("hotelPass") == null ? ""
				: request.getParameter("hotelPass"));
		String tel = (request.getParameter("tel") == null ? "" : request
				.getParameter("tel"));
		String area = (request.getParameter("area") == null ? "" : request
				.getParameter("area"));
		String address = (request.getParameter("address") == null ? ""
				: request.getParameter("address"));
		String coordinate = (request.getParameter("coordinate") == null ? ""
				: request.getParameter("coordinate"));
		String remark = (request.getParameter("remark") == null ? "" : request
				.getParameter("remark"));

		Map<String, String> remap = new HashMap<String, String>();
		remap.put("id", id);
		remap.put("hotelName", hotelName);
		remap.put("hotelid", hotelid);
		remap.put("order", order);
		remap.put("hotelSummary", hotelSummary);
		remap.put("hotelPic", hotelPic);
		remap.put("hotelAccount", hotelAccount);
		remap.put("hotelPass", hotelPass);
		remap.put("tel", tel);
		remap.put("area", area);
		remap.put("address", address);
		remap.put("coordinate", coordinate);
		remap.put("remark", remark);
		try {
			int c = hotelManagerService.insertHotel(remap);
			map.put("orderId", id);
			map.put("flag", flag);
			map.put("total", "成功下单");
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}
		return "admin/jiudian/list";
	}

	@RequestMapping(value = {"hotel/checkAccount"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
	@ResponseBody
	public Msg checkAccount(HttpServletRequest request,
							HttpServletResponse response) {
		Msg msg = new Msg();
		String account = request.getParameter("account"); // 获取账号
		String id = request.getParameter("id"); // 获取账号
		String sql = " select * from  admin where userName= '" + account + "'";
		if (StringUtils.isNotBlank(id)) {
			sql += "    and id !=" + id;
		}
		DBManager db = new DBManager();
		try {
			List list = db.find(sql);
			if (null != list && list.size() > 0) {
				msg.setResource(true);
			} else {
				msg.setResource(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setResource(false);
		}
		return msg;
	}


	@RequestMapping(value = {"hotel/roomlist"},  method = {org.springframework.web.bind.annotation.RequestMethod.POST})
	@ResponseBody
	public String hotelList(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		Msg msg = new Msg();
		String queryName = request.getParameter("queryName"); // 获取账号
		String _curPage = request.getParameter("curPage") == null ? "1" : request.getParameter("curPage");
		String _pageNum = request.getParameter("pageNum") == null ? "" : request.getParameter("pageNum");
		Map map = new HashMap();
		Map _map = new HashMap();
		Connection conn = DBManager.getConnection();
		DBManager db = new DBManager();
		String sql = "SELECT s.id, s.hotelName, s.hotelSummary, s.hotelPic, s.hotelAccount, s.tel, s.area, s.address, s.coordinate, s.remark, s.order as orders, DATE_FORMAT(s.submitDate, '%Y-%m-%d') AS submitDate, s.hotelKind, s.status , k.id  as  kid, k.jiudianKind FROM hotelsupplier s LEFT JOIN hotelsupplierkind k ON s.hotelKind = k.id ORDER BY `order`";
		if (queryName != null) {
			sql = "SELECT s.id, s.hotelName, s.hotelSummary, s.hotelPic, s.hotelAccount, s.tel, s.area, s.address, s.coordinate, s.remark, s.order as orders, DATE_FORMAT(s.submitDate, '%Y-%m-%d') AS submitDate, s.hotelKind, s.status , k.id as  kid, k.jiudianKind FROM hotelsupplier s LEFT JOIN hotelsupplierkind k ON s.hotelKind = k.id where s.hotelName like '%"
					+ queryName
					+ "%' or s.address like '%"
					+ queryName
					+ "%' order by s.order";
		}
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
}