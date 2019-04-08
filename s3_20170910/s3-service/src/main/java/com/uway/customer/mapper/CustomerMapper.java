package com.uway.customer.mapper;

import com.uway.customer.entity.Customer;
import org.apache.ibatis.annotation.myibatis.GenericMapper;

import java.util.List;

/**
 *
 */
public interface CustomerMapper extends GenericMapper<Customer,Integer> {
    public void deleteByIds(List<String> resaleMainIds);
}
