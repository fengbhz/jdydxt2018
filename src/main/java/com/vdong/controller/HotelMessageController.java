package com.vdong.controller;

import com.vdong.services.HotelMsgService;
import com.vdong.commons.util.Constants;
import com.vdong.commons.util.tool;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员用户管理
 * 
 * @author Administrator
 * 
 */
@Controller
public class HotelMessageController {

	@Autowired
	private HotelMsgService  hotelMsgService;

	
	/**
	 *  添加消息列表
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "msg/addmsg" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String addMsg(HttpServletRequest request,HttpServletResponse response) throws IOException {
		boolean flag=true;
		Map map=new HashMap();
		String title = (request.getParameter("title")==null?"":request.getParameter("title"));
		String date = (request.getParameter("date")==null?"":request.getParameter("date"));
		String info = (request.getParameter("info")==null?"":request.getParameter("info"));
		String id = (request.getParameter("id")==null?"":request.getParameter("id"));
		String appuser = (request.getParameter("appuser")==null?"":request.getParameter("appuser"));
		String picname="";
		String type = (request.getParameter("type")==null?"":request.getParameter("type"));
		try {
			Map<String, String> remap = new HashMap<String, String>();
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile myfiles = multipartRequest.getFile("pic");
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
		
			Map<String,String>  remap=new HashMap<String,String>(); 
			remap.put("id", id);
			remap.put("title", title);
			remap.put("date", date);
			remap.put("info", info);
			remap.put("appuser", appuser);
			remap.put("type", type);
			remap.put("picname", picname);
		try {
			int c=0;
			if (null != id && !id.equals("")) {
				hotelMsgService.updateMsg(remap);
			} else {
				hotelMsgService.insertMsg(remap);
			}
			
			if(c>0){
				map.put("success", true);
			}else{
				map.put("success", false);
			}
			
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}
		return "admin/news/list";
	}
	
	
	/**
	 * 管理信息
	 * 
	 * @param request
	 * @param response
	 * @param type
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "msg/managerMsg" },  method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String managerMsg(HttpServletRequest request,
			HttpServletResponse response, String type, String id)
			throws IOException {
		Map map = new HashMap();
		try {
			if (id.equals("-1")) {// 获取消息列表
				List list = hotelMsgService.getMsg(type, "");
				if (null == list || list.size() <= 0) {
					map.put("success", false);
					map.put("msg", "查无消息！");
				} else {
					map.put("success", true);
					map.put("msg", list);
				}
			} else {  //  更新读取状态
				int c = hotelMsgService
						.updateStatus(id, Constants.ALREADY_READ);
				if (c > 0) {
					map.put("success", true);
				} else {
					map.put("success", false);
				}
			}
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}
		return JSONObject.fromObject(map).toString();
	}
	
	
	/**
	 *  添加发现列表
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "msg/addAd" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String addAd(HttpServletRequest request,HttpServletResponse response) throws IOException {
		boolean flag=true;
		Map map=new HashMap();
		 String id =       (request.getParameter("id") == null ? "" : request.getParameter("id"));
		 String adKind = (request.getParameter("adKind") == null ? "" : request.getParameter("adKind"));
		 String adTitle = (request.getParameter("adTitle") == null ? "" : request.getParameter("adTitle"));
		 String adContent = (request.getParameter("info") == null ? "" : request.getParameter("info"));
		 String adStatus = (request.getParameter("adStatus") == null ? "" : request.getParameter("adStatus"));
		String adPic="";
		try {
			Map<String, String> remap = new HashMap<String, String>();
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile myfiles1 = multipartRequest.getFile("adPic");
			String realPath = request.getSession().getServletContext().getRealPath("/upload");
			
			try {
				remap = tool.uploadExcel(request, response, realPath, myfiles1);
				if ("true".equals(remap.get("flag"))) {// 文件上传成功
					adPic +="upload/"+remap.get("filename"); // 文件名称
				}
			} catch (Exception e) {
                  e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			Map<String,String>  remap=new HashMap<String,String>(); 
			remap.put("id", id);
			remap.put("adKind", adKind);
			remap.put("adTitle", adTitle);
			remap.put("adContent", adContent);
			remap.put("adStatus", "1");
			remap.put("adPic", adPic);
			remap.put("adPic2", adPic);
			remap.put("adPic3", adPic);
			remap.put("adPic4", adPic);
			remap.put("adPic5", adPic);
		try {
			int c=0;
			if (null != id && !id.equals("")) {
				c=hotelMsgService.updateAd(remap);
			} else {
				c=hotelMsgService.insertAd(remap);
			}
			
			if(c>0){
				map.put("success", true);
			}else{
				map.put("success", false);
			}
			
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}
		return "admin/Advertisement/list";
	}



	/**
	 *  添加发现列表
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "msg/updateAd" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String updateAd(HttpServletRequest request,HttpServletResponse response) throws IOException {
		boolean flag=true;
		Map map=new HashMap();
		String id =       (request.getParameter("id") == null ? "" : request.getParameter("id"));
		String adKind = (request.getParameter("adKind") == null ? "" : request.getParameter("adKind"));
		String adTitle = (request.getParameter("adTitle") == null ? "" : request.getParameter("adTitle"));
		String adContent = (request.getParameter("info") == null ? "" : request.getParameter("info"));
		String adStatus = (request.getParameter("adStatus") == null ? "" : request.getParameter("adStatus"));
		String adPic="";
		try {
			Map<String, String> remap = new HashMap<String, String>();
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile myfiles1 = multipartRequest.getFile("adPic");
			String realPath = request.getSession().getServletContext().getRealPath("/upload");

			try {
				remap = tool.uploadExcel(request, response, realPath, myfiles1);
				if ("true".equals(remap.get("flag"))) {// 文件上传成功
					adPic +="upload/"+remap.get("filename"); // 文件名称
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String,String>  remap=new HashMap<String,String>();
		remap.put("id", id);
		remap.put("adKind", adKind);
		remap.put("adTitle", adTitle);
		remap.put("adContent", adContent);
		remap.put("adStatus", "1");
		remap.put("adPic", adPic);
		remap.put("adPic2", adPic);
		remap.put("adPic3", adPic);
		remap.put("adPic4", adPic);
		remap.put("adPic5", adPic);
		try {
			int c=0;
			if (null != id && !id.equals("")) {
				hotelMsgService.updateAd(remap);
			} else {
				hotelMsgService.insertAd(remap);
			}

			if(c>0){
				map.put("success", true);
			}else{
				map.put("success", false);
			}

		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}
		return "admin/Advertisement/list";
	}

}
