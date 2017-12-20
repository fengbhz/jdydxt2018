package com.vdong.commons.dao;

import com.vdong.commons.bean.PriceManager;

import java.util.List;

public interface ProductDao {
	/**
	 * 插入价格批量修改信息
	 * @param p
	 * @return
	 */
	public int insertEntrty(PriceManager p);
	
	/**
	 * 获取当前房型的房价信息
	 * @param rooId
	 * @param detailtime
	 * @return
	 */
	public List getJList(String rooId, String detailtime);

	/**
	 *   根据天数获取当前的数据
	 * @param rooId
	 * @param detailtime
	 * @return
	 */
	public List getJListByday(String rooId, String detailtime);
 

}
