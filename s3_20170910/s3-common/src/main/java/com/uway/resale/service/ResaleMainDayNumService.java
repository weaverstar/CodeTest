package com.uway.resale.service;

import com.daboo.service.base.BaseService;
import com.uway.resale.entity.RelaseMainDayNumModel;
import org.springframework.remoting.service.annotation.RemoteService;

@RemoteService
public  interface ResaleMainDayNumService extends BaseService<RelaseMainDayNumModel, Integer>
{
}
