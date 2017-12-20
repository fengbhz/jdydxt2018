package com.vdong.services.impl;

import com.vdong.commons.dao.UserManagerDao;
import com.vdong.services.UserManagerService;
import com.vdong.commons.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userManagerService")
public class UserManagerServiceImpl implements UserManagerService {

	@Autowired
	private UserManagerDao userManagerDao;

	public boolean deleteUser(String id) {
		return userManagerDao.deletedUser(id)>0?true:false;
	}

	public String addUser(String account, String phone)
	{
		return userManagerDao.checkUser(account,phone);
	}

	@Override
	public int userInit(Map<String, String> map) {
		String code = (map.get("code")==null?"":map.get("code"));
		String appid = "wx381d504d76083eb7";
		String secretid = "e2f8f79b73cac46d1e20210056c618b1";
		String openid = "";
		String getOpenIdUrl = "https://api.weixin.qq.com/sns/jscode2session?appid="
				+ appid
				+ "&secret="
				+ secretid
				+ "&js_code="
				+ code
				+ "&grant_type=authorization_code";
		
		String result = HttpUtil.loadJSON(getOpenIdUrl);
		
		openid = result.split("openid\":\"")[1].split("\"}")[0];
		
		map.put("code", openid);
		//  首先根据account进行判断是否已经存在，如果存在状态是待激活 就直接绑定为业主，其他的正常插入
		List list=	userManagerDao.getStatus(map.get("mobile"));
		  if( null!=list&&list.size()>0){
			  Map<String,String>  remap=(Map<String, String>) list.get(0);
			    if(remap.get("applyStatus").equals("2")){ //  如果是待激活，则直接激活
			    	userManagerDao.updateOwerStatus(remap.get("id"), "3");	
			    	map.put("uid", remap.get("id"));   // 双表同ID
			    	map.put("userKind", "1");
			    	userManagerDao.updateUserforOwer(map);
			    }else{//进行更新
			    	map.put("uid", remap.get("id"));
			    	userManagerDao.updateUserforOwer(map);
			    }
		  }else{//重来都没有登录过的
			  userManagerDao.insertUserforOwer(map);
		  }
		  return 0;
	}

	
	public int insertUserMsg(Map<String, String> map) {
		int count1 = userManagerDao.insertUserforOwer(map);
		int count2 = userManagerDao.insertOwner(map);
		if (count1 == 0 || count2 == 0) {
			return 0;
		}
		return 1;
	}

	@Override
	public int updateUserforOwer(Map map) {
		int count1 = userManagerDao.updateOwer(map);
		int count2 = userManagerDao.updateUserforOwer(map);
		if (count1 == 0 || count2 == 0) {
			return 0;
		}
		return 1;
	}

	@Override
	public int updateUserQy(String id, String qy) {
		return	userManagerDao.updateUserQy(id,qy);
	}

	@Override
	public List getOwner(String account) {
		return	userManagerDao.getStatus(account);
	}
	
	

}
