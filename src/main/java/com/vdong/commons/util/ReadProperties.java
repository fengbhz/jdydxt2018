package com.vdong.commons.util;

import com.vdong.commons.bean.PropertiesBean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

private PropertiesBean propertiesbean = new PropertiesBean();
	
	public PropertiesBean getPropertiesBean() {
		return propertiesbean;
	}
	public void setPropertiesBean(PropertiesBean propertiesBean) {
		this.propertiesbean = propertiesBean;
	}
	
	public  PropertiesBean readPropeties(){
		ClassPathResource cr = new ClassPathResource("config.properties");//会重新加载spring框架
        Properties pros = new Properties();
        try {
            pros.load(cr.getInputStream());
            propertiesbean.setUrl(pros.getProperty("Url"));
            propertiesbean.setAppid(pros.getProperty("appid"));
            propertiesbean.setAppSecret(pros.getProperty("secretid"));
            propertiesbean.setMchId(pros.getProperty("mchid"));
            propertiesbean.setSecretId(pros.getProperty("secretid"));
            propertiesbean.setPath(pros.getProperty("path"));
            return propertiesbean;
        } catch (IOException ex) {
        	System.out.println("资源文件不存在");
        }
        return propertiesbean;
	}
}
