package com.uway.system.service;

import com.daboo.service.impl.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uway.system.entity.SysLog;
import com.uway.system.mapper.SysLogMapper;


@Service
@Transactional
public class SysLogServiceImpl extends BaseServiceImpl<SysLog, Long> implements SysLogService {

	@Autowired
	public void setMapper(SysLogMapper mapper) {
		setGenericMapper(mapper);
	}
}
