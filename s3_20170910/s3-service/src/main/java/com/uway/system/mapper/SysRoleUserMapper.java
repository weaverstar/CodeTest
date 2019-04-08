package com.uway.system.mapper;

import java.util.Map;

import org.apache.ibatis.annotation.myibatis.GenericMapper;

import com.uway.system.entity.SysRoleUser;


public interface SysRoleUserMapper extends GenericMapper<SysRoleUser,Long>{
	
	public int deleteByCondition(Map<String, Object> condition);

}
