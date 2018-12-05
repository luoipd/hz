package com.hz.dao;

import com.hz.domain.Customer;

import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    List<Customer> selectCustomerList(Customer customer);

    List<Customer> selectCustomerListByIds(Customer customer);

    int countCustomerListByIds(Customer customer);

    int countCustomerList(Customer customer);
}