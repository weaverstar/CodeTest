package com.uway.web.context;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


import com.uway.system.entity.SysDetail;
import com.uway.system.service.DictCache;


/**
 * 系统缓存操作类
 */
public class SystemCache {
	private static Logger log = Logger.getLogger(SystemCache.class);
	public static final String CACHE_DICT = "CACHE_DICT";
	public static final String CACHE_BRAND_FIRST = "CACHE_BRAND_FIRST";
	public static final String CACHE_ALL_BRAND = "CACHE_ALL_BRAND";
	public static final String CACHE_ALL_CAR_FAMILY = "CACHE_ALL_CAR_FAMILY";
	public static final String CACHE_CAR_PART = "CACHE_CAR_PART";
	
	//缓存数据字典
	public static void cacheAllDict(){
		log.debug("method: cacheAllDict() ");
		OscacheFactory oscacheFactory = OscacheFactory.getInstance();
		Hashtable<String, Map<String, SysDetail>> dict = DictCache.getInstance().loadDictData();
		oscacheFactory.putObject(CACHE_DICT, dict); //缓存基础数据
	}
		
		
	//重新缓存数据字典
	public synchronized static  void reCacheDict() {
		log.debug("method: reCacheDict() ");
		OscacheFactory.getInstance().removeObject(CACHE_DICT);
		
		OscacheFactory oscacheFactory = OscacheFactory.getInstance();
		Hashtable<String, Map<String, SysDetail>> dict = DictCache.getInstance().loadDictData();
		oscacheFactory.putObject(CACHE_DICT, dict); //缓存基础数据
	}
	
}
