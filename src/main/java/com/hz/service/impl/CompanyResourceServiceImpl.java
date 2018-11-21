package com.hz.service.impl;

import com.github.pagehelper.PageHelper;
import com.hz.dao.HomeMapper;
import com.hz.domain.Home;
import com.hz.service.CompanyResourceService;
import com.hz.util.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/21
 */
@Service
public class CompanyResourceServiceImpl implements CompanyResourceService {

    @Autowired
    HomeMapper homeMapper;

    @Override
    public List<Home> selectHomeList(Home home, PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<Home> homes = homeMapper.selectHomeList(home);
        return homes;
    }
}
