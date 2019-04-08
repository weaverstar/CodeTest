package com.uway.product.service;

import com.daboo.service.base.BaseService;
import com.uway.product.entity.Product;
import org.springframework.remoting.service.annotation.RemoteService;

import java.util.List;
import java.util.Map;

/**
 *
 */
@RemoteService
public interface ProductService extends BaseService<Product,Integer> {
    public void deleteByIds(List<String> ids);
    public  List<Product> searchByPaging(Map<String,Object> map);
    public  int total(Map<String,Object> map);
    public Product getProductById(int id);
}
