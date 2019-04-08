package com.uway.product.mapper;

import com.uway.product.entity.ProductType;
import org.apache.ibatis.annotation.myibatis.GenericMapper;

import java.util.List;

/**
 *
 */
public interface ProductTypeMapper extends GenericMapper<ProductType,Integer> {
    public void deleteByIds(List<String> typeIds);
}
