package com.vdong.action;

import com.vdong.commons.db.DBManager;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

@SuppressWarnings("serial")
public class AddOwnerAction extends HttpServlet {

	public AddOwnerAction() {
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
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String owerid = request.getParameter("owerid");
		String id = UUID.randomUUID().toString().substring(0, 32);
		String hotelKind = request.getParameter("hotelKind");
		String hotelName = request.getParameter("hotelName");
		String ownerName = request.getParameter("ownerName");
		String mobile = request.getParameter("mobile");
		String idCard = request.getParameter("idCard");
		String sql ="";
		if(null!=owerid&&!owerid.equals("")){// 执行更新操作
			  String updateSql=" update hotelowner set  ";
               if(StringUtils.isNotBlank(ownerName)){
				   updateSql+=" ownerName= "+ownerName+",";
			   }

			  if(StringUtils.isNotBlank(mobile)){
				  updateSql+=" mobile= "+mobile+",";
			  }

			  if(StringUtils.isNotBlank(idCard)){
				  updateSql+=" idCard= "+idCard +",";
			  }
			  updateSql+=" id= "+owerid +" where  id="+owerid;
			  sql=updateSql;
		  }else{
			   sql = "insert into hotelowner(id, account, name, mobile, idCard, kind, hotelName, status, applyStatus, inputDate, orderNo) select '"
					  + id + "', '"
					  + mobile + "', '"
					  + ownerName + "', '"
					  + mobile + "', '"
					  + idCard + "', '"
					  + hotelKind + "', '"
					  + hotelName
					  + "', '0', '2', sysdate(), (select max(o.orderNo) + 1 from hotelowner o) "
					  + "from dual";
		  }


		Statement stat = null;
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			stat = conn.createStatement();
			System.out.println(sql);
			stat.execute(sql);
			conn.close();
			// 插入用户表
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
		response.sendRedirect("admin/owner/owner.jsp");
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}
}
