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
 * 修改产品
 * @author win7
 *
 */
public class ModProductAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ModProductAction() {
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
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		DBManager dbm = new DBManager();
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
		String wifi = (request.getParameter("wifi")==null?"":request.getParameter("wifi")); // wifi 无线网络（是、否）
		String roomName = (request.getParameter("roomName")==null?"":request.getParameter("roomName")); // roomName 客房名称 --
		String roomNum = (request.getParameter("roomNum")==null?"":request.getParameter("roomNum"));// roomNum 房间数
		String overplusNum = (request.getParameter("overplusNum")==null?"":request.getParameter("overplusNum"));// overplusNum 剩余房间数
		String itemPic = (request.getParameter("itemPic")==null?"":request.getParameter("itemPic")); // itemPic 产品首图
		String virtualCoin = (request.getParameter("virtualCoin")==null?"":request.getParameter("virtualCoin"));// virtualCoin 度假币 --
		String coin = (request.getParameter("coin")==null?"":request.getParameter("coin")); // coin 实体货币 --
		String service = (request.getParameter("service")==null?"":request.getParameter("service"));// service 酒店服务 --
		String installation = (request.getParameter("installation")==null?"":request.getParameter("installation"));// installation 酒店设施 --
		String picArr = (request.getParameter("picArr")==null?"":request.getParameter("picArr"));// picArr 图片集
		String date = new Date().toLocaleString();
		//Object user = request.getSession().getAttribute("user");
		//String appuser = "";
		//if (user != null && appuser.toString() != null) {
		//  appuser = (String) user;
		//}
		String dstPath = request.getSession().getServletContext().getRealPath("upload") + "\\";
		// 文件上传部分
/*		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart == true) {
			try {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				// 得到所有的表单域，它们目前都被当作FileItem
				List<FileItem> fileItems = upload.parseRequest(request);
				Iterator<FileItem> iter = fileItems.iterator();
				// 依次处理每个表单域
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					if (item.isFormField()) {
						// 如果item是正常的表单域
						String names = item.getFieldName();
						String value = item.getString("UTF-8");
						System.out.println("表单域名为:" + names + "表单域值为:" + value);
						if (names.equals("id")) {
							id = value;
						}
						if (names.equals("pid")) {
							pid = value;
						}
						if (names.equals("details")) {
							details = value;
						}
						if (names.equals("notes")) {
							notes = value;
						}
						if (names.equals("costExplain")) {
							costExplain = value;
						}
						if (names.equals("refundExplain")) {
							refundExplain = value;
						}
						if (names.equals("other")) {
							other = value;
						}
						if (names.equals("property")) {
							property = value;
						}
						if (names.equals("bedType")) {
							bedType = value;
						}
						if (names.equals("breakfast")) {
							breakfast = value;
						}
						if (names.equals("wifi")) {
							wifi = value;
						}
						if (names.equals("roomName")) {
							roomName = value;
						}
						if (names.equals("roomNum")) {
							roomNum = value;
						}
						if (names.equals("overplusNum")) {
							overplusNum = value;
						}
						if (names.equals("virtualCoin")) {
							virtualCoin = value;
						}
						if (names.equals("coin")) {
							coin = value;
						}
						if (names.equals("service")) {
							service = value;
						}
						if (names.equals("installation")) {
							installation = value;
						}
						if (names.equals("picArr")) {
							picArr = value;
						}
						if (names.equals("itemPic")) {
							itemPic = value;
							itemPic = dbm.TextToHtml(itemPic);
						}
					} else {
						// 如果item是文件上传表单域
						// 获得文件名及路径
						String fileName = item.getName();
						if (fileName.trim().length() == 0) {
							continue;
						}
						if (fileName != null) {
							File fullFile = new File(item.getName());
							System.out.println("fullFile::  " + fullFile);
							String fujianFileName = fullFile.getName();
							String newFujianName = new Date().getTime()
									+ fujianFileName.substring(fujianFileName.indexOf("."));
							File fileOnServer = new File(dstPath, newFujianName);
							item.write(fileOnServer);
							System.out.println("文件" + fileOnServer.getName() + "上传成功");
							itemPic = "upload/" + fileOnServer.getName();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("the enctype must be multipart/form-data");
		}*/

		String sql = "update hotelroom set id = '"+sid+"', pid = '" + pid + "', details = '" + details + "', notes = '" + notes 
				+ "', costExplain = '" + costExplain + "', refundExplain = '" + refundExplain + "', other = '" 
				+ other + "', property = '" + property + "', bedType = '" + bedType + "', breakfast = '" 
				+ breakfast + "', wifi = '" + wifi + "', roomName = '" + roomName + "', roomNum = '" + roomNum 
				+ "', overplusNum = '" + overplusNum + "', itemPic = '" + itemPic + "', virtualCoin = '" 
				+ virtualCoin + "', coin = '" + coin + "', service = '" + service + "', installation = '" 
				+ installation + "', picArr = '" + picArr + "' where id = '" + id +"'";
		Statement stat = null;
		Connection conn = null;
		try {
			conn = dbm.getConnection();
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
				// TODO Auto-generated catch block
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
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
