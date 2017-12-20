package com.vdong.commons.dao;

import java.util.List;
import java.util.Map;

public interface UserManagerDao {
	/**
	 * 根据id主键来删除用户
	 * 
	 * @param id
	 * @return
	 */
	public int deletedUser(String id);

	/**
	 * 创建用户
	 * 
	 * @param map
	 * @return
	 */
	public int insertUser(Map<String, String> map);

	/**
	 * 根据账号和电话号码对用户进行判断
	 * 
	 * @param account
	 * @param phone
	 * @return
	 */
	public String checkUser(String account, String phone);

	/**
	 * 根据ID 来查找业主信息
	 * 
	 * @param id
	 * @return
	 */
	public List getOwer(String id);

	/**
	 * G根据ID来更新业主的审核状态
	 * 
	 * @param id
	 * @return
	 */
	public int updateOwerStatus(String id, String status);

	/**
	 * 插入业主的个人信息
	 * 
	 * @param map
	 * @return
	 */
	public int insertUserforOwer(Map<String, String> map);

	/**
	 * 更新业主的个人信息
	 * 
	 * @param map
	 * @return
	 */
	public int updateUserforOwer(Map<String, String> map);

	/**
	 * 插入有人住
	 * 
	 * @param map
	 * @return
	 */
	public int insertOwner(Map<String, String> map);

	/**
	 * 更新业主owner表
	 * 
	 * @param map
	 * @return
	 */
	public int updateOwer(Map<String, String> map);

	/**
	 * 根据账号来获取用户的状态
	 * 
	 * @param account
	 * @return
	 */
	public List getStatus(String account);

	/**
	 * 添加权益内容
	 * 
	 * @param id
	 * @param qy
	 * @return
	 */
	public int updateUserQy(String id, String qy);

}
