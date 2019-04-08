package com.uway.wholeSale.service;

import com.daboo.service.base.BaseService;
import com.uway.resale.entity.RelaseMainDayNumModel;
import com.uway.wholeSale.entity.WholeSaleMainModel;
import com.uway.wholeSale.entity.WholeSaleModel;
import java.util.List;
import java.util.Map;
import org.springframework.remoting.service.annotation.RemoteService;

@RemoteService
public  interface WholeSaleMainService extends BaseService<WholeSaleMainModel, Long>
{
  public  void deleteByIds(List<String> paramList);

  public  void saveModel(WholeSaleMainModel paramWholeSaleMainModel, List<WholeSaleModel> paramList, List<RelaseMainDayNumModel> paramList1);

  public  void updateModel(WholeSaleMainModel paramWholeSaleMainModel, List<WholeSaleModel> paramList);

  public  List<Map<String, Object>> findObject(Map<String, Object> paramMap);

  public  int findObjectTotal(Map<String, Object> paramMap);

  public  void deleteRefresh(String paramString);
}
