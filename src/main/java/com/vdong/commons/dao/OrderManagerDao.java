package com.vdong.commons.dao;

import java.util.List;
import java.util.Map;

public interface OrderManagerDao {
	/**
	 * 登陆管理
	 * @param username
	 * @param password
	 * @return
	 */
	public int checkLogin(String username, String password);
	
	/**
	 * 退款更新状态
	 * @param phone
	 * @return
	 */
	public boolean orderUpdate(String id);

	/**
	 * 根据状态和账号和金额来修改度假币
	 * @param account
	 * @param c
	 * @param type
	 * @return
	 */
	public int orderManager(String account, int c, String type);

/**
 * 根据ID 来获取整个订单的数据
 * @param id
 * @return
 */
	public List getAccountByorderId(String id);


	/**
	 * 订单插入
	 * @param map
	 * @return
	 */
	public int insertOrder(Map<String, String> map);

	/**
	 * 订单状态修改
	 * status  最后修改成的状态
	 */
	public Map<String,String>  updateOrderStatus(String id, String status);

	/**
	 *   根据用户名称获取用户信息
	 * @param username
	 * @return
	 */
	public List getUserMsg(String username);

	/**
	 * 根据id来获取整个订单的详细信息
	 * @param id
	 * @return
	 */
	public List getOrderDetail(String id);

	/**
	 * 修改订单的信息
	 * @param remap
	 */
	public void updateOrderMsg(Map<String, String> remap);

	/**
	 * 根据订单状态和标示获取短信内容
	 * @param orderstatus
	 * @return
	 */
	public String getMsg(String orderstatus, String type);
  	
}
