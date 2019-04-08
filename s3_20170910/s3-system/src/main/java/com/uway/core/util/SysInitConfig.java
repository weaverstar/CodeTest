package com.uway.core.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.uway.common.utils.Config;

/**
 * @author db
 * <b>功能：</b><br>
 * 加载jsb_config.xml
 * 
 */
public class SysInitConfig {
	protected static final Logger logger = LoggerFactory.getLogger(SysInitConfig.class);

	private static SysInitConfig instance = new SysInitConfig();

	private SysInitConfig() {
	}

	public static SysInitConfig getInstance() {
		return instance;
	}

	public void loadConfigXML() {
		// 获取配置文件
		Resource resource = new ClassPathResource("jsb_config.xml");
		try {
			//
			Config config = new Config(resource.getInputStream(),"//jsb-config/");
			fillPropMap(config);
			logger.info("display the Globals Parameter {}",propMap);
			logger.info("*************Getting  {}  is ok!*************",
					resource.getFilename());
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("*************Getting {} is err!*************",
					resource.getFilename());
		}
	}
	
	private void fillPropMap(Config config) {
		propMap.put(CfgProp.jsb_REMOTESERVERURL, config.get("path/remoteServerUrl", ""));
		propMap.put(CfgProp.jsb_DEFAULTPASSWORD, config.get("passwrod/defaultPassword", ""));
		propMap.put(CfgProp.jsb_VERSION, config.get("version", ""));
		propMap.put(CfgProp.jsb_HTTPCLIENTPOLICY_CONNECTIONTIMEOUT, config.get("webservice/httpClientPolicy/ConnectionTimeout", ""));
		propMap.put(CfgProp.jsb_HTTPCLIENTPOLICY_RECEIVETIMEOUT, config.get("webservice/httpClientPolicy/ReceiveTimeout", ""));
		propMap.put(CfgProp.jsb_HTTPCLIENTPOLICY_ALLOWCHUNKING, config.get("webservice/httpClientPolicy/AllowChunking", ""));
	}
	 

	public String get(CfgProp prop) {
	   return propMap.get(prop);
	}
	
	public static enum CfgProp {
		jsb_REMOTESERVERURL,
		jsb_DEFAULTPASSWORD,
		jsb_VERSION,
		jsb_HTTPCLIENTPOLICY_CONNECTIONTIMEOUT,
		jsb_HTTPCLIENTPOLICY_RECEIVETIMEOUT,
		jsb_HTTPCLIENTPOLICY_ALLOWCHUNKING
	  }

	 
	  private Map<CfgProp, String> propMap = new HashMap<CfgProp, String>();
}