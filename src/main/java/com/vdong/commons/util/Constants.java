package com.vdong.commons.util;

public class Constants {
	  // public static final String URL = "https://adj.vdongchina.com/jdydxt/";// 放到session中的key
	 public static final String URL = "https://wx.aidujia.net/jdydxt/";// 放到session中的key
	
	public static final String BFLOGOPATH="E:/ccbWebPic/logoPic";
	public static final String BFQUICKMARKPATH="E:/ccbWebPic/quickMarkPic";
	
	// 订单状态  
	/**
	 * 一已下单--未付款
	 */
     public static final  String  PAYED="0";        //  一已下单--未付款
     public static final  String  CANCLE_PAY="1";     //  取消
     public static final  String  ALREADY_ORDER="2";   // 已经付款
     public static final  String  WAIT_STAY="3";       // 等待入住
     public static final  String  NON_ROOM="4";       // 无房(需要退款)
     public static final  String  REFUND_PAY="5";      //  退款中
     public static final  String  REFUND_SUCCESS="6";  //  退款成功
     public static final  String  CHECKEDIN_STAY="7";  //  已入住
     public static final  String  EXPIRED_STAY ="8";   //  未住过期
     
     //支付状态
     public static final  String  NON_PAY="0";        //  未支付
     public static final  String ALREADY_PAY="1";     //  已经支付
     public static final  String  RPLACE_PAY="2";     // 代下单
     
     
     // 角色代码
      public static final String PT_MANAGER="PT_MANAGER";//  平台管理员
      public static final String JD_MANAGER="JD_MANAGER";//  酒吧管理员
      
      
      //消息状态-已经读
      public  static final String ALREADY_READ="1";
}


