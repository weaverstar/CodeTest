package com.uway.system.mapper;

import java.util.List;

import org.apache.ibatis.annotation.myibatis.GenericMapper;

import com.uway.system.entity.SysRole;


public interface SysRoleMapper extends GenericMapper<SysRole,Long>{

	public List<SysRole>  selectRolesByUserId(Long userId);
}
