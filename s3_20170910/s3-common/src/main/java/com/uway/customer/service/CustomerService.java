package com.uway.customer.service;

import com.daboo.service.base.BaseService;
import com.uway.customer.entity.Customer;
import org.springframework.remoting.service.annotation.RemoteService;

import java.util.List;

/**
 *
 */
@RemoteService
public interface CustomerService extends BaseService<Customer,Integer> {
    public void deleteByIds(List<String> mainIds);
}
