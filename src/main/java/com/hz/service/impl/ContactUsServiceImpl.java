package com.hz.service.impl;

import com.github.pagehelper.PageHelper;
import com.hz.dao.ContactUsMapper;
import com.hz.domain.ContactUs;
import com.hz.domain.CustomerCase;
import com.hz.service.ContactUsService;
import com.hz.util.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lyp
 * @date 2018/12/6
 */
@Service
public class ContactUsServiceImpl implements ContactUsService {

    @Autowired
    ContactUsMapper contactUsMapper;
    @Override
    public List<ContactUs> getContactUsList(ContactUs contactUs, PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<ContactUs> contactUses = contactUsMapper.getContactUsList(contactUs);
        return contactUses;
    }

    @Override
    public List<ContactUs> getContactUsListStencil(ContactUs contactUs) {
        List<ContactUs> contactUses = contactUsMapper.getContactUsList(contactUs);
        return contactUses;
    }

    @Override
    public int countContactUs(ContactUs contactUs) {

        return contactUsMapper.countContactUs(contactUs);
    }

    @Override
    public void updateContactUs(ContactUs contactUs) {

        contactUsMapper.updateByPrimaryKeySelective(contactUs);

    }

    @Override
    public void createContactUs(ContactUs contactUs) {
        contactUsMapper.insertSelective(contactUs);
    }

    @Override
    public ContactUs getContactUsInfo(int id) {
        ContactUs contactUs = contactUsMapper.selectByPrimaryKey(id);
        return contactUs;
    }
}
