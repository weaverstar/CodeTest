package com.uway.system.service;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.uway.common.utils.PropertiesUtils;
import com.uway.system.entity.SysDetail;
import com.uway.system.entity.SysDict;
import com.uway.system.mapper.SysDetailMapper;
import com.uway.system.mapper.SysDictMapper;
import com.uway.web.context.ObjectFactory;
import com.uway.web.context.OscacheFactory;
import com.uway.web.context.SystemCache;



public class DictCache {
	private static DictCache instance = null;
	private DictCache() {}
	public static synchronized DictCache getInstance() {
		if (instance == null) {instance = new DictCache();}
		return instance;
	}

	@SuppressWarnings("unchecked")
	private static Hashtable<String, Map<String, SysDetail>> getDictFromCache() {
		Hashtable<String, Map<String, SysDetail>> dict = (Hashtable<String, Map<String, SysDetail>>) OscacheFactory.getInstance().getObject(SystemCache.CACHE_DICT);
		return dict;
	}
	 
	public static synchronized void reloadDictData(){
		SystemCache.reCacheDict();
	}

	public Hashtable<String, Map<String, SysDetail>>loadDictData(){
		SysDictMapper sDictMapper = (SysDictMapper) ObjectFactory.getBean(SysDictMapper.class); 
		SysDetailMapper SysDetailMapper = (SysDetailMapper) ObjectFactory.getBean(SysDetailMapper.class); 
		Hashtable<String, Map<String, SysDetail>> dict = new Hashtable<String, Map<String, SysDetail>>();
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("detailStatus", 1);
		List<SysDict>sDictList = sDictMapper.findModelsByCondition(null);
		List<SysDetail> SysDetailList = SysDetailMapper.findModelsByCondition(para);
		Map<String, SysDetail> dictDetailMap = null;
		for(SysDict sd:sDictList){
			String dictId = sd.getDictId().toString();
			dictDetailMap = new HashMap<String, SysDetail>();
			for (SysDetail SysDetail : SysDetailList) {
				if(dictId.equals(SysDetail.getDictId().toString())){
					dictDetailMap.put(SysDetail.getDetailName(), SysDetail);
				}
			}
			dict.put(sd.getDictName(), dictDetailMap);
		}
		return dict;
	}
	
	
	
	public List<SysDetail> loadCarPartsData(){
		SysDetailMapper detailMapper = (SysDetailMapper) ObjectFactory.getBean(SysDetailMapper.class); 
		
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("detailStatus", 1);
		para.put("dictId", PropertiesUtils.getProperty("carPartId"));
		return detailMapper.findModelsByCondition(para);
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<SysDetail> getCarPartsFromCache() {
		return (List<SysDetail>) OscacheFactory.getInstance().getObject(SystemCache.CACHE_CAR_PART);		
	}
	
	
	//根据dictName得到detail对应的明细map<name,value>
	public static Map<String, String> getDetailNameMapByDictName(String dictName){
		Hashtable<String, Map<String, SysDetail>> dict = getDictFromCache();
		if (dict == null || dict.size() == 0) {
			return null;
		}
		Map<String, String> params=new TreeMap<String, String>();
		Map<String, SysDetail> dictDetailMap = dict.get(dictName);
		for (Map.Entry<String, SysDetail> e : dictDetailMap.entrySet()) {  
			SysDetail d = e.getValue();
			params.put(d.getDetailName(), d.getDetailValue().toString());
		}  
		return params;
	}
	//根据dictName得到detail对应的明细map<value,name>
	public static Map<String, String> getDetailValueMapByDictName(String dictName){
		Hashtable<String, Map<String, SysDetail>> dict = getDictFromCache();
		if (dict == null || dict.size() == 0) {
			return null;
		}
		Map<String, String> params=new TreeMap<String, String>();
		Map<String, SysDetail> dictDetailMap = dict.get(dictName);
		for (Map.Entry<String, SysDetail> e : dictDetailMap.entrySet()) {  
			SysDetail d = e.getValue();
			params.put(d.getDetailValue().toString(),d.getDetailName());
		}  
		return params;
	}
	
	//根据dictName和detailName得到detail对应的value
	public static String getDetailValue(String dictName, String detailName){
		Hashtable<String, Map<String, SysDetail>> dict = getDictFromCache();
		if (dict == null || dict.size() == 0) {
			return null;
		}
		SysDetail dictDetail = dict.get(dictName).get(detailName);
		return dictDetail.getDetailValue().toString();
	}

}