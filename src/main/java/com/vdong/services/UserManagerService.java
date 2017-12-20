package com.vdong.services;

import java.util.List;
import java.util.Map;

public interface UserManagerService {

	/**
	 * 根据ID来删除用户
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteUser(String id);

	/**
	 * 根据账号跟电话对用户进行添加判断
	 * @param account
	 * @param phone
	 * @return
	 */
	public String addUser(String account, String phone);


	/**
	 * 用户初始化（前端用户登陆进入初始化）
	 * @param map
	 * @return
	 */
	public int userInit(Map<String, String> map);

	/**
	 * 插入用户信息（后台）
	 * @param map
	 * @return
	 */
	public int insertUserMsg(Map<String, String> map);

	public int updateUserforOwer(Map map);
	/**
	 * 业主权益添加修改
	 * @param id
	 * @param qy
	 * @return
	 */
	public int updateUserQy(String id, String qy);
	
	/**
	 *  根据账号获取业主信息
	 * @param account
	 * @return
	 */
	public List getOwner(String account);
	
	
}
