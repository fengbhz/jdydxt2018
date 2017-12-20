package com.vdong.action;

import com.vdong.commons.db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class HotelSystemAction extends HttpServlet {

	public HotelSystemAction() {
		super();
	}

	public void destroy() {
		super.destroy();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		this.doPost(request, response);
		out.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String publicSignalName = (request.getParameter("publicSignalName")==null?"":request.getParameter("publicSignalName"));
		String codePic = (request.getParameter("fujian")==null?"":request.getParameter("fujian"));
		String appId = (request.getParameter("appId")==null?"":request.getParameter("appId"));
		String kfPhone = (request.getParameter("kfPhone")==null?"":request.getParameter("kfPhone"));
		String appsecret = (request.getParameter("appsecret")==null?"":request.getParameter("appsecret"));
		String commercialTenantNo = (request.getParameter("commercialTenantNo")==null?"":request.getParameter("commercialTenantNo"));
		String secretKey = (request.getParameter("secretKey")==null?"":request.getParameter("secretKey"));
		String payCertificateFile = (request.getParameter("payCertificate")==null?"":request.getParameter("payCertificate"));
		String certificateKeyFile = (request.getParameter("certificateKey")==null?"":request.getParameter("certificateKey"));
		
		String sysSql = "update hotelsystem set publicSignalName = '" + publicSignalName 
						+ "', codePic = '" + codePic 
				        + "', appId = '" + appId 
				        + "', appsecret = '" + appsecret 
				        + "', commercialTenantNo = '" + commercialTenantNo 
				        + "', secretKey = '" + secretKey 
				        + "', payCertificateFile = '" + payCertificateFile 
				        + "', kfPhone = '" + kfPhone 
				        + "', certificateKeyFile = '" + certificateKeyFile + "'";
		
		Connection conn = DBManager.getConnection();
		PreparedStatement psinsert;
		try {
			psinsert = conn.prepareStatement(sysSql);
			psinsert.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("admin/sysPro.jsp");
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		
	}

}
