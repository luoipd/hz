package com.hz.dao;

import com.hz.domain.Home;
import com.hz.domain.responseBean.ProposalModuleBean;

import java.util.List;

public interface HomeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Home record);

    int insertSelective(Home record);

    Home selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Home record);

    int updateByPrimaryKey(Home record);

    List<Home> selectHomeList(Home home);

    int countHomeList(Home home);

    List<Home> selectHomeAllModuleList(ProposalModuleBean proposalModuleBean);

    List<Home> selectHomeListByIds(int[] ids);
}