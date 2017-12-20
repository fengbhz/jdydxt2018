package com.vdong.services;

import com.vdong.commons.bean.PriceManager;

import java.util.List;
import java.util.Map;

/**
 * 酒店管理接口
 * 
 * @author Administrator
 * 
 */
public interface HotelManagerService {

	/**
	 * 酒店信息添加
	 * 
	 * @param map
	 * @return
	 */
	public int insertHotel(Map<String, String> map);

	/**
	 * 根据条件获取房型的主要内容
	 * 
	 * @param map
	 * @return
	 */
	public List getRoomList(Map<String, String> map);

	/**
	 * 插入批量设置价格的内容
	 * @param priceManager
	 * @return
	 */
	public int insertProduct(PriceManager priceManager);
	
	/**
	 * 获取房价信息
	 * @param roodId
	 * @param detailtime
	 * @return
	 */
	public List getProductList(String roodId, String detailtime);

	/**
	 * 获取当前时间60天内的房价数据
	 * @param roodId
	 * @param detailtime
	 * @return
	 */
	public List getProductListForDays(String roodId, String detailtime);

}
