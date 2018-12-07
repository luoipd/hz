package com.hz.service;

import com.github.pagehelper.PageInfo;
import com.hz.domain.CustomerCase;
import com.hz.domain.responseBean.ProposalModuleBean;
import com.hz.util.page.PageRequest;

import java.util.List;

/**
 * @author lyp
 * @date 2018/12/5
 */
public interface CustomerCaseService {

    PageInfo<CustomerCase> getCustomerCaseList(CustomerCase customerCase, PageRequest pageRequest);

    int countCustomerCase(CustomerCase customerCase);

    void updateCustomerCase(CustomerCase customerCase);

    void createCustomerCase(CustomerCase customerCase);

    CustomerCase getCustomerCaseInfo(int id);

    List<CustomerCase> getCustomerCaseInfoByModule(ProposalModuleBean proposalModuleBean);

}
