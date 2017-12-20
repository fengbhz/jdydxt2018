package com.vdong.commons.bean;

public class QuickMark {
	private int id;
	private  String yjrdm;
	private String yjrjgdm;
    private String pic;
    private String username;
    private String statue;
	public String getYjrdm() {
		return yjrdm;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setYjrdm(String yjrdm) {
		this.yjrdm = yjrdm;
	}
	public String getYjrjgdm() {
		return yjrjgdm;
	}
	public void setYjrjgdm(String yjrjgdm) {
		this.yjrjgdm = yjrjgdm;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatue() {
		return statue;
	}
	public void setStatue(String statue) {
		this.statue = statue;
	}
    

}
