package com.uway.system.service;

import java.util.List;

import com.daboo.service.base.BaseService;
import org.springframework.remoting.service.annotation.RemoteService;

import com.uway.system.entity.SysRole;
import com.uway.system.entity.SysRoleMenu;
import com.uway.system.entity.SysRoleUser;

@RemoteService
public interface SysRoleService extends BaseService<SysRole,Long> {
	
	public List<SysRoleMenu> selectRoleMenuByCondition(Long roleId);	
	
	public List<SysRoleUser> selectSUserRoleByCondition(Long roleId);	
	
	public void  bindRoleMenu(Long roleId,String[] menuIds);
	
	public void  bindRoleUser(Long roleId,String[] userIds);
	
	public  void  deleteRoleByIds(String[] roleIds);
	
	public List<SysRole> selectRolesByUserId(Long userId);
	
}
