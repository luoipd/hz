package com.hz.service;

import com.github.pagehelper.PageInfo;
import com.hz.domain.Customer;
import com.hz.domain.User;
import com.hz.util.page.PageRequest;

import java.util.List;

/**
 * @author lyp
 * @date 2018/12/5
 */
public interface CustomerService {

    PageInfo<Customer> getCustomerList(Customer customer, User sysUser, boolean isDailishang, boolean isAdmin, PageRequest pageRequest);

    int countCustomer(Customer customer, User sysUser, boolean isDailishang);

    Customer getCustomerInfoById(int id);

    void updateCustomer(Customer customer);

    void createCustomer(Customer customer);
}
