package com.vdong.commons.bean;

/**
 * 反馈记录表·
 * 
 * @author lk data 2015-11-27
 */
public class fkLog {
     /**
      *   主键ID 
      */
	private int id;
	/**
	 *  申请书编号
	 */
	private String appid;
	/**
	 * 申请提交情况
	 */
	private String resp;
	/**
	 * 合作方流水号
	 */
	private String teflow;
	/**
	 * 时间戳
	 */
	private String timestamp;
	/**
	 * 返回状态
	 */
	private String statue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}

	public String getTeflow() {
		return teflow;
	}

	public void setTeflow(String teflow) {
		this.teflow = teflow;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

}
