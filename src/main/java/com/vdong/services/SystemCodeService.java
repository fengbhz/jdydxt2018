package com.vdong.services;

import com.vdong.commons.bean.Attach;

import java.util.List;
import java.util.Map;

/**
 * 系统配置项操作
 * 
 * @author Administrator
 * 
 */
public interface SystemCodeService {

	/**
	 * 根据代码值和代码类别来获取代码项
	 * @param dmlb
	 * @param dmz
	 * @return
	 */
	public List getList(String dmlb, String dmz);
	
	/**
	 * 根据条件查找附件表信息
	 * @param map
	 * @return
	 */
	public List getAttach(Map<String, String> map);
	/**
	 * 存储附件表，并返回主键
	 * @param attach
	 * @return
	 */
	public int saveAttach(Attach attach);

	/**
	 * 更新附件表
	 * @param attach
	 * @return
	 */
	public int updateAttach(Attach attach);
	
	
	/**
	 *   删除附件表
	 * @param id
	 * @return
	 */
	public int deleteAttach(String id);

}
