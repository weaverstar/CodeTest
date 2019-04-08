package com.uway.web.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.ibatis.annotation.myibatis.GenerateMapperSqlUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;



/**
 * 系统启动时加载
 * @version 1.0
 */
public class SpringContextLoaderListener extends ContextLoaderListener implements ServletContextListener {
	protected static final Logger logger = LoggerFactory.getLogger(SpringContextLoaderListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent event) { 
		super.contextDestroyed(event);
	}
	
	 
	public void contextInitialized(ServletContextEvent event) {
		/**
		 * 初始化容器，<mybatis:scan base-package="com.cttr.grcloud.*.mapper" /> 此段会先执行
		 */
		super.contextInitialized(event);
		
		ServletContext sc = event.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		SqlSessionFactory factory = context.getBean(SqlSessionFactory.class);
		GenerateMapperSqlUtil genMapperUtil = new GenerateMapperSqlUtil();
		genMapperUtil.setConfiguration(factory.getConfiguration());
		
		/**
		 * 通过Server端web.xml中配置需要扫描的路径
		 */
		genMapperUtil.initMyIbatisMapper(sc.getInitParameter("contextScanPackage"));	
		if (sc != null){
			ObjectFactory.getInstance(sc);
		}
		SystemCache.cacheAllDict();

	}
	
}
