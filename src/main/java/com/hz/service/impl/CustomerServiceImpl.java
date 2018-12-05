package com.hz.service.impl;

import com.hz.dao.CustomerMapper;
import com.hz.dao.UserMapper;
import com.hz.domain.Customer;
import com.hz.domain.Role;
import com.hz.domain.User;
import com.hz.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lyp
 * @date 2018/12/5
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public List<Customer> getCustomerList(Customer customer, User sysUser) {

        List<Role> roles = sysUser.getRoles();
        boolean isDailishang =false;
        for(Role role:roles){
            if (role.getId()==4){
                isDailishang=true;
                break;
            }
        }
        if(isDailishang){
            List<Integer> users = userMapper.selectAllSaler(sysUser.getId());
            customer.setCreaterIds(users);
            return customerMapper.selectCustomerListByIds(customer);
        }else{
            customer.setCreaterId(sysUser.getId());
            return customerMapper.selectCustomerList(customer);
        }
    }
}
