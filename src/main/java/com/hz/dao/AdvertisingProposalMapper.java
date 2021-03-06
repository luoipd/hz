package com.hz.dao;

import com.hz.domain.AdvertisingProposal;

import java.util.List;

public interface AdvertisingProposalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdvertisingProposal record);

    int insertSelective(AdvertisingProposal record);

    AdvertisingProposal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdvertisingProposal record);

    int updateByPrimaryKey(AdvertisingProposal record);

    List<AdvertisingProposal> selectProposalList(AdvertisingProposal advertisingProposal);

    List<AdvertisingProposal> selectProposalListByDailishang(AdvertisingProposal advertisingProposal);

    int countProposalList(AdvertisingProposal advertisingProposal);
}