package com.uway.resale.mapper;

import com.uway.resale.entity.ResaleMainModel;
import org.apache.ibatis.annotation.myibatis.GenericMapper;

import java.util.List;

/**
 * Created by Administrator on 2018/9/18.
 */
public interface ResaleMainMapper extends GenericMapper<ResaleMainModel,Integer> {
    public void deleteByIds(List<String> resaleMainIds);
    public int insertResaleMain(ResaleMainModel resaleMainModel);
}
