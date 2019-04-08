package com.uway.repertory.mapper;

import com.uway.repertory.entity.Stock;
import org.apache.ibatis.annotation.myibatis.GenericMapper;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface StockMapper extends GenericMapper<Stock,Integer> {
    public void deleteByIds(List<String> ids);
    public List<Stock> paging(Map<String, Object> map);
    public int total(Map<String, Object> map);
    public List<Map<String,Object>> findByIds(List<String> ids);
}
