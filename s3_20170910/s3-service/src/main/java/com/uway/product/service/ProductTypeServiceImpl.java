package com.uway.product.service;

import com.daboo.service.impl.base.BaseServiceImpl;
import com.uway.product.entity.ProductType;
import com.uway.product.mapper.ProductTypeMapper;
import com.uway.resale.mapper.ResaleMainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
@Transactional
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType,Integer> implements ProductTypeService {
    @Autowired
    private ProductTypeMapper mapper;

    @Autowired
    public void setMapper(ProductTypeMapper mapper) {
        super.setGenericMapper(mapper);
    }
    @Override
    public void deleteByIds(List<String> ids) {
        mapper.deleteByIds(ids);
    }
}
