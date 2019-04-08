package com.uway.system.service;

import com.daboo.service.base.BaseService;
import org.springframework.remoting.service.annotation.RemoteService;

import com.uway.system.entity.SysDetail;

@RemoteService
public interface SysDetailService extends BaseService<SysDetail,Long> {

}
