package com.vdong.action;

import com.vdong.commons.db.DBManager;
import com.vdong.commons.util.XmlConverUtil;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("serial")
public class HotelBeforePayAction extends HttpServlet {

	public HotelBeforePayAction() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
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
		response.setCharacterEncoding("utf-8");
        response.setContentType("text/xml;charset=utf-8");
        response.setHeader("Cache-control", "no-cache");
		PrintWriter out = response.getWriter();
		String appid = "wx381d504d76083eb7";
		String secretid = "e2f8f79b73cac46d1e20210056c618b1";
		String mch_id = "1483402252";
		String nonce_str = UUID.randomUUID().toString().substring(0, 32);
		String sign = "";
		String account = (request.getParameter("account")==null?"":request.getParameter("account"));
		String prepayId = (request.getParameter("prepayId")==null?"":request.getParameter("prepayId"));
		String body = (request.getParameter("body")==null?"":request.getParameter("body"));
		body="test";
		String out_trade_no = (request.getParameter("out_trade_no")==null?"":request.getParameter("out_trade_no"));
		String total_fee = (request.getParameter("total_fee")==null?"":request.getParameter("total_fee"));
		String prepay_id="";
		
		int ze = (int)(Float.parseFloat(total_fee)*100);
		
		String openid = "";
		String sql = "SELECT code FROM hoteluser where account = '" + account + "'";
		System.out.println(sql);
		Connection conn = DBManager.getConnection();
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				openid = rs.getString("code");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String getOpenIdUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		System.out.println(getOpenIdUrl);
		
		BufferedReader in = null;
        String result = "";
        try {
           String tempA = "appid="+ appid 
            		     + "&body=" + body
            		     + "&device_info=" + "WEB"
            		     + "&fee_type=" + "CNY"
					     + "&mch_id=" + mch_id
					     + "&nonce_str=" + nonce_str
					     + "&notify_url=" + "http://www.weixin.qq.com/wxpay/pay.php"
					     + "&openid=" + openid
					     + "&out_trade_no=" + out_trade_no
					     + "&total_fee=" + Integer.toString(ze)
					     + "&trade_type=" + "JSAPI";
            
            String signTemp = tempA + "&key=" + secretid;
            
            sign = string2MD5(signTemp).toUpperCase();
            
            String params = "<xml><appid>" + appid + "</appid>"
					  + "<mch_id>" + mch_id + "</mch_id>"
					  + "<device_info>WEB</device_info>"
					  + "<nonce_str>" + nonce_str + "</nonce_str>"
					  + "<body>" + body + "</body>"
					  + "<notify_url>http://www.weixin.qq.com/wxpay/pay.php</notify_url>"
					  + "<out_trade_no>" + out_trade_no + "</out_trade_no>"
					  + "<fee_type>CNY</fee_type>"
					  + "<total_fee>" + Integer.toString(ze) + "</total_fee>"
					  + "<trade_type>JSAPI</trade_type>"
					  + "<openid>" + openid + "</openid>"
					  + "<sign>" + sign + "</sign></xml>";
            
        	
        	
        	
            byte[] requestBytes = params.getBytes("utf-8");
            HttpClient httpClient = new HttpClient();// 客户端实例化
            PostMethod postMethod = new PostMethod(getOpenIdUrl);
            // 设置请求头  Content-Type
            postMethod.setRequestHeader("Content-Type", "application/json");
            
            InputStream inputStream = new ByteArrayInputStream(requestBytes, 0,
                    requestBytes.length);
            RequestEntity requestEntity = new InputStreamRequestEntity(inputStream,
                    requestBytes.length, "application/json; charset=utf-8"); // 请求体
            postMethod.setRequestEntity(requestEntity);
            
            httpClient.executeMethod(postMethod);// 执行请求
            InputStream soapResponseStream = postMethod.getResponseBodyAsStream();// 获取返回的流
            byte[] datas = null;
            try {
                datas = readInputStream(soapResponseStream);// 从输入流中读取数据
            } catch (Exception e) {
                e.printStackTrace();
            }
            result = new String(datas, "UTF-8");
            System.out.println(result);
             Map remp=XmlConverUtil.xml2Map(result); 	
           if(null!=remp){
        	   prepay_id=  remp.get("prepay_id").toString();
           } 
         
           	String tempB = "appId="+ appid 
			+ "&nonceStr=" + nonce_str
			+ "&package=prepay_id="+prepay_id	
			+ "&signType=MD5"
			+ "&timeStamp=" + (int) (System.currentTimeMillis() / 1000);
	
	String signB = tempB + "&key=" + secretid;
	System.out.println("nonce_str--->"+nonce_str);
	System.out.println("signB---->"+signB);
	sign = string2MD5(signB).toUpperCase();
    remp.put("sign", sign);
    remp.put("nonce_str", nonce_str);
    remp.put("timeStamp", (int) (System.currentTimeMillis() / 1000));
	JSONObject resultObj = JSONObject.fromObject(remp);
	out.println(resultObj.toString());
	System.out.println(resultObj.toString());
	conn.close();
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        finally{
            try{
            	conn.close();
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}
	
	public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }
	
	public static String string2MD5(String inStr){
		MessageDigest md5 = null;
		try{
			md5 = MessageDigest.getInstance("MD5");
		}catch (Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++){
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}


public static void main(String[] args) {
	System.out.println( (int) (System.currentTimeMillis() ));
}
}
