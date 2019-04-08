package com.uway.common.utils;
 

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropertiesUtils{
	private static ResourceBundle resourceBundle;

	private static final Log log = LogFactory.getLog(PropertiesUtils.class);
	
	static {
		try {
			resourceBundle = ResourceBundle.getBundle("message/config");
		} catch (Exception e) {
			log.error("  Getting config is failed! ",e);
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
	
	public static Integer getIntegerProperty(String key){
		return Integer.parseInt(getProperty(key));
	}
	
	public static Long getLongProperty(String key){
		return Long.parseLong(getProperty(key));
	}
	
	public static String getProperty(String key,Object ... args) {
		String result = getProperty(key);
		if(args != null){
			MessageFormat format = new MessageFormat(result,Locale.getDefault());
			return format.format(args);
		}
		return result;
	}
	
	public static void main(String args[]){
		//System.out.println(getProperty("DBurl"));
		System.out.println(getIntegerProperty("user.sendCash"));
	}
}
