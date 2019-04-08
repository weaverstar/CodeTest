package com.uway.web.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.client.RemoteServiceScannerConfigurer;
import org.springframework.web.context.ContextLoaderListener;

import com.uway.core.util.SysInitConfig;
import com.uway.core.util.SysInitConfig.CfgProp;


/**
 * 系统启动时加载配置文件
 * @version 1.0
 */
public class JsbContextLoaderListener extends ContextLoaderListener implements ServletContextListener {


	protected static final Logger logger = LoggerFactory.getLogger(JsbContextLoaderListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent event) { 
		super.contextDestroyed(event);
	}
	 
	 
	public void contextInitialized(ServletContextEvent event) {
		logger.info("begin init jsb-config.xml");
		SysInitConfig.getInstance().loadConfigXML();
		logger.info("end init jsb-config.xml");
		ServletContext servletContext = event.getServletContext();
		/**
		 *servletContext 通过Servlet的上下文，获取web.xml文件中ScanServicePackage对应的值
		 *此处其实是扫描JAR包【jsb_c】中的service接口
		 */
		RemoteServiceScannerConfigurer.setBasePackage(servletContext.getInitParameter("ScanServicePackage"));
		RemoteServiceScannerConfigurer.setBaseUrl(SysInitConfig.getInstance().get(CfgProp.jsb_REMOTESERVERURL));
		super.contextInitialized(event);
		
		servletContext.setAttribute("jsVersion", SysInitConfig.getInstance().get(CfgProp.jsb_VERSION));
	}
	
}
