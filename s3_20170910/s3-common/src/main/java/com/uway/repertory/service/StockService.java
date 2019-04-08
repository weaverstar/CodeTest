package com.uway.repertory.service;

import com.daboo.service.base.BaseService;
import com.uway.repertory.entity.Stock;
import org.springframework.remoting.service.annotation.RemoteService;

import java.util.List;
import java.util.Map;

/**
 *
 */
@RemoteService
public interface StockService extends BaseService<Stock,Integer>{
    public  void deleteByIds(List<String> ids);
    public  List<Stock> searchByPaging(Map<String,Object> map);
    public  int total(Map<String,Object> map);
    public  void addRefresh(Stock stock);
    public  void updateRefresh(int id,Stock stock);
    public void deleteRefresh(List<Map<String,Object>> stockList);
    public List<Map<String,Object>> findByIds(List<String> ids);
}
