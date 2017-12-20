package com.vdong.action;

import com.vdong.commons.db.DBManager;
import com.vdong.commons.util.DateUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 添加广告
 * 
 * @author win7
 * 
 */
public class AddAdvertisementAction extends HttpServlet {
	public AddAdvertisementAction() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String id = "";// 广告ID
		String adKind = "";// 广告分类
		String adTitle = "";// 广告标题
		String info = request.getParameter("info");// 广告内容
		String adPic = "";// 广告图片（路径）
		String dstPath = request.getSession().getServletContext()
				.getRealPath("upload")+ "\\";
		// 文件上传部分
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
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
						if (names.equals("adKind")) {
							adKind = value;
						}
						if (names.equals("adTitle")) {
							adTitle = value;
						}
						if (names.equals("info")) {
							info = value;
							// info = dbm.TextToHtml(info);
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
									+ fujianFileName.substring(fujianFileName
											.indexOf("."));
							File fileOnServer = new File(dstPath, newFujianName);
							item.write(fileOnServer);
							System.out.println("文件" + fileOnServer.getName()
									+ "上传成功");
							adPic = "upload/" + fileOnServer.getName();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("the enctype must be multipart/form-data");
		}
		String sql = "insert into hotelad(id, adKind, adSubmitDate, adContent, adTitle, adPic, adStatus)  values('"
				+ id
				+ "','"
				+ adKind
				+ "','"
				+ DateUtil.dateFormat(new Date())
				+ "','" + info + "','" + adTitle + "','" + adPic + "',+'1')";
		Statement stat = null;
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			stat = conn.createStatement();
			System.out.println("添加广告=========" + sql);
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
		response.sendRedirect("admin/Advertisement/list.jsp");
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
	}

}
