package com.vdong.action;

import com.vdong.commons.db.DBManager;
import com.vdong.commons.util.DateUtil;

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
 * 修改广告
 * @author win7
 *
 */
public class ModAdvertisementAction extends HttpServlet {

	public ModAdvertisementAction() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");;// 广告ID
		String adKind = request.getParameter("adKind");;// 广告分类
		String adTitle = request.getParameter("adTitle");;// 广告标题
		String info = request.getParameter("info");;// 广告内容
		if(info!=null){
			//info=dbm.TextToHtml(info);
		}
		String sql = "update hotelad set adKind='"+adKind+"',adTitle='"+adTitle+"',adSubmitDate='"+DateUtil.dateFormat(new Date())+"',adContent='"+info+"' where id="+id;
		System.out.println("修改广告===="+sql);

		Statement stat = null;
		Connection conn=null;
		try {
			conn=DBManager.getConnection();
			stat = conn.createStatement();
			stat.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(stat!=null)
					stat.close();
				if(conn!=null)
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
