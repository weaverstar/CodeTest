package com.uway.common.utils;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * spring 对象工厂
 * @version 1.0
 */
public class ObjectFactory {
	protected static WebApplicationContext wac = null;
	private static ObjectFactory me = null;

	private ObjectFactory(WebApplicationContext wac) {
		ObjectFactory.wac = wac;
	}

	public static ObjectFactory getInstance(ServletContext servletContext) {
		if (null == me) {
			me = new ObjectFactory(WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext));
		}
		return me;
	}
	
	public static ApplicationContext getApplicationContext(){
		return wac;
	}
	public static ObjectFactory getInstance(WebApplicationContext wac){
		if (null == me) {
			me = new ObjectFactory(wac);
		}
		return me;
	}
	
			
	public static ObjectFactory getInstance() {
		return me;
	}

	public static Object getBean(String objname) {
		return wac.getBean(objname);
	}
	
	public static <T> T getBean(Class<T> requiredType){
		return wac.getBean(requiredType);
	}
}
