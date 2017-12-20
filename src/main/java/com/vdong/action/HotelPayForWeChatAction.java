package com.vdong.action;

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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("serial")
public class HotelPayForWeChatAction extends HttpServlet {

	public HotelPayForWeChatAction() {
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
	
	@SuppressWarnings({ "rawtypes" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String appid = "wx381d504d76083eb7";
		String secretid = "e2f8f79b73cac46d1e20210056c618b1";
		String mch_id = "1445131502";
		String nonce_str = UUID.randomUUID().toString().substring(0, 32);
		String sign = "16811E3132A045D469427352B7A7D660";
		String openid = "o5EsEwqb7ASxkgEPYSAO-ea-Kjgk";
		String code = (request.getParameter("code")==null?"":request.getParameter("code"));
		//String code = "003PrKHa0Elf0t1leSEa04bCHa0PrKHY";
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
        try {
            URL realUrl = new URL(getOpenIdUrl);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
           
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        finally{
            try{
                if(out!=null){
                    out.close();
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
		
       
		
		
		
		
		
		
		
		Map map = new HashMap();
		map.put("result", result);
		
		JSONObject resultObjs = JSONObject.fromObject(map);
		out.println(resultObjs.toString());
		
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
