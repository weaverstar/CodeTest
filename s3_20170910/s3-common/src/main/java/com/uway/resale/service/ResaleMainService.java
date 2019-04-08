package com.uway.resale.service;

import com.daboo.service.base.BaseService;
import com.uway.resale.entity.RelaseMainDayNumModel;
import com.uway.resale.entity.ResaleMainModel;
import com.uway.resale.entity.ResaleModel;
import java.util.List;
import org.springframework.remoting.service.annotation.RemoteService;

@RemoteService
public  interface ResaleMainService extends BaseService<ResaleMainModel, Integer>
{
  public  void deleteByIds(List<String> paramList);

  public  void saveModel(ResaleMainModel paramResaleMainModel, List<ResaleModel> paramList, List<RelaseMainDayNumModel> paramList1);

  public  void updateModel(String paramString, List<ResaleModel> paramList);

  public  void deleteRefresh(String paramString);
}
