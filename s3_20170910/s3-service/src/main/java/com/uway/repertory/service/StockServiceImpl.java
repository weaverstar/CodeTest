package com.uway.repertory.service;

import com.daboo.service.impl.base.BaseServiceImpl;
import com.uway.common.utils.NumberUtil;
import com.uway.common.utils.StringUtils;
import com.uway.product.entity.Product;
import com.uway.repertory.entity.Entrepot;
import com.uway.repertory.entity.Stock;
import com.uway.repertory.mapper.EntrepotMapper;
import com.uway.repertory.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
@Transactional
public class StockServiceImpl extends BaseServiceImpl<Stock,Integer> implements StockService{
    @Autowired
    private StockMapper mapper;
    @Autowired
    private EntrepotMapper entrepotMapper;

    @Autowired
    public void setMapper(StockMapper mapper) {
        super.setGenericMapper(mapper);
    }

    @Override
    public void deleteByIds(List<String> ids) {
        mapper.deleteByIds(ids);
    }

    @Override
    public List<Stock> searchByPaging(Map<String, Object> map) {
        return mapper.paging(map);
    }

    @Override
    public int total(Map<String, Object> map) {
        return mapper.total(map);
    }

    @Override
    public void addRefresh(Stock stock) {
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("productId",stock.getProductId());
        conditionMap.put("productType",stock.getProductType());
        List<Entrepot> entrepotList = entrepotMapper.findModelsByCondition(conditionMap);
        if(entrepotList != null ){
            if(entrepotList.size() > 0){
                Entrepot entrepot = entrepotList.get(0);
                double total = NumberUtil.parseDouble(entrepot.getTotal(),0) + NumberUtil.parseDouble(stock.getTotal(),0);
                entrepot.setTotal(total + "");
                entrepotMapper.updateModelById(entrepot);
            }else{
                Entrepot entrepot = new Entrepot();
                entrepot.setProductId(stock.getProductId());
                entrepot.setProductType(stock.getProductType());
                entrepot.setTotal(stock.getTotal()+"");
                entrepotMapper.insertModel(entrepot);
            }
        }
        mapper.insertModel(stock);
    }

    @Override
    public void updateRefresh(int id,Stock stock) {
        /**恢复库存**/
        Stock oldStock = mapper.getObjectById(id);
        Map conditionMap = new HashMap<String,Object>();
        conditionMap.put("productId",oldStock.getProductId());
        conditionMap.put("productType",oldStock.getProductType());
        List<Entrepot> entrepotList = entrepotMapper.findModelsByCondition(conditionMap);
        if(entrepotList != null ){
            if(entrepotList.size() > 0){
                Entrepot entrepot = entrepotList.get(0);
                double total = NumberUtil.parseDouble(entrepot.getTotal(),0) - NumberUtil.parseDouble(oldStock.getTotal(),0);
                entrepot.setTotal(total + "");
                entrepotMapper.updateModelById(entrepot);
            }
        }
        /**重新更新库存**/
        conditionMap.clear();
        conditionMap.put("productId",stock.getProductId());
        conditionMap.put("productType",stock.getProductType());
        List<Entrepot> entrepotList2 = entrepotMapper.findModelsByCondition(conditionMap);
        if(entrepotList2 != null ){
            if(entrepotList2.size() > 0){
                Entrepot entrepot = entrepotList2.get(0);
                double total = NumberUtil.parseDouble(entrepot.getTotal(),0) + NumberUtil.parseDouble(stock.getTotal(),0);
                entrepot.setTotal(total + "");
                entrepotMapper.updateModelById(entrepot);
            }
        }
        mapper.updateModelById(stock);
    }

    @Override
    public void deleteRefresh(List<Map<String,Object>> stockList) {
        if(stockList != null){
            for(Map<String,Object> map : stockList){
                Map conditionMap = new HashMap<String,Object>();
                conditionMap.put("productId", map.get("product_id"));
                conditionMap.put("productType",map.get("product_type"));
                List<Entrepot> entrepotList = entrepotMapper.findModelsByCondition(conditionMap);
                if(entrepotList != null ){
                    if(entrepotList.size() > 0){
                        Entrepot entrepot = entrepotList.get(0);
                        double total = NumberUtil.parseDouble(entrepot.getTotal(),0) - NumberUtil.parseDouble(map.get("total"),0);
                        entrepot.setTotal(total + "");
                        entrepotMapper.updateModelById(entrepot);
                    }
                }
                mapper.deleteById(Integer.parseInt(map.get("id").toString()));
            }
        }
    }

    @Override
    public List<Map<String,Object>> findByIds(List<String> ids) {
        return mapper.findByIds(ids);
    }
}
