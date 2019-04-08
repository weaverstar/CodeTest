package com.uway.repertory.mapper;

import com.uway.repertory.entity.Entrepot;
import org.apache.ibatis.annotation.myibatis.GenericMapper;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface EntrepotMapper extends GenericMapper<Entrepot,Integer> {
    public void deleteByIds(List<String> ids);
    public List<Entrepot> paging(Map<String, Object> map);
    public int total(Map<String, Object> map);
}
