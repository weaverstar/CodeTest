package com.uway.resale.mapper;

import com.uway.resale.entity.ResaleModel;
import org.apache.ibatis.annotation.myibatis.GenericMapper;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author admin
 */
public interface ResaleMapper extends GenericMapper<ResaleModel,Long>{

	//------------------请在此添加自定义方法（开始）------------------
	//------------------请在此添加自定义方法（结束）------------------
    public void deleteByIds(List<String> resaleIds);
    public void deleteByMainId(String mainId);
    public List<Map<String, Object>> findObject(String mainId);
}
