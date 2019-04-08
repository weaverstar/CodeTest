package com.uway.wholeSale.service;

import com.daboo.service.base.BaseService;
import com.uway.wholeSale.entity.WholeSaleModel;
import java.util.List;
import java.util.Map;
import org.springframework.remoting.service.annotation.RemoteService;

@RemoteService
public  interface WholeSaleService extends BaseService<WholeSaleModel, Long>
{
  public  void deleteByIds(List<String> paramList);

  public  void deleteByMainId(String paramString);

  public  List<Map<String, Object>> findAllWholeSale(String paramString);
}
