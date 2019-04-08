package com.uway.repertory.service;

import com.daboo.service.impl.base.BaseServiceImpl;
import com.uway.product.entity.Product;
import com.uway.repertory.entity.Entrepot;
import com.uway.repertory.mapper.EntrepotMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
@Transactional
public class EntrepotServiceImpl extends BaseServiceImpl<Entrepot,Integer> implements EntrepotService{
    @Autowired
    private EntrepotMapper mapper;

    @Autowired
    public void setMapper(EntrepotMapper mapper) {
        super.setGenericMapper(mapper);
    }

    @Override
    public void deleteByIds(List<String> ids) {
        mapper.deleteByIds(ids);
    }

    @Override
    public List<Entrepot> searchByPaging(Map<String, Object> map) {
        return mapper.paging(map);
    }

    @Override
    public int total(Map<String, Object> map) {
        return mapper.total(map);
    }
}
