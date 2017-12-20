package com.vdong.services;

import java.util.List;
import java.util.Map;

/**
 * 消息管理
 * 
 * @author Administrator
 * 
 */
public interface HotelMsgService {
	/**
	 * 信息存储
	 * 
	 * @param map
	 * @return
	 */
	public int insertMsg(Map<String, String> map);
	
	/**
	 * 对发现进行更新
	 * 
	 * @param map
	 * @return
	 */
	public int updateMsg(Map<String, String> map);

	/**
	 * 根据ID来更新消息是否读的状态
	 * 
	 * @param id
	 * @param readStatus
	 * @return
	 */
	public int updateStatus(String id, String readStatus);

	/**
	 * 根据类型来获取消息的内容
	 * 
	 * @param type
	 * @param str
	 * @return
	 */
	public List getMsg(String type, String str);

	/**
	 * 对发现进行更新
	 * 
	 * @param map
	 * @return
	 */
	public int updateAd(Map<String, String> map);
	
	
	
	/**
	 * 对发现进行添加
	 * 
	 * @param map
	 * @return
	 */
	public int insertAd(Map<String, String> map);

	
	

}
