package com.vdong.commons.bean;

import java.io.Serializable;

public class Msg implements Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String bundle = null;

	protected String key = null;

	protected String name = null;

	protected boolean resource = true;
	
	private Object data;

	public String getBundle() {
		return bundle;
	}

	public void setBundle(String bundle) {
		this.bundle = bundle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isResource() {
		return resource;
	}

	public void setResource(boolean resource) {
		this.resource = resource;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e.toString());
		}
	}

	public String toString() {
		StringBuffer results = new StringBuffer();

		results.append("Msg: name=");
		results.append(name);
		results.append("  key=");
		results.append(key);
		results.append("  resource=");
		results.append(resource);
		results.append("  bundle=");
		results.append(bundle);
		results.append("\n");

		return results.toString();
	}

}
