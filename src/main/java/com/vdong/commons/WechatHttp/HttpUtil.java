package com.vdong.commons.WechatHttp;

import com.vdong.commons.util.MD5Util;
import com.vdong.commons.util.ReadProperties;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 *  微信条用接口分装类
 * @author Administrator
 *
 */
public class HttpUtil {
	
	/**
	 * 微信订单退款结果查询
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static String doGetMethed(String xmlstr,String url,String mchId) throws Exception {
    	 ReadProperties properties = new ReadProperties();
		 properties.getPropertiesBean().getAppid();
		  KeyStore keyStore = KeyStore.getInstance("PKCS12");
		  String returnCode =""; //  返回码
         //读取本机存放的PKCS12证书文件
		  System.out.println("证书Url--->"+properties.getPropertiesBean().getPath());
          FileInputStream instream = new FileInputStream(new File("C://cert/apiclient_cert.p12"));
          try {
          	//指定PKCS12的密码(商户ID)
              keyStore.load(instream, mchId.toCharArray());
          } finally {
              instream.close();
          }

          SSLContext sslcontext = SSLContexts.custom()
                  .loadKeyMaterial(keyStore, mchId.toCharArray()).build();
          SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
                  null,SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
          
          CloseableHttpClient httpclient = HttpClients.custom()
                  .setSSLSocketFactory(sslsf) .build();
          
          HttpPost httppost = new HttpPost(url);  
          StringEntity se = new StringEntity(xmlstr);  
          httppost.setEntity(se);  
          System.out.println("executing request" + httppost.getRequestLine());  
          CloseableHttpResponse responseEntry = httpclient.execute(httppost);  
          try {  
              HttpEntity entity = responseEntry.getEntity();  
              System.out.println(responseEntry.getStatusLine());  
              if (entity != null) {  
                  System.out.println("Response content length: "  + entity.getContentLength());  
                  SAXReader saxReader = new SAXReader();  
                  Document document = saxReader.read(entity.getContent());  
                  Element rootElt = document.getRootElement();  
                  System.out.println("根节点：" + rootElt.getName());  
                      returnCode = rootElt.elementText("return_code");  
                      System.out.println(rootElt.elementText("return_msg"));
                  JSONObject result = new JSONObject();  
                  if(returnCode.equals("SUCCESS")){  
                	   System.out.println("退款成功！");
                  }else{  
                      result.put("status","false");  
                      result.put("msg",rootElt.elementText("err_code_des"));  
                  }  
              }  
              EntityUtils.consume(entity);  
          }catch(Exception  e){
        	  e.printStackTrace();
          } finally {  
              responseEntry.close(); 
              httpclient.close();  
      } 
		return returnCode;
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
	
	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	public  static  String createSign(SortedMap<String, String> packageParams) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8")
				.toUpperCase();
		return sign;

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
	
}
