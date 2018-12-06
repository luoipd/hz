package com.hz.service;

import com.hz.domain.Customer;
import com.hz.domain.User;
import com.hz.util.page.PageRequest;

import java.util.List;

/**
 * @author lyp
 * @date 2018/12/5
 */
public interface CustomerService {

    List<Customer> getCustomerList(Customer customer, User sysUser, boolean isDailishang, PageRequest pageRequest);

    int countCustomer(Customer customer,User sysUser,boolean isDailishang);

    Customer getCustomerInfoById(int id);

    void updateCustomer(Customer customer);

    void createCustomer(Customer customer);
}
