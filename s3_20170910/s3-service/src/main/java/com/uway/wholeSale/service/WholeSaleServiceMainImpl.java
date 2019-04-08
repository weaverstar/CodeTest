package com.uway.wholeSale.service;

import com.daboo.service.impl.base.BaseServiceImpl;
import com.uway.common.utils.NumberUtil;
import com.uway.repertory.entity.Entrepot;
import com.uway.repertory.mapper.EntrepotMapper;
import com.uway.resale.entity.RelaseMainDayNumModel;
import com.uway.resale.mapper.ResaleMainDayNumMapper;
import com.uway.wholeSale.entity.WholeSaleMainModel;
import com.uway.wholeSale.entity.WholeSaleModel;
import com.uway.wholeSale.mapper.WholeSaleMainMapper;
import com.uway.wholeSale.mapper.WholeSaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  mainn批发
 */
@Service
@Transactional
public class WholeSaleServiceMainImpl  extends BaseServiceImpl<WholeSaleMainModel,Long> implements WholeSaleMainService{
    @Autowired
    private WholeSaleMainMapper mapper;
    @Autowired
    private WholeSaleMapper saleMapper;
    @Autowired
    private EntrepotMapper entrepotMapper;
    @Autowired
    private ResaleMainDayNumMapper resaleMainDayNumMapper;

    @Autowired
    public void setMapper(WholeSaleMainMapper mapper) {
        super.setGenericMapper(mapper);
    }

    @Override
    public void deleteByIds(List<String> wholeSaleMainIds) {
        mapper.deleteByIds(wholeSaleMainIds);
    }

    @Override
    public void saveModel(WholeSaleMainModel mainModel, List<WholeSaleModel> modelList, List<RelaseMainDayNumModel> dayNumList) {
        double totalPrice = 0;
        /***保存批发客户基本信息***/
        WholeSaleMainModel newMainModel = super.persist(mainModel);
        int main_id = newMainModel.getMainId();
        if(modelList != null){
            for(WholeSaleModel model : modelList){
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
                saleMapper.insertModel(model);
                totalPrice = totalPrice + (NumberUtil.parseDouble(model.getPrice(),0) * NumberUtil.parseDouble(model.getProductNum(),0));
            }
            newMainModel.setTemp1(totalPrice+"");
            newMainModel.setTemp2("未付款");
            long flowId = 0;
            mapper.updateModelById(newMainModel);
            /**流水号**/
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String time = sdf.format( new Date());
            if(dayNumList.size() > 0){
                RelaseMainDayNumModel relaseMainDayNumModel = dayNumList.get(0);
                int num = relaseMainDayNumModel.getNum() + 1;
                relaseMainDayNumModel.setNum(num);
                resaleMainDayNumMapper.updateModelById(relaseMainDayNumModel);
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                flowId = Long.parseLong(sdf2.format(new Date()) + "0000") + num;
            }else{
                RelaseMainDayNumModel relaseMainDayNumModel = new RelaseMainDayNumModel();
                relaseMainDayNumModel.setNum(1);
                relaseMainDayNumModel.setTimeIndex(time);
                resaleMainDayNumMapper.insertModel(relaseMainDayNumModel);
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                flowId = Long.parseLong(sdf2.format(new Date()) + "0000") + 1;
            }
            newMainModel.setTemp3(flowId + "");
            mapper.updateModelById(newMainModel);
        }
    }

    @Override
    public void updateModel(WholeSaleMainModel mainModel, List<WholeSaleModel> modelList) {
        int mainId = mainModel.getMainId();
        double totalPrice = 0;
        Map conditionMap = new HashMap<String,Object>();
        conditionMap.put("mainId",mainId);
        /**恢复库存**/
        List<WholeSaleModel> wholeSaleModelList = saleMapper.findModelsByCondition(conditionMap);
        if(wholeSaleModelList != null){
            for(WholeSaleModel model : wholeSaleModelList){
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
        saleMapper.deleteByMainId(mainId+"");
        /****保存新数据**/
        for(WholeSaleModel model : modelList){
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
            saleMapper.insertModel(model);
            totalPrice = totalPrice + (NumberUtil.parseDouble(model.getPrice(),0) * NumberUtil.parseDouble(model.getProductNum(),0));
        }
        mainModel.setTemp1(totalPrice+"");
        mapper.updateModelById(mainModel);
    }

    @Override
    public List<Map<String,Object>> findObject(Map<String,Object> map) {
        return mapper.findObject(map);
    }

    @Override
    public int findObjectTotal(Map<String, Object> map) {
        return mapper.findObjectTotal(map);
    }

    @Override
    public void deleteRefresh(String ids) {
        String[] id = ids.split(",");
        List<String> wholeSaleIds = Arrays.asList(id);
        mapper.deleteByIds(wholeSaleIds);
        for(String mainId : wholeSaleIds){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("mainId",mainId);
            List<WholeSaleModel> wholeSaleModelList  = saleMapper.findModelsByCondition(map);
            if(wholeSaleModelList != null){
                for(WholeSaleModel wholeSaleModel : wholeSaleModelList){
                    Map conditionMap = new HashMap<String,Object>();
                    conditionMap.put("productId", wholeSaleModel.getProduct());
                    conditionMap.put("productType",wholeSaleModel.getProductType());
                    List<Entrepot> entrepotList = entrepotMapper.findModelsByCondition(conditionMap);
                    if(entrepotList != null ){
                        if(entrepotList.size() > 0){
                            Entrepot entrepot = entrepotList.get(0);
                            double total = NumberUtil.parseDouble(entrepot.getTotal(),0) + NumberUtil.parseDouble(wholeSaleModel.getProductNum(),0);
                            entrepot.setTotal(total + "");
                            entrepotMapper.updateModelById(entrepot);
                        }
                    }
                    saleMapper.deleteById(Long.parseLong(wholeSaleModel.getWholeSaleId()+""));
                }
            }
        }
    }

}
