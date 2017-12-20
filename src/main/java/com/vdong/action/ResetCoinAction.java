package com.vdong.action;

import com.vdong.commons.db.DBManager;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("serial")
public class ResetCoinAction extends HttpServlet {

	public ResetCoinAction() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String type=request.getParameter("type");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String sql = "update hoteluser set virtualCoin = '0', lockVirtualCoin = '0' where  1=1 ";
		  if(StringUtils.isNotBlank(type)){
			  sql+="   and userKind="+type;
		  }
		Statement stat = null;
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			stat = conn.createStatement();
			System.out.println(sql);
			stat.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		String url="admin/owner/owner.jsp";
		if(StringUtils.isNotBlank(type)&&type.equals("0")){
			url="admin/owner/friendManager.jsp";
		}
		response.sendRedirect(url);

		
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}
}
