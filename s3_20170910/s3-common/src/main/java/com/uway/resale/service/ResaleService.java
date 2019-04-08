package com.uway.resale.service;

import com.daboo.service.base.BaseService;
import com.uway.resale.entity.ResaleModel;
import java.util.List;
import java.util.Map;
import org.springframework.remoting.service.annotation.RemoteService;

@RemoteService
public  interface ResaleService extends BaseService<ResaleModel, Long>
{
  public  void deleteByIds(List<String> paramList);

  public  void deleteByMainId(String paramString);

  public  List<Map<String, Object>> findAllWholeSale(String paramString);
}

