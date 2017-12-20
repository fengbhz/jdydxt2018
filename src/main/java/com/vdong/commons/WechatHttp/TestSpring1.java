package com.vdong.commons.WechatHttp;


public class TestSpring1 {/*

	@Autowired
	private WeipayClient weipayClient;
	

	@Test
	public void testCreaseSystemUser()throws Exception {
		String outtradeno=StrCharUtil.uuid();
		System.out.println(outtradeno);
		UnifiedOrderRequest unifiedOrderRequest=new UnifiedOrderRequest();
		unifiedOrderRequest.setAppid("wxc3d857095b050e9e");
		unifiedOrderRequest.setMchId("1230895902");
		unifiedOrderRequest.setNonceStr(StrCharUtil.uuid());
		unifiedOrderRequest.setAttach("attach");
		unifiedOrderRequest.setBody("充值0.01元");
		unifiedOrderRequest.setNotifyUrl("http://dev.nbvdong.com/agent-web/callback/wechat");
		unifiedOrderRequest.setSpbillCreateIp("127.0.0.1");
		unifiedOrderRequest.setTotalFee(1);
		unifiedOrderRequest.setTradeType("NATIVE");
		unifiedOrderRequest.setDeviceInfo("WEB");
		unifiedOrderRequest.setOutTradeNo(outtradeno);
		unifiedOrderRequest.setSign(SignatureUtil.createRequestSignature(unifiedOrderRequest, "yx9HHwuHM7s3iNUn4M3gd7e9EXND677s"));
		
		System.out.println(unifiedOrderRequest.toXML());
		UnifiedOrderResponse unifiedOrderResponse=weipayClient.unifiedOrderForResponse(unifiedOrderRequest);
		
		System.out.println(unifiedOrderResponse.getReturnMsg());
		System.out.println(unifiedOrderResponse.getResultCode());
		System.out.println(unifiedOrderResponse.getReturnCode());
		System.out.println(unifiedOrderResponse.getCodeUrl());
		System.out.println(unifiedOrderResponse.getPrepayId());
		
		OrderQueryRequest orderQueryRequest=new OrderQueryRequest();
		orderQueryRequest.setAppid("wxc3d857095b050e9e");
		orderQueryRequest.setMchId("1230895902");
		orderQueryRequest.setNonceStr(StrCharUtil.uuid());
		orderQueryRequest.setOutTradeNo("ecbffb2d4151428ca6ed33c736ecd623");
		orderQueryRequest.setSign(SignatureUtil.createRequestSignature(orderQueryRequest, "yx9HHwuHM7s3iNUn4M3gd7e9EXND677s"));
		
		OrderQueryResponse orderQueryResponse=weipayClient.orderQueryForResponse(orderQueryRequest);
		System.out.println(orderQueryResponse.toXML());
	}
	
	@Test
	public void testPay()throws Exception {
		UnifiedOrderRequest unifiedOrderRequest=new UnifiedOrderRequest();
		unifiedOrderRequest.setAppid("wxdb3ebfe558cd0653");
		unifiedOrderRequest.setMchId("1427830602");
		unifiedOrderRequest.setNonceStr(StrCharUtil.uuid());
		unifiedOrderRequest.setAttach("attach");
		unifiedOrderRequest.setBody("充值0.01元");
		unifiedOrderRequest.setNotifyUrl("http://dev.nbvdong.com/agent-web/callback/wechat");
		unifiedOrderRequest.setSpbillCreateIp("112.16.158.102");
		unifiedOrderRequest.setTotalFee(1);
		unifiedOrderRequest.setTradeType("JSAPI");
		unifiedOrderRequest.setDeviceInfo("WEB");
		unifiedOrderRequest.setOutTradeNo("123213");
		unifiedOrderRequest.setOpenid("");
		unifiedOrderRequest.setSign(SignatureUtil.createRequestSignature(unifiedOrderRequest, "yx9HHwuHM7s3iNUn4M3gd7e9EXND677s"));
		UnifiedOrderResponse unifiedOrderResponse=weipayClient.unifiedOrderForResponse(unifiedOrderRequest);
		
		System.out.println(unifiedOrderResponse.getReturnMsg());
		System.out.println(unifiedOrderResponse.getResultCode());
		System.out.println(unifiedOrderResponse.getReturnCode());
		System.out.println(unifiedOrderResponse.getCodeUrl());
		System.out.println(unifiedOrderResponse.getPrepayId());
	}
	
	
	@Test
	public void test1()throws Exception {
		

		WeipayClient weipayClient=new WeipayClient(new RestTemplate());
		
		UnifiedOrderRequest unifiedOrderRequest=new UnifiedOrderRequest();
		unifiedOrderRequest.setAppid("wx5fed04a1e6286a40");
		unifiedOrderRequest.setMchId("1445131502");
		unifiedOrderRequest.setNonceStr(StrCharUtil.uuid());
		unifiedOrderRequest.setAttach("attach");
		unifiedOrderRequest.setBody("充值0.01元");
		unifiedOrderRequest.setNotifyUrl("http://dev.nbvdong.com/agent-web/callback/wechat");
		unifiedOrderRequest.setSpbillCreateIp("112.16.158.102");
		unifiedOrderRequest.setTotalFee(1);
		unifiedOrderRequest.setTradeType("JSAPI");
		unifiedOrderRequest.setDeviceInfo("WEB");
		unifiedOrderRequest.setOutTradeNo("aaaa0001010101");
		unifiedOrderRequest.setOpenid("oAS7q0NhymO7EiMPl0QmOkPpW9_M");
		unifiedOrderRequest.setSign(SignatureUtil.createRequestSignature(unifiedOrderRequest, "941dc9c75cd6dc21b39133804e86521f"));
		UnifiedOrderResponse unifiedOrderResponse=weipayClient.unifiedOrderForResponse(unifiedOrderRequest);
		
		System.out.println(unifiedOrderResponse.getReturnMsg());
		System.out.println(unifiedOrderResponse.getResultCode());
		System.out.println(unifiedOrderResponse.getReturnCode());
		System.out.println(unifiedOrderResponse.getCodeUrl());
		System.out.println(unifiedOrderResponse.getPrepayId());
	}
	
	@Test
	public void testRefund()throws Exception {
		
		RefundRequest refundRequest=new RefundRequest();
		refundRequest.setAppid("wxc3d857095b050e9e");
		refundRequest.setMchId("1230895902");
		refundRequest.setNonceStr(StrCharUtil.uuid());
		//refundRequest.setOutTradeNo("ecbffb2d4151428ca6ed33c736ecd623");
		refundRequest.setOutRefundNo(StrCharUtil.uuid());
		refundRequest.setTotalFee(1);
		refundRequest.setRefundFee(1);
		refundRequest.setSign(SignatureUtil.createRequestSignature(refundRequest, "yx9HHwuHM7s3iNUn4M3gd7e9EXND677s"));
		RefundResponse refundResponse=weipayClient.refundForResponse(refundRequest);
		System.out.println(refundResponse.getReturnMsg());
		System.out.println(refundResponse.toXML());
//		unifiedOrderRequest.setMchId("");
		
	}

*/}
