package com.uway.resale.service;

import com.daboo.service.base.BaseService;
import com.daboo.service.impl.base.BaseServiceImpl;
import com.uway.resale.entity.RelaseMainDayNumModel;
import com.uway.resale.mapper.ResaleMainDayNumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service
@Transactional
public class ResaleMainDayNumServiceImpl extends BaseServiceImpl<RelaseMainDayNumModel,Integer> implements ResaleMainDayNumService{
    @Autowired
    private ResaleMainDayNumMapper mapper;
    @Autowired
    public void setMapper(ResaleMainDayNumMapper mapper) {
        super.setGenericMapper(mapper);
    }
}
