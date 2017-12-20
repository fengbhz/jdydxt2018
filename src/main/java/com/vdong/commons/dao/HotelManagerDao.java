package com.vdong.commons.dao;

import java.util.List;
import java.util.Map;

public interface HotelManagerDao {

	/**
	 * 新增酒店信息
	 * @param map
	 * @return
	 */
	public int insertHotel(Map<String, String> map);

	/**
	 * 获取酒店的
	 * @param map
	 */
	public List getRoomList(Map<String, String> map);
	
	
}
