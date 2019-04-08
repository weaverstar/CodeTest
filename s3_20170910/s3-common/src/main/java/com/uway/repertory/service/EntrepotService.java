package com.uway.repertory.service;

import com.daboo.service.base.BaseService;
import com.uway.product.entity.Product;
import com.uway.repertory.entity.Entrepot;
import org.springframework.remoting.service.annotation.RemoteService;

import java.util.List;
import java.util.Map;

/**
 *
 */
@RemoteService
public interface EntrepotService extends BaseService<Entrepot,Integer> {
    public void deleteByIds(List<String> ids);
    public  List<Entrepot> searchByPaging(Map<String,Object> map);
    public  int total(Map<String,Object> map);
}
