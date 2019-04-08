package com.uway.wholeSale.mapper;

import com.uway.wholeSale.entity.WholeSaleMainModel;
import org.apache.ibatis.annotation.myibatis.GenericMapper;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface WholeSaleMainMapper extends GenericMapper<WholeSaleMainModel,Long> {
    public void deleteByIds(List<String> wholeSaleMainIds);
    public List<Map<String,Object>> findObject(Map<String,Object> map);
    public int findObjectTotal(Map<String,Object> map);
}
