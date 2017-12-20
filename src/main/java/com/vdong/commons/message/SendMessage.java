package com.vdong.commons.message;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.HashMap;
import java.util.Map;

public class SendMessage {
	  //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";
    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId ="LTAIraEnIMY0AXig";
    static final String accessKeySecret = "7PLb5FBztENvRoSTzUrOenP2IpTdFg";
     // 必填:短信签名-可在短信控制台中找到
    static final String sign = "有个度假屋";
	public static String sendMsg(Map<String, String> map) {
		// 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为20个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		String phone=map.get("phone");
		// 必填:短信模板-可在短信控制台中找到
		String moldId=map.get("moldId");
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		String Json=map.get("json");
		
		// 设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		// 初始化ascClient需要的几个参数
		// 替换成你的AK
		// 初始化ascClient,暂时不支持多region
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
				accessKeyId, accessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product,
					domain);
		} catch (ClientException e1) {
			e1.printStackTrace();
		}
		IAcsClient acsClient = new DefaultAcsClient(profile);
		// 组装请求对象
		SendSmsRequest request = new SendSmsRequest();
		request.setPhoneNumbers(phone);
		request.setSignName(sign);
		request.setTemplateCode(moldId);
		request.setTemplateParam(Json);
		// 可选-上行短信扩展码(无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");
		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("yourOutId");
		// 请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse;
		try {
			sendSmsResponse = acsClient.getAcsResponse(request);
			if (sendSmsResponse.getCode() != null
					&& sendSmsResponse.getCode().equals("OK")) {
				// 请求成功
				System.out.println("短信发送结果！---> 发送成功");
			}
			System.out.println("短信发送结果！--->"+sendSmsResponse.getMessage());
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		Map<String, String> map=new HashMap<String, String>();
		  map.put("phone", "18516601569");
		  map.put("moldId", "SMS_78655049");
		  map.put("json", "{\"tiem\":\"【爱度假】\", \"code\":\"1234\"}");
		  sendMsg(map);
		
		
	}
	
}
