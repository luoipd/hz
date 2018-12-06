package com.hz.dao;

import com.hz.domain.ContactUs;
import com.hz.domain.responseBean.ProposalModuleBean;

import java.util.List;

public interface ContactUsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ContactUs record);

    int insertSelective(ContactUs record);

    ContactUs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ContactUs record);

    int updateByPrimaryKey(ContactUs record);

    ContactUs getContactUsByModule(ProposalModuleBean proposalModuleBean);

    List<ContactUs> getContactUsList(ContactUs contactUs);

    int countContactUs(ContactUs contactUs);
}