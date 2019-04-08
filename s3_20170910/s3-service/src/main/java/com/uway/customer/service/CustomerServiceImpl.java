package com.uway.customer.service;

import com.daboo.service.impl.base.BaseServiceImpl;
import com.uway.customer.entity.Customer;
import com.uway.customer.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
@Transactional
public class CustomerServiceImpl extends BaseServiceImpl<Customer,Integer> implements CustomerService {

    @Autowired
    private CustomerMapper mapper;

    @Autowired
    public void setMapper(CustomerMapper mapper) {
        super.setGenericMapper(mapper);
    }

    @Override
    public void deleteByIds(List<String> ids) {
        mapper.deleteByIds(ids);
    }
}
