package com.uway.product.service;


import com.daboo.service.impl.base.BaseServiceImpl;
import com.uway.product.entity.Product;
import com.uway.product.mapper.ProductMapper;
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
public class ProductServiceImpl extends BaseServiceImpl<Product,Integer> implements ProductService{
    @Autowired
    private ProductMapper mapper;

    @Autowired
    public void setMapper(ProductMapper mapper) {
        super.setGenericMapper(mapper);
    }
    @Override
    public void deleteByIds(List<String> ids) {
        mapper.deleteByIds(ids);
    }

    @Override
    public List<Product> searchByPaging(Map<String, Object> map) {
        return mapper.paging(map);
    }

    @Override
    public int total(Map<String, Object> map) {
        return mapper.total(map);
    }

    @Override
    public Product getProductById(int id) {
        return mapper.getProductById(id);
    }
}
