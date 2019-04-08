package com.uway.system.service;

import com.daboo.service.base.BaseService;
import com.uway.system.entity.SysLog;
import org.springframework.remoting.service.annotation.RemoteService;

@RemoteService
public interface SysLogService extends BaseService<SysLog,Long> {

}
