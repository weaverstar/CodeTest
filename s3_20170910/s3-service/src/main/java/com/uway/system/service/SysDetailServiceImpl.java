package com.uway.system.service;

import com.daboo.service.impl.base.BaseServiceImpl;
import com.uway.system.entity.SysDetail;
import com.uway.system.mapper.SysDetailMapper;
import com.uway.web.context.SystemCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional
public class SysDetailServiceImpl extends BaseServiceImpl<SysDetail, Long>
		implements SysDetailService {
	@Autowired
	public void setMapper(SysDetailMapper mapper) {
		super.setGenericMapper(mapper);
	}
	
	@Override
	public SysDetail persist(SysDetail record) {
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		record.setDetailStatus(1);
		super.persist(record);
		SystemCache.reCacheDict();
		return record;
	}
	
	@Override
	public int removeById(Long detailId) {
		int flag = super.removeById(detailId);
		SystemCache.reCacheDict();
		return flag;
	}

	@Override
	public int updateById(SysDetail entity) {
		int rows = super.updateById(entity);
		SystemCache.reCacheDict();
		return rows;
	}
	
	
}
