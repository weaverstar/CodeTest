package com.uway.wholeSale.service;

import com.daboo.service.impl.base.BaseServiceImpl;
import com.uway.wholeSale.entity.WholeSaleModel;
import com.uway.wholeSale.mapper.WholeSaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 *  批发
 */
@Service
@Transactional
public class WholeSaleServiceImpl extends BaseServiceImpl<WholeSaleModel,Long> implements WholeSaleService{

    @Autowired
    private WholeSaleMapper mapper;

    @Autowired
    public void setMapper(WholeSaleMapper mapper) {
        super.setGenericMapper(mapper);
    }

    @Override
    public void deleteByIds(List<String> wholeSaleIds) {
        mapper.deleteByIds(wholeSaleIds);
    }

    @Override
    public void deleteByMainId(String mainId) {
        mapper.deleteByMainId(mainId);
    }

    @Override
    public List<Map<String, Object>> findAllWholeSale(String mainId) {
        return mapper.findObject(mainId);
    }

    public void saveModel(WholeSaleModel model){
        super.persist(model);
    }

}
