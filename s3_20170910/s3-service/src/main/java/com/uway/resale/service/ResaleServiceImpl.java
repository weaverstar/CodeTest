package com.uway.resale.service;
import com.daboo.service.impl.base.BaseServiceImpl;
import com.uway.resale.entity.ResaleModel;
import com.uway.resale.mapper.ResaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 *  零售
 */
@Service
@Transactional
public class ResaleServiceImpl extends BaseServiceImpl<ResaleModel,Long> implements ResaleService {
    @Autowired
    private ResaleMapper mapper;

    @Autowired
    public void setMapper(ResaleMapper mapper) {
        super.setGenericMapper(mapper);
    }

    @Override
    public void deleteByIds(List<String> resaleIds) {
        mapper.deleteByIds(resaleIds);
    }

    @Override
    public void deleteByMainId(String mainId) {
        mapper.deleteByMainId(mainId);
    }

    @Override
    public List<Map<String, Object>> findAllWholeSale(String mainId) {
        return mapper.findObject(mainId);
    }
}
