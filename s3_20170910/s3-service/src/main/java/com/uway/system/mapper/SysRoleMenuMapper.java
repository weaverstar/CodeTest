package com.uway.system.mapper;

import java.util.Map;

import org.apache.ibatis.annotation.myibatis.GenericMapper;

import com.uway.system.entity.SysRoleMenu;


public interface SysRoleMenuMapper extends GenericMapper<SysRoleMenu,Long>{
	
	public int deleteByCondition(Map<String, Object> condition);

}
