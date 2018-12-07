package com.hz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hz.dao.AdvertisingStandardDetailMapper;
import com.hz.dao.CustomerCaseMapper;
import com.hz.domain.CustomerCase;
import com.hz.domain.responseBean.ProposalModuleBean;
import com.hz.service.CustomerCaseService;
import com.hz.util.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lyp
 * @date 2018/12/5
 */
@Service
public class CustomerCaseServiceImpl implements CustomerCaseService {

    @Autowired
    CustomerCaseMapper customerCaseMapper;
    @Autowired
    AdvertisingStandardDetailMapper advertisingStandardDetailMapper;


    @Override
    public PageInfo<CustomerCase> getCustomerCaseList(CustomerCase customerCase, PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<CustomerCase> customerCases = customerCaseMapper.getCustomerList(customerCase);
        PageInfo<CustomerCase> customerCasePageInfo = new PageInfo<>(customerCases);
        return customerCasePageInfo;
    }

    @Override
    public int countCustomerCase(CustomerCase customerCase) {

        return customerCaseMapper.countCustomer(customerCase);
    }

    @Override
    public void updateCustomerCase(CustomerCase customerCase) {

        customerCaseMapper.updateByPrimaryKeySelective(customerCase);

    }

    @Override
    public void createCustomerCase(CustomerCase customerCase) {
        customerCaseMapper.insertSelective(customerCase);
    }

    @Override
    public CustomerCase getCustomerCaseInfo(int id) {
        CustomerCase customerCase = customerCaseMapper.selectByPrimaryKey(id);
        return customerCase;
    }

    @Override
    public List<CustomerCase> getCustomerCaseInfoByModule(ProposalModuleBean proposalModuleBean) {
        return customerCaseMapper.selectProposalBean(proposalModuleBean);
    }


}
