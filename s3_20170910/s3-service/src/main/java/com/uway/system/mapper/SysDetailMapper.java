package com.uway.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotation.myibatis.GenericMapper;

import com.uway.system.entity.SysDetail;


public interface SysDetailMapper extends GenericMapper<SysDetail, Long> {

	public void deleteByCondition(Map<String, Object> map);
	
	/**
	 * 查询所有部件
	 * @param dictId
	 * @return
	 */
	public List<SysDetail> findAllCarPart(Integer dictId);
}
