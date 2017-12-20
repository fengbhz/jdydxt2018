package com.vdong.services.impl;

import com.vdong.commons.WechatHttp.HttpUtil;
import com.vdong.commons.bean.LoginUser;
import com.vdong.commons.dao.OrderManagerDao;
import com.vdong.services.OrderManagerService;
import com.vdong.commons.util.Constants;
import com.vdong.commons.util.ReadProperties;
import com.vdong.commons.util.tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("orderManagerService")
public class OrderManagerServiceImpl implements OrderManagerService {
	private ReadProperties properties = new ReadProperties();	
	@Autowired
	private OrderManagerDao orderManagerDao;

	public boolean updateOrder(String id) {
		boolean flag = true;
		try {
			// 根据状态进行判断店家是否已经进行了留房处理，否--->直接退款
			List list = orderManagerDao.getAccountByorderId(id);
			if (list != null && list.size() > 0) {
				Map map = (Map) list.get(0);
				String status = String.valueOf(map.get("orderStatus"));
				if (null != status && status.equals(Constants.ALREADY_ORDER)) {// 直接退款
					refundOrder(id, null);
				} else {
					flag = orderManagerDao.orderUpdate(id);
				}
			}

		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public int checkLogin(String username, String password) {

		return orderManagerDao.checkLogin(username, password);
	}
	
	public List getUserMsg(String username) {
		return orderManagerDao.getUserMsg(username);
	}
	
	
	public boolean insertOrder(Map<String, String> map) {
		return (orderManagerDao.insertOrder(map))!=0?true:false;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String,String> refundOrder(String id,String repayNum) {
		List list = orderManagerDao.getAccountByorderId(id);
		Map remap=new HashMap<String,String>();
		if (null != list && list.size() > 0) {
			Map<String, String> map = (Map<String, String>) list.get(0);
			String payWay=map.get("payWay");  //    获取付款方式    支付方式（0前台支付、1微信支付、2度假币支付） 
			//     直接返回，提示管理员直接现金退款
			if(null!=payWay&&payWay.equals("0")){
				remap.put("type", "0");
				remap.put("success", "true");
				//  微信支付，调用微信支付退款接口
			}else if(null!=payWay&&payWay.equals("1")){
				String appid=properties.getPropertiesBean().getAppid();
				appid="wx381d504d76083eb7";
				String secretid=properties.getPropertiesBean().getSecretId();
				secretid="e2f8f79b73cac46d1e20210056c618b1";
				String mchid=properties.getPropertiesBean().getMchId();
				mchid="1483402252";
				   //生成签名
				String mch_id=mchid;
				String nonce_str=tool.newGUID();
				String op_user_id=mchid;
				String out_refund_no=map.get("orderId");
				String out_trade_no=map.get("id");
				String total_fee=map.get("sum");  // 总付款额
				String transaction_id="";
				
				String  total_fe=String.valueOf(new BigDecimal(total_fee).multiply(new BigDecimal("100")).intValue());
				if(null==repayNum||repayNum.equals("")){
					repayNum=total_fe;
				}else{
					repayNum=String.valueOf(new BigDecimal(repayNum).multiply(new BigDecimal("100")).intValue());
				}
				 String tempA = "appid="+ appid 
            		     + "&mch_id=" +mch_id
            		     + "&nonce_str=" + nonce_str
					     + "&op_user_id=" + op_user_id
					     + "&out_refund_no=" + out_refund_no
					     + "&out_trade_no=" + out_trade_no
					     + "&refund_fee=" + repayNum   //退款额
					     + "&total_fee=" +total_fe ; 
            String signTemp = tempA + "&key=" + secretid;
            String sign = HttpUtil.string2MD5(signTemp).toUpperCase();
            System.out.println("退款签名：---->"+sign);
				
         String params = "<xml><appid>" + appid + "</appid>"
					  + "<mch_id>" + mch_id + "</mch_id>"
					  + "<nonce_str>" + nonce_str + "</nonce_str>"
					  + "<op_user_id>" + op_user_id + "</op_user_id>"
					  + "<out_refund_no>"+out_refund_no+"</out_refund_no>"
					  + "<out_trade_no>" + out_trade_no + "</out_trade_no>"
					  + "<refund_fee>"+repayNum+"</refund_fee>"
					  + "<total_fee>" + total_fe + "</total_fee>"
					  + "<transaction_id></transaction_id>"
					  + "<sign>" + sign + "</sign></xml>";
          System.out.println("params----->"+params);
      	 String url="https://api.mch.weixin.qq.com/secapi/pay/refund";
      	     try {
      	      	remap.put("type", "1");
				HttpUtil.doGetMethed(params, url,mch_id);  //    微信退款工具类
				remap.put("success", "true");
			} catch (Exception e) {
				remap.put("success", "false");
				e.printStackTrace();
			}
			}else if(null!=payWay&&payWay.equals("2")){
				if(null==repayNum||repayNum.equals("")){
					repayNum=map.get("sum");
				}
			int count=	orderManagerDao.orderManager(map.get("account"), Integer.valueOf(repayNum), "");
			remap.put("type", "2");
				if(count>0){
					remap.put("success", "true");
				}else{
					remap.put("success", "false");
				}
			}
			  if(null!=remap.get("success")&&remap.get("success").equals("true")){// 判断是否退款成功
				  orderManagerDao.updateOrderStatus(id,Constants.REFUND_SUCCESS); 
			  }
		}
		return remap;
	}

	public Map<String, String> updateOrderStatus(String id, String status,LoginUser user) {
		Map map=new HashMap();
		// 首先进行判断，现有状态能否进行如此操作
		   if(null!=user.getCode()&&user.getCode().equals(Constants.JD_MANAGER)){
			     //  如果是无房状态需要进行退款  
			  if(status.equals(Constants.NON_ROOM)){
				  map=   refundOrder(id, "");
			  }else{
				  map=   orderManagerDao.updateOrderStatus(id,status);
			  }
		   }else  if(null!=user.getCode()&&user.getCode().equals(Constants.PT_MANAGER)){
			     //  申请退款和作废的已支付状态
			     if(status.equals(Constants.REFUND_PAY)){
			        System.out.println("这个走另外的接口！");
			     }
		       map=   orderManagerDao.updateOrderStatus(id,status);
		   }
		return map;
	}

	public List getOrderDetail(String id) {
		return orderManagerDao.getOrderDetail(id);
	}

	
	public void updateOrderByStatus(Map<String, String> remap) {
	   String  statue=remap.get("status");
	   String id=remap.get("id");
	   if(statue.equals(Constants.REFUND_SUCCESS)){ //申请退款
			  refundOrder(id, remap.get("refuNum"));
	   }else  if(statue.equals("-1")){// 修改订单
		   orderManagerDao.updateOrderMsg(remap);
	   }else{
		   orderManagerDao.updateOrderStatus(id,statue);
	   }
	}

	public String getMsg(String orderstatus, String type) {
		return orderManagerDao.getMsg(orderstatus, type);
	}

	public static void main(String[] args) {
		String  total_fe=String.valueOf(new BigDecimal(1).multiply(new BigDecimal("100")).intValue());
		System.out.println(total_fe);
	}
}

