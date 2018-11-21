package com.hz.dao;

import com.hz.domain.AdvertisingProposal;

public interface AdvertisingProposalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdvertisingProposal record);

    int insertSelective(AdvertisingProposal record);

    AdvertisingProposal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdvertisingProposal record);

    int updateByPrimaryKey(AdvertisingProposal record);
}