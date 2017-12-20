package com.vdong.commons.bean;
/**
 * 用户信息实体类
 * @author lk
 * data 2015-11-27
 */
public class userbean {
	/**
	 * 主键ID
	 */
    private int id;  
    /**
	 * 申请书编号
	 */
    private String appid;
    /**时间戳
	 */
    private String timestamp;
    /**
	 * 中文姓名
	 */
    private String zwm;
    /**
	 * 电话号码
	 */
    private  String phone;
    /**
	 * 卡种
	 */
    private String kz;
    /**
	 * 添加时间
	 */
    private String addtime ;
    /**
	 * 证件有效期
	 */
    private String zjyxq;
    /**
	 * 证件有效标志
	 */
    private String zjyxbz;
    /**
	 * 引荐人代号---二维码参数携带
	 */
    private String yjrdh;
    /**
	 * 引荐单位机构代码-----二维码参数传递
	 */
    private String yjjgdm;
    /**
	 * 发卡机构
	 */
    private String authorg;
    /**
	 * 辅助1
	 */
    private String fuzu1;
    /**
	 * 辅助2
	 */
    private String fuzu2;
    /**
	 * 辅助3
	 */
    private String fuzu3;
    
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getZwm() {
		return zwm;
	}
	public void setZwm(String zwm) {
		this.zwm = zwm;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getKz() {
		return kz;
	}
	public void setKz(String kz) {
		this.kz = kz;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getZjyxq() {
		return zjyxq;
	}
	public void setZjyxq(String zjyxq) {
		this.zjyxq = zjyxq;
	}
	public String getZjyxbz() {
		return zjyxbz;
	}
	public void setZjyxbz(String zjyxbz) {
		this.zjyxbz = zjyxbz;
	}
	public String getYjrdh() {
		return yjrdh;
	}
	public void setYjrdh(String yjrdh) {
		this.yjrdh = yjrdh;
	}
	public String getYjjgdm() {
		return yjjgdm;
	}
	public void setYjjgdm(String yjjgdm) {
		this.yjjgdm = yjjgdm;
	}
	public String getAuthorg() {
		return authorg;
	}
	public void setAuthorg(String authorg) {
		this.authorg = authorg;
	}
	public String getFuzu1() {
		return fuzu1;
	}
	public void setFuzu1(String fuzu1) {
		this.fuzu1 = fuzu1;
	}
	public String getFuzu2() {
		return fuzu2;
	}
	public void setFuzu2(String fuzu2) {
		this.fuzu2 = fuzu2;
	}
	public String getFuzu3() {
		return fuzu3;
	}
	public void setFuzu3(String fuzu3) {
		this.fuzu3 = fuzu3;
	}
	
	
	
	
}
