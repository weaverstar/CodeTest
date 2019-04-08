package com.uway.resale.service;

import com.daboo.service.impl.base.BaseServiceImpl;
import com.uway.common.utils.NumberUtil;
import com.uway.repertory.entity.Entrepot;
import com.uway.repertory.mapper.EntrepotMapper;
import com.uway.resale.entity.RelaseMainDayNumModel;
import com.uway.resale.entity.ResaleMainModel;
import com.uway.resale.entity.ResaleModel;
import com.uway.resale.mapper.ResaleMainDayNumMapper;
import com.uway.resale.mapper.ResaleMainMapper;
import com.uway.resale.mapper.ResaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  main零售
 */
@Service
@Transactional
public class ResaleMainServiceImpl extends BaseServiceImpl<ResaleMainModel,Integer> implements ResaleMainService {

    @Autowired
    private ResaleMainMapper mapper;
    @Autowired
    private ResaleMapper resaleMapper;
    @Autowired
    private ResaleMainDayNumMapper resaleMainDayNumMapper;
    @Autowired
    private EntrepotMapper entrepotMapper;


    @Autowired
    public void setMapper(ResaleMainMapper mapper) {
        super.setGenericMapper(mapper);
    }

    @Override
    public void deleteByIds(List<String> resaleMainIds) {
        mapper.deleteByIds(resaleMainIds);
    }

    @Override
    public void saveModel(ResaleMainModel mainModel, List<ResaleModel> modelList, List<RelaseMainDayNumModel> mainDayNumModellList) {
        ResaleMainModel newResaleMainModel = super.persist(mainModel);
        int main_id = newResaleMainModel.getMainId();
        double totalPrice = 0;
        if(modelList != null){
            for(ResaleModel model : modelList){
                /**** 同步库存数据***/
                String product = model.getProduct();
                String productType = model.getProductType();
                Map conditionMap = new HashMap<String,Object>();
                conditionMap.put("productId",product);
                conditionMap.put("productType",productType);
                List<Entrepot> entrepotList = entrepotMapper.findModelsByCondition(conditionMap);
                if(entrepotList != null ){
                    if(entrepotList.size() > 0){
                        Entrepot entrepot = entrepotList.get(0);
                        double total =  NumberUtil.parseDouble(entrepot.getTotal(),0) -  NumberUtil.parseDouble(model.getProductNum(),0);
                        entrepot.setTotal(total + "");
                        entrepotMapper.updateModelById(entrepot);
                    }
                }
                /***保存批发数据***/
                model.setMainId(main_id);
                resaleMapper.insertModel(model);
                totalPrice = totalPrice + (NumberUtil.parseDouble(model.getPrice(),0) * NumberUtil.parseDouble(model.getProductNum(),0));
            }
            newResaleMainModel.setTemp1(totalPrice + "");
            mapper.updateModelById(newResaleMainModel);
        }
        /**流水号**/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format( new Date());
        if(mainDayNumModellList != null){
            if(mainDayNumModellList.size() > 0){
                RelaseMainDayNumModel relaseMainDayNumModel = mainDayNumModellList.get(0);
                int num = relaseMainDayNumModel.getNum() + 1;
                relaseMainDayNumModel.setNum(num);
                resaleMainDayNumMapper.updateModelById(relaseMainDayNumModel);
            }else{
                RelaseMainDayNumModel relaseMainDayNumModel = new RelaseMainDayNumModel();
                relaseMainDayNumModel.setNum(1);
                relaseMainDayNumModel.setTimeIndex(time);
                resaleMainDayNumMapper.insertModel(relaseMainDayNumModel);
            }
        }
    }

    @Override
    public void updateModel(String mainId, List<ResaleModel> modelList) {
        Map conditionMap = new HashMap<String,Object>();
        conditionMap.put("mainId",mainId);
        double totalPrice = 0;
        /**恢复库存**/
        List<ResaleModel> resaleModelList = resaleMapper.findModelsByCondition(conditionMap);
        if(resaleModelList != null){
            for(ResaleModel model : resaleModelList){
                String product = model.getProduct();
                String productType = model.getProductType();
                conditionMap.clear();
                conditionMap.put("productId",product);
                conditionMap.put("productType",productType);
                List<Entrepot> entrepotList = entrepotMapper.findModelsByCondition(conditionMap);
                if(entrepotList != null ){
                    if(entrepotList.size() > 0){
                        Entrepot entrepot = entrepotList.get(0);
                        double total =  NumberUtil.parseDouble(entrepot.getTotal(),0) +  NumberUtil.parseDouble(model.getProductNum(),0);
                        entrepot.setTotal(total + "");
                        entrepotMapper.updateModelById(entrepot);
                    }
                }
            }
        }
        /**删除历史数据**/
        resaleMapper.deleteByMainId(mainId);
        /****保存新数据**/
        for(ResaleModel model : modelList){
            /**** 同步库存数据***/
            String product = model.getProduct();
            String productType = model.getProductType();
            conditionMap.clear();
            conditionMap.put("productId",product);
            conditionMap.put("productType",productType);
            List<Entrepot> entrepotList = entrepotMapper.findModelsByCondition(conditionMap);
            if(entrepotList != null ){
                if(entrepotList.size() > 0){
                    Entrepot entrepot = entrepotList.get(0);
                    double total =  NumberUtil.parseDouble(entrepot.getTotal(),0) -  NumberUtil.parseDouble(model.getProductNum(),0);
                    entrepot.setTotal(total + "");
                    entrepotMapper.updateModelById(entrepot);
                }
            }
            /***保存批发数据***/
            resaleMapper.insertModel(model);

            totalPrice = totalPrice + (NumberUtil.parseDouble(model.getPrice(),0) * NumberUtil.parseDouble(model.getProductNum(),0));
        }
        ResaleMainModel resaleMainModel = mapper.getObjectById(Integer.parseInt(mainId));
        resaleMainModel.setTemp1(totalPrice+"");
        mapper.updateModelById(resaleMainModel);
    }

    @Override
    public void deleteRefresh(String ids) {
        String[] id = ids.split(",");
        List<String> wholeSaleIds = Arrays.asList(id);
        mapper.deleteByIds(wholeSaleIds);
        for(String mainId : wholeSaleIds){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("mainId",mainId);
            List<ResaleModel> resaleModelList  = resaleMapper.findModelsByCondition(map);
            if(resaleModelList != null){
                for(ResaleModel resaleModel : resaleModelList){
                    Map conditionMap = new HashMap<String,Object>();
                    conditionMap.put("productId", resaleModel.getProduct());
                    conditionMap.put("productType",resaleModel.getProductType());
                    List<Entrepot> entrepotList = entrepotMapper.findModelsByCondition(conditionMap);
                    if(entrepotList != null ){
                        if(entrepotList.size() > 0){
                            Entrepot entrepot = entrepotList.get(0);
                            double total = NumberUtil.parseDouble(entrepot.getTotal(),0) + NumberUtil.parseDouble(resaleModel.getProductNum(),0);
                            entrepot.setTotal(total + "");
                            entrepotMapper.updateModelById(entrepot);
                        }
                    }
                    resaleMapper.deleteById(Long.parseLong(resaleModel.getResaleId()+""));
                }
            }
        }

    }

}
