package com.hz.service;

import com.hz.domain.Home;
import com.hz.util.page.PageRequest;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/21
 */
public interface CompanyResourceService {

    List<Home> selectHomeList(Home home, PageRequest pageRequest);
}
