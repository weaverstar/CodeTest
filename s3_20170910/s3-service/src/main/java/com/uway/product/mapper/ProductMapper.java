package com.uway.product.mapper;


import com.uway.product.entity.Product;
import org.apache.ibatis.annotation.myibatis.GenericMapper;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface ProductMapper extends GenericMapper<Product,Integer> {
    public void deleteByIds(List<String> ids);
    public List<Product> paging(Map<String, Object> map);
    public int total(Map<String, Object> map);
    public Product getProductById(int id);
}
