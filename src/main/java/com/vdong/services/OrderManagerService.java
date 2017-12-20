package com.vdong.services;

import com.vdong.commons.bean.LoginUser;

import java.util.List;
import java.util.Map;

public interface OrderManagerService {

	/**
	 * 登陆管理
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public int checkLogin(String username, String password);

	public List getUserMsg(String username);

	/**
	 * 订单管理
	 * 
	 * @param phone
	 * @return
	 */
	public boolean updateOrder(String id);

	/**
	 * 订单增加
	 */

	public boolean insertOrder(Map<String, String> map);

	/**
	 * 订单退款（后台操作使用）
	 */

	public Map<String, String> refundOrder(String id, String repayNum);

	/**
	 * 对现有订单的状态进行操作
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	public Map<String, String> updateOrderStatus(String id, String status,
                                                 LoginUser user);

	/**
	 * 根据id来获取订单的详细信息
	 * 
	 * @param id
	 * @return
	 */
	public List getOrderDetail(String id);

	/**
	 * 根据状态对订单进行判断，修改（退款）
	 * @param remap
	 */
	public void updateOrderByStatus(Map<String, String> remap);

	/**
	 * 获取短信内容
	 * @param orderstatus
	 * @param type
	 * @return
	 */
	public String getMsg(String orderstatus, String type) ;
	
}
