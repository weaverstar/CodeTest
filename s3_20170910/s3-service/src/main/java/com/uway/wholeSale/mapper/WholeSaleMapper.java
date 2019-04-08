package com.uway.wholeSale.mapper;

import com.uway.wholeSale.entity.WholeSaleModel;
import org.apache.ibatis.annotation.myibatis.GenericMapper;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface WholeSaleMapper extends GenericMapper<WholeSaleModel,Long> {
    public void deleteByIds(List<String> wholeSaleIds);
    public void deleteByMainId(String mainId);
    public List<Map<String, Object>> findObject(String mainId);
}
