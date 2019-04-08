package com.uway.system.service;

import java.util.List;
import java.util.Map;

import com.daboo.service.base.BaseService;
import org.springframework.remoting.service.annotation.RemoteService;

import com.uway.system.entity.SysUser;

@RemoteService
public interface SysUserService extends BaseService<SysUser,Long> {
	
	public void updateUserPwd(SysUser record);
	
	public  void deleteUserByIds(String[] userIds);
}
