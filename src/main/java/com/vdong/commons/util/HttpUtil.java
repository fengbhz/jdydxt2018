package com.vdong.commons.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * 微信http请求工具类
 * 
 * @author lk data 2016-1-5
 */
public class HttpUtil {
	
	/**
	 * 
	 *Description: 发送http post 请求，并返回结果
	 * 
	 * @param url
	 *            链接地址
	 * @param str
	 *            json参数
	 * @return data: 2016-1-5 return String
	 */
	public static String httpUtil(String url, String str) {
		/*
		 * String url =
		 * "http://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + code;
		 */
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);
		post.setRequestBody(str);
		post.getParams().setContentCharset("utf-8");
		String respStr = "";
		try {
			client.executeMethod(post);
			respStr = post.getResponseBodyAsString();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return respStr;
	}
	
	
	
	
	
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, Object> paramsDatas = new LinkedMultiValueMap<String, Object>();
		paramsDatas.set("username", "13975289687");
		paramsDatas.set("password", "890326");
		paramsDatas.set("appid", "wxf0deebd5e17a6723");
		paramsDatas.set("appsecret", "c25409622a0870559f36dc8f956ade70");
		paramsDatas.set("days", "365");
		paramsDatas.set("ptype", "5");  //  mouldid
		paramsDatas.set("groupid", "7");  // bggid
		HttpEntity<MultiValueMap<String, Object>> mapRequest = new HttpEntity<MultiValueMap<String, Object>>(paramsDatas);
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://weixin.vdongchina.com/api/weichen.php?action=synData", mapRequest, String.class);
		JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
		JSONObject jsonData = jsonObject.getJSONObject("data");
		String jsonCode = jsonObject.getString("code");
		String jsonMsg = jsonObject.getString("msg");
		System.out.println("jsonData---->"+jsonData+"   jsonCode---->"+jsonCode+"  :-->"+jsonMsg);
		
		
		
	  
	
	
	
	
	}
	
	
	
	
	
	/**
	 * 
	 * Description: 通过网络请求参数
	 * 
	 * @param url
	 * @return data: 2016-1-6 return String
	 */
	public static String loadJSON(String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL oracle = new URL(url);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream(), "utf-8"));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json.toString();
	}
/*
   public static void main(String[] args) {
	   String str="{\"filter\":{ \"is_to_all\":\"true\",  \"group_id\":\"2\"   },\"mpnews\":{\"media_id\":\"SYMg2APwQFAxga9o572ye03jzz_DnprnAbGmqZQGDt8ZGsj6lXzMOnjWxC0iXhr7\"},\"msgtype\":\"mpnews\"}";
       System.out.println(str);
	   String sr=   HttpUtil.loadJSON(" http://apis.map.qq.com/ws/geocoder/v1/?address=宁波市&key=TEUBZ-EZKH5-IX5IV-QXUHS-MUD63-3KBKA");
//	String sr=   HttpUtil.httpUtil("https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=cIDtZHAknFsw6p6zwfFl0P9Jes_NnR6usVDJd4Ts5zodqoYLR7xQY3j1x-WmiZvm30hsZPnqyzX3FS3OyWBGubgn3eZ-ptmV_XWS73Nb3kcHCIdAEAVQW",str);
	
	 //System.out.println(sr);
	 JSONObject jsonObject=JSONObject.fromObject(sr);
	 JSONObject json=JSONObject.fromObject(jsonObject.get("result"));
	 System.out.println(json.get("location"));   
	   
	   
	   
}*/


}
