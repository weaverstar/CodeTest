package com.uway.product.service;

import com.daboo.service.base.BaseService;
import com.uway.product.entity.ProductType;
import org.springframework.remoting.service.annotation.RemoteService;

import java.util.List;

/**
 *
 */
@RemoteService
public interface ProductTypeService extends BaseService<ProductType,Integer> {
    public void deleteByIds(List<String> ids);
}
