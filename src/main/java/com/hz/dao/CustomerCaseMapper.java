package com.hz.dao;

import com.hz.domain.CustomerCase;
import com.hz.domain.responseBean.ProposalModuleBean;

import java.util.List;

public interface CustomerCaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerCase record);

    int insertSelective(CustomerCase record);

    CustomerCase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerCase record);

    int updateByPrimaryKey(CustomerCase record);

    List<CustomerCase> getCustomerList(CustomerCase customerCase);

    int countCustomer(CustomerCase customerCase);

    List<CustomerCase> selectProposalBean(ProposalModuleBean proposalModuleBean);


}