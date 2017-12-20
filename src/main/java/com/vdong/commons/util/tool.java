package com.vdong.commons.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *   工具类
 * @author lk
 * data 2015-11-28
 */
public class tool {

/**
 * 
 *Description:  根据身份证号码  来判断性别 
 * @param sfzhm 身份证号码
 * @return 
 * data: 2015-11-28
 * return  String  0--男 ， 1--女
 */
public  static  String getSex(String value){
	value = value.trim();
		if (value == null || (value.length() != 15 && value.length() != 18)) {
			return "";
		}
		if (value.length() == 15 || value.length() == 18) {
		//	String lastValue = value.substring(value.length() - 1, value.length());
			String last2Value = value.substring(value.length() - 2, value.length()-1);
		//	System.out.println(last2Value);
			int sex;
				sex = Integer.parseInt(last2Value) % 2;
				return sex == 0 ? "1" : "0";
		} else {
			return "";
		}
}

  /** 
  * 产生一个32位的GUID 
  * @return 
  */ 
  public static String newGUID() 
  { 
  UUID uuid = UUID.randomUUID(); 
  return uuid.toString().replace("-", ""); 
  } 


  /** 
       * 获取32位GUID 
       * 
       * @return 
       */ 
      public static String getId() { 
          try { 
              MessageDigest md = MessageDigest.getInstance("MD5"); 
              UUID uuid = UUID.randomUUID(); 
              String guidStr = uuid.toString(); 
              md.update(guidStr.getBytes(), 0, guidStr.length()); 
              return new BigInteger(1, md.digest()).toString(16); 
          } catch (NoSuchAlgorithmException e) { 
              return null; 
          } 
      }
      
      
   /*   public static void main(String[] args) {
    	//  System.out.println(newGUID()+"  @@"+newGUID().length());
    	  function();
	}
      */
     /** 
      * 
      *Description:  根据当前时间计算是一年中的第几天
      * @return 
      * data: 2015-11-29
      * return  int
      */
      public  static int orderDate( Date date){
    	  int dateSum = 0;
    	//  Date date =new Date();
    	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	  String dateStr = format.format(date);
    	  System.out.println(dateStr);
    	  int year = Integer.valueOf(dateStr.substring(0,4));
    	  int month = Integer.valueOf(dateStr.substring(5,7));
    	  int day = Integer.valueOf(dateStr.substring(8,10));
    	 
    	  for (int i = 1; i < month; i++){
    	   switch(i){
    	     case 1: case 3: case 5: case 7: case 8: case 10: case 12:dateSum += 31; break;
    	     case 4: case 6: case 9: case 11:dateSum += 30; break;
    	     case 2: 
    	      if(((year % 4 == 0) & (year % 100 != 0)) | (year % 400 == 0))
    	       dateSum += 29;
    	      else dateSum += 28;    
    	   }
    	  }
    	  dateSum = dateSum + day;
    	  return dateSum;
    	 }

	public static int function() {
		int n = 0;
		n = (int) (Math.random() * 1000);
		System.out.println("N==>"+n);
		/*while (n < 100 || !handle(n)) {
			n = (int) (Math.random() * 1000);
		}*/
     return n;
	}

	public static boolean handle(int n) {
		int[] list = new int[5];
		for (int i = 0; i < 5; i++) {
			list[i] = n % 10;
			n = n / 10;
		}
		for (int i = 0; i < 5; i++) {
			for (int j = i + 1; j < 5; j++) {
				if (list[i] == list[j])
					return false;
			}
		}
		return true;
	}
	     
	    public static String dateTrans(String date, String oldFormat, 
	            String newFormat, int index) {
	        String result = null;
	        try {
	            SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);
	            ParsePosition pos = new ParsePosition(index);
	            Date d = sdf.parse(date, pos);
	            sdf = new SimpleDateFormat(newFormat);
	            result = sdf.format(d);
	        } catch (Exception err) {
	            System.out.println("Trans Error: "+ err.getMessage());
	        }
	        return result;
	    }
	    /**
		 * 
		 *  这是mysql语句的封装
		 * @param sql1
		 * @param start
		 * @param end
		 * @return
		String
		 */
		public static String paginationFormysql(String sql1, int start, int end) {
			String sql = " SELECT pa.*   from (" + sql1 + ") pa   limit  "+start+","+end;
			return sql;
		}
		/**
		 * 
		 *Description:   logo图片上传
		 * @param request
		 * @param response
		 * @param realPath
		 * @param myfiles
		 * @return 
		 * data: 2015-12-11
		 * return  Map<String,String>
		 */
		public static Map<String, String> uploadExcel(HttpServletRequest request,HttpServletResponse response,String realPath,MultipartFile myfiles) {
			Map<String, String> remap = new HashMap<String, String>();
			Date data = new Date();
			SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddhhmmss");
			format2.format(data);
			String originalFilename = null;
			if (myfiles.isEmpty()) {
				remap.put("flag", "false");
				remap.put("msg", "上传失败,请选择文件后上传");
			} else {
				originalFilename = myfiles.getOriginalFilename();
				System.out.println("文件原名: " + originalFilename);
				String filename = format2.format(data) + ".jpg ";// 对文件进行重命名
				try {
					FileUtils.copyInputStreamToFile(myfiles.getInputStream(),
							new File(realPath, filename));
					remap.put("flag", "true");
					remap.put("msg", "上传成功！");
					System.out.println("文件名+文件路径为" + realPath + "\\" + filename);
					 /*try{
						 FileUtils.copyInputStreamToFile(myfiles.getInputStream(),
									new File(Constants.BFLOGOPATH, filename)); 
					 }catch (Exception e) {
						 System.out.println("文件备份名称为：" + Constants.BFLOGOPATH + "  路径不存在或者已经出现问题 !");
					 }*/
					remap.put("realPath", realPath + "\\" + filename);
					remap.put("filename", filename);
				} catch (IOException e) {
					System.out.println("文件[" + originalFilename + "]上传失败,堆栈轨迹如下");
					e.printStackTrace();
					remap.put("flag", "false");
					remap.put("msg", "文件上传失败，请重试！！！");
				}
			}
			return remap;
		}
		
		
		public static void main(String[] args) {
		/*	String appid = "872"
				+ (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(
						new Date()).substring(2, 4)
				+ String.valueOf(tool.orderDate(new Date()))
				+ String.valueOf(tool.function());
			  String str = String.format("%03d", 1);  
				String todaynum= String.format("%03d",tool.orderDate(new Date()));
			System.out.println(function());*/
			
			
			
		System.out.println(StringEscapeUtils.escapeJava("1、可以通过生成兑换码，将度假币转赠给亲友使用。2、所有度假币（包含赠送给亲友的度假币）会在今年12月31日之后自动清零。3、『业主专享』频道上上架的第三方合作商换住酒店仅限业主使用，后期酒店数量会随平台的发展而逐渐增加。4、每个业主账号每年在『业主专享』频道的消费额度限制为1000度假币，超出部分将无法预订成功。"));	
		
		System.out.println(StringEscapeUtils.unescapeJava("1\u3001\u53EF\u4EE5\u901A\u8FC7\u751F\u6210\u5151\u6362\u7801\uFF0C\u5C06\u5EA6\u5047\u5E01\u8F6C\u8D60\u7ED9\u4EB2\u53CB\u4F7F\u7528\u30022\u3001\u6240\u6709\u5EA6\u5047\u5E01\uFF08\u5305\u542B\u8D60\u9001\u7ED9\u4EB2\u53CB\u7684\u5EA6\u5047\u5E01\uFF09\u4F1A\u5728\u4ECA\u5E7412\u670831\u65E5\u4E4B\u540E\u81EA\u52A8\u6E05\u96F6\u30023\u3001\u300E\u4E1A\u4E3B\u4E13\u4EAB\u300F\u9891\u9053\u4E0A\u4E0A\u67B6\u7684\u7B2C\u4E09\u65B9\u5408\u4F5C\u5546\u6362\u4F4F\u9152\u5E97\u4EC5\u9650\u4E1A\u4E3B\u4F7F\u7528\uFF0C\u540E\u671F\u9152\u5E97\u6570\u91CF\u4F1A\u968F\u5E73\u53F0\u7684\u53D1\u5C55\u800C\u9010\u6E10\u589E\u52A0\u30024\u3001\u6BCF\u4E2A\u4E1A\u4E3B\u8D26\u53F7\u6BCF\u5E74\u5728\u300E\u4E1A\u4E3B\u4E13\u4EAB\u300F\u9891\u9053\u7684\u6D88\u8D39\u989D\u5EA6\u9650\u5236\u4E3A1000\u5EA6\u5047\u5E01\uFF0C\u8D85\u51FA\u90E8\u5206\u5C06\u65E0\u6CD5\u9884\u8BA2\u6210\u529F\u3002"));
		
		}
		

}
