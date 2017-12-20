package com.vdong.commons.util;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class Tools {

	  public static void getSign(Map map){
	        map.put("appId", "");
	        map.put("timeStamp", (int) (System.currentTimeMillis() / 1000));
	        //随机字符串
	        map.put("nonceStr", "");
	        map.put("package", "prepay_id=");
	        map.put("signType", "MD5");
	        //key 商户密钥 ,根据map重新生成sign
	        String sign = SignatureUtil.createMapSignature(map, "key");
	  }

	public static String getMsg(Map<String, String>  map, String s) {
		String sp=s;
		for(Map.Entry<String, String> entry : map.entrySet()) { 
			if(entry.getKey().equals("checkInDate")||entry.getKey().equals("checkOutDate")||entry.getKey().equals("orderTime")){
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
				String str=sdf.format(entry.getValue());  
				System.out.println(str);
				sp=sp.replace(entry.getKey(), "["+str+"]");
			}else{
				sp=sp.replace(entry.getKey(), "["+entry.getValue()+"]");
			}
		}   
		System.out.println("替换后的字符为-->"+sp);
		return sp;
	}

	
	 public static JSONObject sendMsg(HttpServletResponse response,String url) throws IOException{
		 response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 BufferedReader in = null;
			String result = "";
			try {
				URL realUrl = new URL(url);
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
				System.out.println("发送 POST 请求出现异常！" + e);
				e.printStackTrace();
			}
			// 使用finally块来关闭输出流、输入流
			finally {
				try {
					if (out != null) {
						out.close();
					}
					if (in != null) {
						in.close();
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			System.out.print("result---->"+result);
			 Map map = new HashMap();

			JSONObject resultObj = JSONObject.fromObject(map);
	
	     return resultObj;
     }
	 
	 
	 
	 
}