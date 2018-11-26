package com.hz.dao;

import com.hz.domain.Market;
import com.hz.domain.responseBean.ProposalModuleBean;

import java.util.List;

public interface MarketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Market record);

    int insertSelective(Market record);

    Market selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Market record);

    int updateByPrimaryKey(Market record);

    List<Market> selectMarketAllModuleList(ProposalModuleBean proposalModuleBean);
}