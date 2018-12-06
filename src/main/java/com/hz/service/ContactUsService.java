package com.hz.service;

import com.hz.domain.ContactUs;
import com.hz.util.page.PageRequest;

import java.util.List;

/**
 * @author lyp
 * @date 2018/12/6
 */
public interface ContactUsService {

    List<ContactUs> getContactUsList(ContactUs contactUs, PageRequest pageRequest);

    List<ContactUs> getContactUsListStencil(ContactUs contactUs);

    int countContactUs(ContactUs contactUs);

    void updateContactUs(ContactUs contactUs);

    void createContactUs(ContactUs contactUs);

    ContactUs getContactUsInfo(int id);
}
