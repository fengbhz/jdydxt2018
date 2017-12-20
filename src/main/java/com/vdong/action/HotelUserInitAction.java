package com.vdong.action;

import com.vdong.commons.db.DBManager;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("serial")
public class HotelUserInitAction extends HttpServlet {

	public HotelUserInitAction() {
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String id = UUID.randomUUID().toString().substring(0, 32);
		String mobile = (request.getParameter("mobile")==null?"":request.getParameter("mobile"));
		String nickName = (request.getParameter("nickName")==null?"":request.getParameter("nickName"));
		String gender = (request.getParameter("gender")==null?"":request.getParameter("gender"));
		String language = (request.getParameter("language")==null?"":request.getParameter("language"));
		String city = (request.getParameter("city")==null?"":request.getParameter("city"));
		String province = (request.getParameter("province")==null?"":request.getParameter("province"));
		String country = (request.getParameter("country")==null?"":request.getParameter("country"));
		String avatarUrl = (request.getParameter("avatarUrl")==null?"":request.getParameter("avatarUrl"));
		String code = (request.getParameter("code")==null?"":request.getParameter("code"));
		
		String appid = "wx381d504d76083eb7";
		String secretid = "e2f8f79b73cac46d1e20210056c618b1";
		String openid = "";
		String getOpenIdUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" 
				            + appid 
							+ "&secret="
							+ secretid
							+ "&js_code="
							+ code
							+ "&grant_type=authorization_code";
		
		System.out.println(getOpenIdUrl);
		
		BufferedReader in = null;
        String result = "";
        PrintWriter outUrl = null;
        try {
            URL realUrl = new URL(getOpenIdUrl);
            URLConnection connUrl = realUrl.openConnection();
            connUrl.setRequestProperty("accept", "*/*");
            connUrl.setRequestProperty("connection", "Keep-Alive");
            connUrl.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connUrl.setDoOutput(true);
            connUrl.setDoInput(true);
            outUrl = new PrintWriter(connUrl.getOutputStream());
            outUrl.flush();
            in = new BufferedReader(new InputStreamReader(connUrl.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }finally{
            try{
                if(outUrl!=null){
                	outUrl.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        
        System.out.println(result);
		
        openid = result.split("openid\":\"")[1].split("\"}")[0];
		Connection conn = DBManager.getConnection();
		String checkSql = "SELECT count(1) as checkNum FROM hoteluser where account = '" + mobile + "' or mobile = '" + mobile + "' ";
		String uerInfoSql = "insert into hoteluser(id, account, password, name, mobile, idCard, userKind, userStutus, virtualCoin, lockVirtualCoin, integral, wechat, belongHotelId, belongHotel, role, nickName, gender, language, city, province, country, avatarUrl, code) "
				          + "select '"
				          + id
				          + "', '"
				          + mobile
				          + "', '', '', '"
				          + mobile
				          + "', '', '0', '0', '0', '0', '0', '', '', '', '', '"
				          + nickName 
				          + "', '"
				          + gender
				          + "', '"
				          + language
				          + "', '"
				          + city
				          + "', '"
				          + province
				          + "', '"
				          + country
				          + "', '"
				          + avatarUrl
				          + "', '"
				          + openid
				          + "' from dual";
		PreparedStatement psCheck;
		PreparedStatement psUserInit;
		try {
			psCheck = conn.prepareStatement(checkSql);
			ResultSet rs = psCheck.executeQuery();
			int checkNum = 0;
			while (rs.next()) {
				checkNum = rs.getInt("checkNum");
			}
			if(checkNum == 0){
				psUserInit = conn.prepareStatement(uerInfoSql);
				psUserInit.execute();
			}
			
			Map map = new HashMap();
			
			map.put("flag", "success");
			
			JSONObject resultObj = JSONObject.fromObject(map);
			out.println(resultObj.toString());
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		
	}

}
